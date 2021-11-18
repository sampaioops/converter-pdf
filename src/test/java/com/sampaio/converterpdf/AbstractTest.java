package com.sampaio.converterpdf;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


@ActiveProfiles("test")
@ContextConfiguration(classes = TestContextConfiguration.class)
@SpringBootTest
public abstract class AbstractTest {

}
