package com.getourhome.propertyservice;

import com.getourhome.propertyservice.util.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PropertyServiceApplicationTests {

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void contextLoads() {
    }

}
