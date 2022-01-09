
package com.thiendz.tool.fplautolms.models;

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
public class AnswerBase implements Comparable<AnswerBase> {

    private int id;
    private String question;
    private List<String> answerTextList;
    private List<Integer> bestSolutionIdList;

    public AnswerBase(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(AnswerBase o) {
        if (o == null) {
            return 1;
        }
        return this.getId() - o.getId();
    }

}
