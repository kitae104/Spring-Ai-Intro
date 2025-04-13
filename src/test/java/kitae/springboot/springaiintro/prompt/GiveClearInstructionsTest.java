package kitae.springboot.springaiintro.prompt;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GiveClearInstructionsTest extends BaseTestClass{

    @Test
    void testGetJSon(){
        String prompt = """
                4개의 가상 자동차 목록을 생성합니다. 
                제조사, 모델명, 연식, 색상 속성을 포함하여 JSON 형식으로 제공합니다. 
                JSON 문자열을 반환합니다.
                """;

        System.out.println(chat(prompt));
    }

    @Test
    void testGetXML() {
        String prompt = """
                4개의 가상 자동차 목록을 생성합니다. 
                제조사, 모델명, 연식, 색상 속성을 포함하여 XML 형식으로 제공합니다. 
                XML 문자열을 반환합니다.
                """;

        System.out.println(chat(prompt));
    }

    String directionsPrompt = """
            모든 답변은 한국어로 작성하세요.
            세 개의 따옴표로 구분된 텍스트가 제공됩니다.
            연속적인 지침이 포함된 경우
            다음 형식으로 해당 지침을 다시 작성하세요.
            
            1단계 - ...
            2단계 - ...
            N단계 - ...
            
            텍스트에 연속적인 지침이 없는 경우 \\"제공된 단계 없음\\"이라고 작성하세요.
            
            \\"\\"\\"{text_1}\\"\\"\\"
            
            """;

    String cookASteak = """            
           완벽한 스테이크를 만드는 것은 쉽습니다.
           먼저, 스테이크를 냉장고와 포장에서 꺼냅니다. 실온에 최소 30분 동안 두세요.
           스테이크에 올리브 오일을 살짝 바르고 소금과 후추로 간을 합니다.
           다음으로, 팬을 강불로 가열합니다.
           그런 다음 스테이크를 팬에 넣고 양면을 각각 3분씩 구워줍니다.
           마지막으로 스테이크를 5분 정도 식힌 후 썰어줍니다.
           맛있게 드세요!
            """;

    @Test
    void testCookASteak() {
        PromptTemplate promptTemplate = new PromptTemplate(directionsPrompt, Map.of("text_1", cookASteak));
        System.out.println(ollamaChatModel.call(promptTemplate.create()).getResult().getOutput().getText());
    }

    @Test
    void testCookSteakAsSnoopDog() {
        PromptTemplate promptTemplate = new PromptTemplate(directionsPrompt
                + " 래퍼들이 말하는 어조로 작성하고, korean 으로 작성하세요.",
                Map.of("text_1", cookASteak));

        System.out.println(ollamaChatModel.call(promptTemplate.create()).getResult().getOutput().getText());
    }
}


