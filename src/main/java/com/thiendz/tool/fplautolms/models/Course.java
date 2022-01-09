
package com.thiendz.tool.fplautolms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    private int id;
    private String name;
    private int activeId;
    private List<Quiz> quizList;

    public Course(int id) {
        this.id = id;
    }
}
