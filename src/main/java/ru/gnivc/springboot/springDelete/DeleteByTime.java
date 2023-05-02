package ru.gnivc.springboot.springDelete;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gnivc.springboot.springDelete.repository.DataBaseRepository;

@Component
@EnableScheduling
public class DeleteByTime {
    
    private static final Logger logger = LogManager.getLogger("ru.springboot.springDelete.repository.DeleteByTime");
    
    @Autowired
    DataBaseRepository dataBaseRepository;

    @Scheduled(cron = "${firstFixedRate}")
    public void deletionEveryHour(){
        dataBaseRepository.deleteData();
    }

    @Scheduled(cron = "${secondFixedRate}")
    public void removalAtTwelveAtNight(){
            dataBaseRepository.deleteData();
      
    }


}
