package fun.jinying.demo.auwried;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jy
 * @date 2019-09-10 下午6:07
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/application.xml"})
public class HumanTest {

    private Human man;
    @Autowired
    private Human woman;

    @Test
    public void say() {
        man.say();
        woman.say();
    }

    @Autowired
    public void setMan(Human man) {
        this.man = man;
    }

    public void setWoman(Human woman) {
        this.woman = woman;
    }
}
