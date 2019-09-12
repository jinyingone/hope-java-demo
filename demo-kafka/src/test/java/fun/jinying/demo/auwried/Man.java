package fun.jinying.demo.auwried;

import org.springframework.stereotype.Component;

/**
 * @author jy
 * @date 2019-09-10 下午6:04
 **/
@Component
public class Man implements Human {
    @Override
    public void say() {
        System.out.println("i am man");
    }
}
