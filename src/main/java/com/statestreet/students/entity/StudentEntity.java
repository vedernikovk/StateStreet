package com.statestreet.students.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Student")
public class StudentEntity {

    @Id
    @SequenceGenerator(name = "student_generator", sequenceName = "student_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")    private Long id;
    private String name;

    @ManyToMany
    private Set<CourseEntity> courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseEntity> courses) {
        this.courses = courses;
    }
}
