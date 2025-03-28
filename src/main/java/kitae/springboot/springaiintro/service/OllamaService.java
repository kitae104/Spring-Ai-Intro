package kitae.springboot.springaiintro.service;

import kitae.springboot.springaiintro.model.Answer;
import kitae.springboot.springaiintro.model.Question;

public interface OllamaService {

    public String getAnswer(String question);

    public Answer getAnswer(Question question);
}
