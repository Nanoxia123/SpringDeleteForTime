package ru.gnivc.nikita.springDelete;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component

public class DataBaseRepository {


    //Логирование информации для запуска в отладке
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(DataBaseRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${QueryDelSqlParam}")
    private int QueryDelSqlParam;

    @Value("${LimitDelSqlParam}")
    private int LimitDelSqlParam;


    private String sqlTestWithLimit = "DELETE FROM tablet WHERE ctid IN (SELECT ctid FROM tablet WHERE age = ? LIMIT ?)";


    public boolean deleteData() {
        try {
            jdbcTemplate.update(sqlTestWithLimit, QueryDelSqlParam, LimitDelSqlParam);
            log.info("Сейчас выполнился запрос: {}", sqlTestWithLimit);
            return true;
        }
        catch (DataAccessException exception) {
            log.info("Не работает");
            return false;
        }

    }
}














