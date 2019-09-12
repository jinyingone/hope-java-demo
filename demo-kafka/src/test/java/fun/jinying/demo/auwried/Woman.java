package fun.jinying.demo.auwried;

import org.springframework.stereotype.Component;

/**
 * @author jy
 * @date 2019-09-10 下午6:05
 **/
@Component
public class Woman implements Human {
    @Override
    public void say() {
        System.out.println("I an humam");
    }
}
