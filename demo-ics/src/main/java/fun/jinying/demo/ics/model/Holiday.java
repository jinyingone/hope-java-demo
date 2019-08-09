package fun.jinying.demo.ics.model;

import java.util.Date;
import java.util.List;

/**
 * 节假日
 *
 * @author jy
 * @date 2019-08-07 下午3:11
 **/
public class Holiday {
    private String name;
    private Date startTime;
    private Date endTime;
    private int days;
    private List<Date> adjusts;
}
