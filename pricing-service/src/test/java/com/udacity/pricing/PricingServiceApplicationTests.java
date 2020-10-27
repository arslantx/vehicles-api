package com.udacity.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import com.udacity.pricing.entity.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
    }

    @Test
    public void getPriceByVehicleId() {
        ResponseEntity<Price> response = this.restTemplate.getForEntity("http://localhost:" + port + "/prices/1", Price.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("USD", response.getBody().getCurrency());
        assertEquals(new BigDecimal(15200.25), response.getBody().getPrice());
    }
}
