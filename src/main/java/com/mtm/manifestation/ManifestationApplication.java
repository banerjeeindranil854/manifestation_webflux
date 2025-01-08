package com.mtm.manifestation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "manifestation", version = "1.0", description = "Documentation manifestation v1.0"))
class ManifestationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManifestationApplication.class, args);
    }

}