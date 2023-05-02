package ru.gnivc.springboot.springDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDeleteApplication {

	@Autowired
	private DeleteByTime deleteByTime;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDeleteApplication.class, args);
		SpringDeleteApplication app = context.getBean(SpringDeleteApplication.class);
		app.startDeletion();
	}
	public void startDeletion(){
		deleteByTime.firstFixedRate();
		deleteByTime.secondFixedRate();
	}
}
