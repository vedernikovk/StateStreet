package com.statestreet.students.service;

import com.statestreet.students.entity.CourseEntity;
import com.statestreet.students.entity.StudentEntity;
import com.statestreet.students.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class StudentServiceImpl implements StudentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addStudent(Student student) {
        var newStudent = new StudentEntity();
        newStudent.setName(student.getName());

        Set<CourseEntity> courses = student.getCourses().stream().map(id -> {
            var course = new CourseEntity();
            course.setId(id);
            return course;

        }).collect(Collectors.toSet());

        newStudent.setCourses(courses);
        em.persist(newStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(long id) throws StudentNotFoundException {
        var student = em.find(StudentEntity.class, id);
        if (student == null) {
            throw new StudentNotFoundException();
        }

        em.remove(student);
    }

    @Override
    public List<String> findEnrolledStudents(String courseName) {
        List result = em.createQuery("Select s.name from StudentEntity s, IN (s.courses) c Where c.name = :name Order by s.name")
                .setParameter("name", courseName)
                .getResultList();
        return result;
    }

    @Override
    public List<String> findNotEnrolledStudents(String courseName) {
        List<String> allStudents = (List<String>) em.createQuery("Select s.name from StudentEntity s").getResultList();

        List<String> courseStudents = findEnrolledStudents(courseName);

        allStudents.removeAll(courseStudents);

        return allStudents;
    }
}
