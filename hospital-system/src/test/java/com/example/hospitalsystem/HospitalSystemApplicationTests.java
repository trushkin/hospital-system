package com.example.hospitalsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "scheduler.enabled=false")

class HospitalSystemApplicationTests {

    @Test
    void contextLoads() {
    }

}
