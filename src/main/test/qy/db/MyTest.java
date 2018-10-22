package qy.db;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:./context.xml"})
public class MyTest {

    @BeforeAll
    public void beforeTest(){
        System.out.println("ddddd");
    }

    @Test
    private void test(){
    }
}
