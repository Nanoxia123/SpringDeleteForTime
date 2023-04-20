package ru.gnivc.nikita.springDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringDeleteApplication {



	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDeleteApplication.class, args);

		DeleteByTime deleteByTime = context.getBean(DeleteByTime.class);
		deleteByTime.deletionEveryHour();
		//deleteByTime.removalAtTwelveAtNight();


	}

}
