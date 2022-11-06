package com.ysl.bookingtest.repository;

import com.ysl.bookingtest.model.Availability;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {
    /* Create */
    Availability save(Availability toBeSaved);

//    Iterable<Availability> saveAll(List<Availability> toBeSaved);


    /* Read */
    List<Availability> findAll();

    @Query(value = "SELECT a FROM Availability a " +
                    "WHERE a.timeSlot.startTime >= :startTime AND a.timeSlot.endTime <= :endTime")
//    @Query(value = "SELECT a FROM Availability a " +
//        "WHERE a.startTime >= :startTime AND a.endTime <= :endTime")
    List<Availability> findAllBetween(@Param("startTime") LocalDateTime startTime,
                                      @Param("endTime") LocalDateTime endTime);

    @Query(value = "SELECT a FROM Availability a WHERE a.reservation IS NULL")
    List<Availability> findAvailable();

    @Query(value = "SELECT a FROM Availability a " +
                    "WHERE a.timeSlot.startTime >= :startTime AND a.timeSlot.endTime <= :endTime " +
                    "AND a.reservation IS NULL")
//    @Query(value = "SELECT a FROM Availability a " +
//        "WHERE a.startTime >= :startTime AND a.endTime <= :endTime " +
//        "AND a.reservation IS NULL")
    List<Availability> findAvailableBetween(@Param("startTime") LocalDateTime startTime,
                                   @Param("endTime") LocalDateTime endTime);

    Optional<Availability> findById(@NonNull Long id);

}
