package com.fiap.techchallenger5.msauthusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsauthusersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsauthusersApplication.class, args);
	}

}
