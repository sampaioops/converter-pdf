package com.sampaio.converterpdf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.sampaio.converterpdf")
public class TestContextConfiguration {
}
