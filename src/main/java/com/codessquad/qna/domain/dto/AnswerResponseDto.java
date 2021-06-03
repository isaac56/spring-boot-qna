package com.codessquad.qna.domain.dto;

import com.codessquad.qna.domain.Answer;
import com.codessquad.qna.domain.User;
import com.codessquad.qna.domain.validationGroup.Submit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class AnswerResponseDto {
    private long id;

    @NotNull
    private User writer;

    @NotBlank(groups = {Submit.class, Default.class})
    private String contents;

    private AnswerResponseDto(long id, @NotNull User writer, @NotBlank(groups = {Submit.class, Default.class}) String contents) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }

    public static AnswerResponseDto of(Answer answer) {
        return new AnswerResponseDto(answer.getId(), answer.getWriter(), answer.getContents());
    }

    public long getId() {
        return id;
    }

    public User getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }
}
