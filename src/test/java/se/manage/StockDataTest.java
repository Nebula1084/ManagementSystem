package se.manage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.manage.stock.VolatileStock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class StockDataTest {

    @Value("${server.port}")
    private int port;

    @Before
    public void before() {

    }

    @Test
    public void test() {

    }

}
