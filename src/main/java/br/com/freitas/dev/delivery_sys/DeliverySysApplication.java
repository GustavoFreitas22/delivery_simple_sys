package br.com.freitas.dev.delivery_sys;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Delivery API", version = "1.0", description = "Simple delivery sys"))
public class DeliverySysApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverySysApplication.class, args);
    }

}
