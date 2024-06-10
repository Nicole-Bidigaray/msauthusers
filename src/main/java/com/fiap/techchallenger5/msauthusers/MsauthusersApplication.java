package com.fiap.techchallenger5.msauthusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.fiap.techchallenger5.msauthusers.domain.consumer")
public class MsauthusersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsauthusersApplication.class, args);
	}

}
