package com.example;

import java.time.OffsetDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableCaching // キャッシュ機能を有効にします
public class HelloRedisApplication {
	private final Greeter greeter;

	public HelloRedisApplication(Greeter greeter) {
		this.greeter = greeter;
	}

	@GetMapping("/")
	String hello() {
		return greeter.hello();
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloRedisApplication.class, args);
	}
}

@Component
class Greeter {
	@Cacheable("hello") // 実行結果をキャッシュします
	public String hello() {
		return "Hello. It's " + OffsetDateTime.now() + " now.";
	}
}

