package fun.jinying.demo.ics.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IcsDaoImplTest {
    @Autowired
    IcsDao icsDao;

    @Test
    public void testGet() {
        System.out.println(icsDao.getZhcsHolidays());
    }

}