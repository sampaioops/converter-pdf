package com.sampaio.converterpdf.configuration.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JdbcConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.write")
    public HikariConfig writeConfiguration(){
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.read")
    public HikariConfig readConfiguration(){
        return new HikariConfig();
    }

    @Bean
    public DataSource writeDataSource(){
        return new HikariDataSource(writeConfiguration());
    }

    @Bean
    public DataSource readDataSource(){
        return new HikariDataSource(readConfiguration());
    }

    @Primary
    @Bean
    @DependsOn(value = {"readDataSource", "writeDataSource"})
    public DataSource dataSource(final DataSource readDataSource, final DataSource writeDataSource){
        final var routingDataSource = new RoutingDataSource();

        final Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(RoutingDataSource.Route.PRIMARY, readDataSource);
        targetDataSources.put(RoutingDataSource.Route.REPLICA, writeDataSource);

        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);

        return routingDataSource;
    }

    @Bean
    public LockProvider lockProvider(@Qualifier("dataSource") final DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") final DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
