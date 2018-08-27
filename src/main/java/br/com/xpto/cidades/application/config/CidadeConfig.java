package br.com.xpto.cidades.application.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
public class CidadeConfig {

    @Value("${spring.datasource.url}")
    private String springDataSourceUrl;
    @Value("${spring.datasource.username}")
    private String springDataSourceUsername;
    @Value("${spring.datasource.password}")
    private String springDataSourcePassword;
    @Value("${spring.datasource.driver}")
    private String springDataSourceDriver;
    @Value("${spring.jpa.database}")
    private String springJpaDataBase;

    public String getSpringDataSourceUrl() {
        return springDataSourceUrl;
    }

    public String getSpringDataSourceUsername() {
        return springDataSourceUsername;
    }

    public String getSpringDataSourcePassword() {
        return springDataSourcePassword;
    }

    public String getSpringDataSourceDriver() {
        return springDataSourceDriver;
    }

    public String getSpringJpaDataBase() {
        return springJpaDataBase;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getSpringDataSourceDriver());
        dataSource.setUrl(getSpringDataSourceUrl());
        dataSource.setUsername(getSpringDataSourceUsername());
        dataSource.setPassword(getSpringDataSourcePassword());
        return dataSource;
    }

}
