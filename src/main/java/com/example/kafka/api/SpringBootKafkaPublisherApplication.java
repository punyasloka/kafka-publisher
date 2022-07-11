package com.example.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootKafkaPublisherApplication {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topic = "kafkatest";

	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		template.send(topic, "Hello " + name);
		return "Data Published";
	}

	@GetMapping("/publishJson")
	public String publishJson() {
		User user = new User(903, "test", new String[]{"Sandton", "GT", "2199"});
		template.send(topic, user);
		return "JSON Data Published";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaPublisherApplication.class, args);
	}

}
