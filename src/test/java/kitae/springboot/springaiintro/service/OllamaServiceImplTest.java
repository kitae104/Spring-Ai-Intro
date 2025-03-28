package kitae.springboot.springaiintro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OllamaServiceImplTest {

    @Autowired
    private OllamaService service;

    @Test
    void getAnswer() {
        String answer = service.getAnswer("Write a python script to output numbers from 1 to 100.");
        System.out.println("답변 =================");
        System.out.println(answer);
    }

    @Test
    void testGetAnswer() {
    }
}