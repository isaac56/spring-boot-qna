package com.codessquad.qna.domain;

import com.codessquad.qna.domain.validationGroup.Submit;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Entity
@Where(clause = "deleted = false")
public class Question extends BaseEntity {
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    @NotNull
    private User writer;

    @Column(nullable = false, length = 100)
    @NotBlank(groups = {Submit.class, Default.class})
    private String title;

    @Column(nullable = false, length = 3000)
    @NotBlank(groups = {Submit.class, Default.class})
    private String contents;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    private List<Answer> answers;

    @Column(columnDefinition = "int default 0")
    @NotNull
    private int countOfAnswer;

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private boolean deleted;

    protected Question() {
    }

    public Question(User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public Integer getCountOfAnswer() {
        return countOfAnswer;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void delete() {
        answers.stream().forEach(answer -> answer.delete());
        this.deleted = true;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public boolean isWriter(User user) {
        return writer.isSameId(user);
    }

    public boolean canDeleted() {
        return answers.stream().filter(answer -> !answer.isWriter(this.writer)).count() == 0;
    }

    public void increaseAnswerCount() {
        this.countOfAnswer++;
    }

    public void decreaseAnswerCount() {
        this.countOfAnswer--;
    }
}
