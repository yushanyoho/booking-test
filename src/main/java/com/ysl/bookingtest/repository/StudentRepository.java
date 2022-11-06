package com.ysl.bookingtest.repository;

import com.ysl.bookingtest.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query(value = "SELECT s FROM Student s WHERE s.username LIKE :username")
    Optional<Student> findByUsernameLikeIgnoreCase(@Param ("username")@NonNull String username);

}
