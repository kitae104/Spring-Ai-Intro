package kitae.springboot.springaiintro.controller;

import kitae.springboot.springaiintro.model.Answer;
import kitae.springboot.springaiintro.model.GetCapitalRequest;
import kitae.springboot.springaiintro.model.Question;
import kitae.springboot.springaiintro.service.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final OllamaService ollamaService;

    /**
     * 일반적인 질문
     * @param question
     * @return
     */
    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question){
        return ollamaService.getAnswer(question);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
        return ollamaService.getCaptial(getCapitalRequest);
    }

}
