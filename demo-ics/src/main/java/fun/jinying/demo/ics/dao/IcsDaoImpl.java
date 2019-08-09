package fun.jinying.demo.ics.dao;

import fun.jinying.demo.ics.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实现
 *
 * @author jy
 * @date 2019-08-07 下午3:10
 **/
@Repository
public class IcsDaoImpl implements IcsDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Holiday> getZhcsHolidays() {
        jdbcTemplate.execute("create table TEST\n" +
                "(\n" +
                "  ID   INTEGER,\n" +
                "  NAME VARCHAR(32)\n" +
                ");");


        String sql = "SELECT * from public.TEST";
        List<Holiday> holidays = jdbcTemplate.queryForList(sql, Holiday.class);
        return holidays;
    }
}
