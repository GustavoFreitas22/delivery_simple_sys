package br.com.freitas.dev.delivery_sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DeliverySysApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliverySysApplication.class, args);
	}

}
