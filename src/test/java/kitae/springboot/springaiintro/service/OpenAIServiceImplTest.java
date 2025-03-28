package kitae.springboot.springaiintro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    private OllamaService openAIService;

    @Test
    public void getAnswer() {
        String answer = openAIService.getAnswer("Write a python script to output numbers from 1 to 100.");
        System.out.println("답변 =================");
        System.out.println(answer);
    }
}