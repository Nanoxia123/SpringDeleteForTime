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

    @Scheduled(cron = "${fixedRateEveryHour}")
    public void deletionEveryHour(){
        dataBaseRepository.deleteData();
        logger.info("Функция 'Удаления каждый час' успешно выполнена. Следующее удаление через час");
    }

    @Scheduled(cron = "${everyTwelveHoursOfTheNightRate}")
    public void removalAtTwelveAtNight(){
        LocalTime time = LocalTime.now();
        if (time.equals(LocalTime.MIDNIGHT)) {
            dataBaseRepository.deleteData();
            logger.info("Функция 'Удаление в 12 ночи' успешно выполнена.");
        }
        else{
            logger.info("Функция 'Удаление в 12 ночи' не выполнена, так как сейчас не 12 ночи. Текущее время {}", LocalTime.now());

        }
    }


}
