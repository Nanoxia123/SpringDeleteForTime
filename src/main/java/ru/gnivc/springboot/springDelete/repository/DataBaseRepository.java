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
    private final String tableSqlParam;
    private final String primalKeyColumnSqlParam;
    private final String arithmeticSignSqlParam;
    private final String secondColumnSqlParam;

    @Autowired
    public DataBaseRepository(JdbcTemplate jdbcTemplate,
                              @Value("${queryDelSqlParam}") int queryDelSqlParam,
                              @Value("${limitDelSqlParam}") int limitDelSqlParam,
                              @Value("${tableSqlParam}") String tableSqlParam,
                              @Value("${primalKeyColumnSqlParam}") String primalKeyColumnSqlParam,
                              @Value("${arithmeticSignSqlParam}") String arithmeticSignSqlParam,
                              @Value("${secondColumnSqlParam}") String secondColumnSqlParam) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryDelSqlParam = queryDelSqlParam;
        this.limitDelSqlParam = limitDelSqlParam;
        this.tableSqlParam = tableSqlParam;
        this.primalKeyColumnSqlParam = primalKeyColumnSqlParam;
        this.arithmeticSignSqlParam = arithmeticSignSqlParam;
        this.secondColumnSqlParam = secondColumnSqlParam;
    }


    public boolean deleteData() {
        String limitedDeletionRequest = String.format("DELETE FROM %s WHERE %s IN (SELECT %s FROM %s WHERE %s %s ? LIMIT ?)",
                tableSqlParam, primalKeyColumnSqlParam, primalKeyColumnSqlParam, tableSqlParam, secondColumnSqlParam, arithmeticSignSqlParam);
        try {
            jdbcTemplate.update(limitedDeletionRequest, queryDelSqlParam, limitDelSqlParam);
            logger.info("SQL запрос {} был успешно выполнен. Использовались следующие данные: " +
                            "Таблица: {}, Условие: {}, Второе условие: {}, Арифметический знак: {}. Лимит на количество удаляемых строк: {}",
                    limitedDeletionRequest, tableSqlParam, primalKeyColumnSqlParam, secondColumnSqlParam, arithmeticSignSqlParam, limitDelSqlParam);
            return true;
        }
        catch (DataAccessException exception) {
            logger.error("Произошла ошибка при выполнении запроса. Параметры были заданы не верно. sql запрос {}", limitedDeletionRequest);
            return false;
        }

    }
}














