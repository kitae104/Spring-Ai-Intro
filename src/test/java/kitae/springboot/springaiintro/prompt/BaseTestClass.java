package kitae.springboot.springaiintro.prompt;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * BaseTestClass는 Spring Boot 테스트 환경에서 OllamaChatModel을 사용하여
 * 프롬프트를 처리하는 기본 테스트 클래스를 제공합니다.
 */
@SpringBootTest
public class BaseTestClass {

    /**
     * OllamaChatModel 인스턴스. Spring의 @Autowired를 통해 주입됩니다.
     */
    @Autowired
    OllamaChatModel ollamaChatModel;

    /**
     * 주어진 프롬프트를 OllamaChatModel에 전달하여 처리된 결과를 반환합니다.
     *
     * @param prompt 처리할 프롬프트 문자열
     * @return OllamaChatModel에서 반환된 텍스트 결과
     */
    String chat(String prompt) {
        // 프롬프트 템플릿 생성
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        // 템플릿을 기반으로 프롬프트 객체 생성
        Prompt promptToSend = promptTemplate.create();

        // OllamaChatModel을 호출하여 결과 텍스트 반환
        ChatResponse response = ollamaChatModel.call(promptToSend);
        return response.getResult().getOutput().getText();
    }
}