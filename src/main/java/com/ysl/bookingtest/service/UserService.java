package com.ysl.bookingtest.service;

import com.ysl.bookingtest.model.Student;
import com.ysl.bookingtest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    StudentRepository studentRepository;

    public Student createStudentAccount(Student student) {
        Student saved = studentRepository.save(student);

        return saved;
    }

    public Student createStudentAccount(String username) {
        Student toBeSaved = new Student(username);
        Student saved = studentRepository.save(toBeSaved);

        return saved;
    }

    public Student getStudentByName(String username) {
        Optional<Student> opt = studentRepository.findByUsernameLikeIgnoreCase(username);

        if (opt.isEmpty()) {
            return createStudentAccount(username);
        } else {
            return opt.get();
        }
    }
}
