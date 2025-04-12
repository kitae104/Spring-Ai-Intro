package kitae.springboot.springaiintro.service;

// 필요한 모델 및 라이브러리 임포트

import com.fasterxml.jackson.databind.ObjectMapper;
import kitae.springboot.springaiintro.model.Answer;
import kitae.springboot.springaiintro.model.GetCapitalRequest;
import kitae.springboot.springaiintro.model.GetCapitalResponse;
import kitae.springboot.springaiintro.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

// 서비스 클래스 선언 및 @Service 어노테이션으로 Spring Bean으로 등록
@Service
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성
public class OllamaServiceImpl implements OllamaService {

    // OllamaChatModel 의존성 주입
    private final OllamaChatModel chatModel;

    private final ObjectMapper objectMapper;  // JSON 변환을 위한 ObjectMapper 주입

    // 질문에 대한 답변을 문자열로 반환하는 메서드
    @Override
    public String getAnswer(String question) {
        // PromptTemplate을 사용하여 프롬프트 생성
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        // Ollama 모델 호출
        ChatResponse response = chatModel.call(prompt);
        // 결과 텍스트 반환
        return response.getResult().getOutput().getText();
    }

    // Question 객체를 받아 Answer 객체를 반환하는 메서드
    @Override
    public Answer getAnswer(Question question) {
        // Question의 내용을 기반으로 프롬프트 생성
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        // Ollama 모델 호출
        ChatResponse response = chatModel.call(prompt);
        // 결과를 Answer 객체로 반환
        return new Answer(response.getResult().getOutput().getText());
    }


    // get-capital-prompt.st 템플릿 파일을 읽기 위한 Resource 주입
    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    // GetCapitalRequest를 받아 Answer 객체를 반환하는 메서드
    @Override
    public GetCapitalResponse getCaptial(GetCapitalRequest getCapitalRequest) {

        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();
        System.out.println("format = " + format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of(
                "stateOrCountry", getCapitalRequest.stateOrCountry(),
                "format", format
        ));

        ChatResponse response = chatModel.call(prompt);
        System.out.println(response.getResult().getOutput().getText());

        return converter.convert(response.getResult().getOutput().getText());
    }

    // get-capital-prompt-with-info.st 템플릿 파일을 읽기 위한 Resource 주입
    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalPromptWithInfo;

    // 추가 정보를 포함하여 GetCapitalRequest를 처리하는 메서드
    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        // 템플릿 파일을 기반으로 프롬프트 생성
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPromptWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry())); // 매개변수 전달
        // Ollama 모델 호출
        ChatResponse response = chatModel.call(prompt);
        // 결과를 Answer 객체로 반환
        return new Answer(response.getResult().getOutput().getText());
    }
}