package ru.gnivc.springboot.springDelete;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gnivc.springboot.springDelete.repository.DataBaseRepository;

import java.time.LocalTime;

@Component
@EnableScheduling
public class DeleteByTime {
    @Autowired
    DataBaseRepository dataBaseRepository;

    private static final Logger logger = LogManager.getLogger("ru.springboot.springDelete.repository.DeleteByTime");

    @Scheduled(cron = "${firstFixedRate}")
    public void deletionEveryHour(){
        dataBaseRepository.deleteData();
        logger.info("Функция 'Первого удаления' успешно выполнена.");
    }

    @Scheduled(cron = "${secondfirstFixedRate}")
    public void removalAtTwelveAtNight(){
            dataBaseRepository.deleteData();
            logger.info("Функция 'Второго удаления' успешно выполнена.");
    }


}
