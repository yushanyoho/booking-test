package com.ysl.bookingtest.repository;

import com.ysl.bookingtest.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query(value = "SELECT r FROM Reservation r WHERE r.student.id = :student_id")
    List<Reservation> findByStudent_Id(@NonNull Long student_id);

//    List<Reservation> saveAll(List<Reservation> toBeSaved);
}
