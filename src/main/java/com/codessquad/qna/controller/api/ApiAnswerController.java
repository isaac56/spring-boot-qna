package com.codessquad.qna.controller.api;

import com.codessquad.qna.entity.Answer;
import com.codessquad.qna.entity.User;
import com.codessquad.qna.service.AnswerService;
import com.codessquad.qna.util.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AnswerService answerService;

    @Autowired
    public ApiAnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public Answer create(@PathVariable long questionId, String contents, HttpSession session) {
        logger.debug("{}번 질문에 답변 생성 요청", questionId);
        User user = HttpSessionUtils.getUser(session);
        return answerService.addAnswer(questionId, user, contents);
    }

    @DeleteMapping("/{answerId}")
    public String delete(@PathVariable long questionId, @PathVariable long answerId, HttpSession session) {
        logger.debug("{}번 질문의 {}번 답변 삭제 요청", questionId, answerId);
        User user = HttpSessionUtils.getUser(session);
        answerService.deleteAnswer(answerId, user);
        return "redirect:/questions/" + questionId;
    }
}