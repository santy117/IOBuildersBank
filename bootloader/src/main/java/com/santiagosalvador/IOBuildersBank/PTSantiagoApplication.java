package com.santiagosalvador.IOBuildersBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.santiagosalvador.IOBuildersBank.output.entity")
public class PTSantiagoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PTSantiagoApplication.class, args);
    }

}