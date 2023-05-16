package com.boosters.mda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MdaApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(MdaApplication.class, args);
	}
	
}
/*
 * Auditing 기능을 활성화시키기 위해선
 * 	Application 클래스에 @EnableJpaAuditing 어노테이션을 추가해주어야한다
 */