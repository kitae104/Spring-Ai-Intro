package kitae.springboot.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalResponse(@JsonPropertyDescription("이것은 도시 이름 입니다.") String answer) {
}
