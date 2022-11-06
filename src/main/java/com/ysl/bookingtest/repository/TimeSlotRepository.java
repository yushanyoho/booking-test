package com.ysl.bookingtest.repository;

import com.ysl.bookingtest.model.TimeSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
    TimeSlot save(TimeSlot timeSlot);
}
