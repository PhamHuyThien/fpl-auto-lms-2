package com.thiendz.tool.fplautolms.services;

import com.thiendz.tool.fplautolms.models.Course;
import com.thiendz.tool.fplautolms.models.User;

public class CourseService {
    User user;
    Integer refIdCourse;
    Course course;

    public CourseService(User user, Integer refIdCourse) {
        this.user = user;
        this.refIdCourse = refIdCourse;
    }

    public void start() {
        course = new Course(refIdCourse);
    }

    public Course getCourse() {
        return course;
    }
}
