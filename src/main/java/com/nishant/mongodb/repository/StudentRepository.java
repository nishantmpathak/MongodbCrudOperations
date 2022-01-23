package com.nishant.mongodb.repository;

import com.nishant.mongodb.data.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    @Query("{'name.firstName':'?0'}")
    List<Student> findStudentByFirstName(String name);

    @Query("{rollNumber:?0}")
    Student findStudentByRollNumber(int rollNumber);
}
