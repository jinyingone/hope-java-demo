package fun.jinying.demo.ics.dao;

import fun.jinying.demo.ics.model.Holiday;

import java.util.List;

/**
 * dao
 *
 * @author jy
 * @date 2019-08-07 下午3:10
 **/
public interface IcsDao {

    List<Holiday> getZhcsHolidays();
}
