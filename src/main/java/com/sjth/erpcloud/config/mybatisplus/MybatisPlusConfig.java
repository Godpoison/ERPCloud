package com.sjth.erpcloud.config.mybatisplus;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//扫描dao或mapper接口
@MapperScan("com.sjth.erpcloud.module.*.dao")
public class MybatisPlusConfig {
    /**
     * 分页插件，自动识别数据库类型
     *
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("sqlserver");
        return paginationInterceptor;
    }

    /**
     * sql执行效率插件
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }
}
