package com.monster.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@MapperScan("com.monster.shardingjdbc.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ShardingjdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingjdbcDemoApplication.class, args);
	}

}
