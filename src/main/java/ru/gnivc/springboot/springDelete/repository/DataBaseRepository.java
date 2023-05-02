package ru.gnivc.springboot.springDelete.repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component

public class DataBaseRepository {

    private static final Logger logger = LogManager.getLogger("ru.springboot.springDelete.repository.DataBaseRepository");
    private final JdbcTemplate jdbcTemplate;
    private final int queryDelSqlParam;
    private final int limitDelSqlParam;
    private final String tableDelSqlParam;
    private final String primalKeyColumnDelSqlParam;
    private final String arithmeticSignDelSqlParam;
    private final String secondColumnDelSqlParam;

    @Autowired
    public DataBaseRepository(JdbcTemplate jdbcTemplate,
                              @Value("${queryDelSqlParam}") int queryDelSqlParam,
                              @Value("${limitDelSqlParam}") int limitDelSqlParam,
                              @Value("${tableDelSqlParam}") String tableDelSqlParam,
                              @Value("${primalKeyColumnDelSqlParam}") String primalKeyColumnDelSqlParam,
                              @Value("${arithmeticSignDelSqlParam}") String arithmeticSignDelSqlParam,
                              @Value("${secondColumnDelSqlParam}") String secondColumnDelSqlParam) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryDelSqlParam = queryDelSqlParam;
        this.limitDelSqlParam = limitDelSqlParam;
        this.tableDelSqlParam = tableDelSqlParam;
        this.primalKeyColumnDelSqlParam = primalKeyColumnDelSqlParam;
        this.arithmeticSignDelSqlParam = arithmeticSignDelSqlParam;
        this.secondColumnDelSqlParam = secondColumnDelSqlParam;
    }


    public boolean deleteData() {
        String limitedDeletionRequest = String.format("DELETE FROM %s WHERE %s IN (SELECT %s FROM %s WHERE %s %s ? LIMIT ?)",
                tableDelSqlParam, primalKeyColumnDelSqlParam, primalKeyColumnDelSqlParam, tableDelSqlParam, secondColumnDelSqlParam, arithmeticSignDelSqlParam);
        try {
            jdbcTemplate.update(limitedDeletionRequest, queryDelSqlParam, limitDelSqlParam);
            logger.info("SQL запрос {} был успешно выполнен. Использовались следующие данные: " +
                            "Таблица: {}, Условие: {}, Второе условие: {}, Арифметический знак: {}. Лимит на количество удаляемых строк: {}",
                    limitedDeletionRequest, tableDelSqlParam, primalKeyColumnDelSqlParam, secondColumnDelSqlParam, arithmeticSignDelSqlParam, limitDelSqlParam);
            return true;
        }
        catch (DataAccessException exception) {
            logger.error("Произошла ошибка при выполнении запроса. Параметры были заданы не верно. sql запрос {}", limitedDeletionRequest);
            return false;
        }

    }
}














