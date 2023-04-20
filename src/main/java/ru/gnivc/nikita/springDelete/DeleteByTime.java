package ru.gnivc.nikita.springDelete;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DeleteByTime {

    @Autowired
    DataBaseRepository dataBaseRepository;


    //Удаление каждый час
    @Scheduled(cron = "${FixedRateEveryHour}")
    public void deletionEveryHour(){
        dataBaseRepository.deleteData();
        //System.out.println("OK");
    }

    //Удаление каждый раз в 12 ночи
    @Scheduled(cron = "${EveryTwelveHoursOfTheNightRate}")
    public void removalAtTwelveAtNight(){
        dataBaseRepository.deleteData();
        //System.out.println("OK");
    }


}
