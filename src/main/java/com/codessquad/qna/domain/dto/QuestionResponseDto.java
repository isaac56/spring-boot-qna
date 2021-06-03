package com.codessquad.qna.domain.dto;

import com.codessquad.qna.domain.Question;
import com.codessquad.qna.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionResponseDto {
    private long id;

    @NotNull
    private User writer;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    private List<AnswerResponseDto> answers;

    @NotNull
    private int countOfAnswer;

    public QuestionResponseDto(long id, @NotNull User writer, @NotBlank String title, @NotBlank String contents,
                               List<AnswerResponseDto> answers, @NotNull int countOfAnswer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = answers;
        this.countOfAnswer = countOfAnswer;
    }

    public static QuestionResponseDto of(Question question) {
        return new QuestionResponseDto(
                question.getId(),
                question.getWriter(),
                question.getTitle(),
                question.getContents(),
                question.getAnswers().stream().map(x -> AnswerResponseDto.of(x)).collect(Collectors.toList()),
                question.getCountOfAnswer()
        );
    }

    public long getId() {
        return id;
    }

    public User getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public List<AnswerResponseDto> getAnswers() {
        return answers;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }
}
