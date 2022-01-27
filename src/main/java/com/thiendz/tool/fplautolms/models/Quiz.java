package com.thiendz.tool.fplautolms.models;

import com.thiendz.tool.fplautolms.utils.enums.QuizState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz implements Comparable<Quiz> {

    private int id;
    private int timeRemaining;
    private String name;
    private boolean automationSupport;
    private QuizState quizState;
    private List<AnswerBase> answerBaseList;

    @Override
    public int compareTo(Quiz o) {
        if (o == null) {
            return 1;
        }
        return id - o.id;
    }

}
