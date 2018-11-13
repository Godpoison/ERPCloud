package com.sjth.erpcloud.config.shiro;
 
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.sjth.erpcloud.module.auth.entity.AuthUser;
import com.sjth.erpcloud.module.auth.entity.Permission;
import com.sjth.erpcloud.module.auth.entity.Role;
import com.sjth.erpcloud.module.auth.service.AuthUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/12/11.
 * 自定义权限匹配和账号密码匹配
 */
public class MyShiroRealm extends AuthorizingRealm {
    private Logger logger =  LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
    private AuthUserService authUserService;
    /**
     * 为用户授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取前端输入的用户信息，封装为User对象
    	AuthUser userweb = (AuthUser) principals.getPrimaryPrincipal();
        //获取前端输入的用户名
        String username = userweb.getUserName();
        //根据前端输入的用户名查询数据库中对应的记录
        AuthUser user = authUserService.findByUsername(username);
        //如果数据库中有该用户名对应的记录，就进行授权操作
        if (user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //因为addRoles和addStringPermissions方法需要的参数类型是Collection
            //所以先创建两个collection集合
            Collection<String> rolesCollection = new HashSet<String>();
            Collection<String> perStringCollection = new HashSet<String>();
            //获取user的Role的set集合
            Set<Role> roles = user.getRoles();
            //遍历集合
            for (Role role : roles){
                //将每一个role的name装进collection集合
                rolesCollection.add(role.getRoleName());
                //获取每一个Role的permission的set集合
                Set<Permission> permissionSet =  role.getPermissions();
                //遍历集合
                for (Permission permission : permissionSet){
                    //将每一个permission的name装进collection集合
                    perStringCollection.add(permission.getPermissionName());
                }
                //为用户授权
                info.addStringPermissions(perStringCollection);
            }
            //为用户授予角色
            info.addRoles(rolesCollection);
            return info;
        }else{
            return null;
        }

    }



    /**
     * 认证登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	 // 将token装换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 获取用户名即可
        String username = upToken.getUsername();
        // 查询数据库，是否查询到用户名和密码的用户
        AuthUser userInfo = authUserService.findByUsername(username);
        AuthenticationInfo info = null;
        if(userInfo != null) {
            // 如果查询到了，封装查询结果，返回给我们的调用
            Object principal =  userInfo.getUserName();
            Object credentials = userInfo.getPassword();
            // 获取盐值，即用户名
            ByteSource salt = ByteSource.Util.bytes(username);
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            info = new SimpleAuthenticationInfo(principal, credentials, salt,realmName);
        }else {
            // 如果没有查询到，抛出一个异常
            throw new AuthenticationException();
        }
        return info;
    }
}