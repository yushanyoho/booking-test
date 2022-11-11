package com.ysl.bookingtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.TODO;
import com.ysl.bookingtest.model.Availability;
import com.ysl.bookingtest.model.Reservation;
import com.ysl.bookingtest.model.Student;
import com.ysl.bookingtest.model.TimeSlot;
import com.ysl.bookingtest.repository.AvailabilityRepository;
import com.ysl.bookingtest.repository.ReservationRepository;
import com.ysl.bookingtest.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookingService {

    @Autowired
    TimeSlotRepository timeSlotRepository;
    @Autowired
    AvailabilityRepository avbRepository;
    @Autowired
    ReservationRepository rsvRepository;


    /* Availability */

    /**
     * Retrieve all available timeslots (all instances of Availability haven't been booked)
     * @return The list of instances of Availability haven't been booked
     */
    public List<Availability> getAvailabilities() {
        return avbRepository.findAvailable();
    }

    /**
     * Retrieve from repository the list of instances of Availability between the provided time.
     * @param startTime Create instances of Availability from what time.
     * @param endTime Create instances of Availability until what time.
     * @return The list of instances of Availability between the provided time
     */
    public List<Availability> getAvailabilities(LocalDateTime startTime, LocalDateTime endTime) {
        return avbRepository.findAvailableBetween(startTime, endTime);
    }

    /**
     * Create instances of Availability from a range of time, 1h for each instance.
     * @param startTime Create instances of Availability from what time.
     * @param endTime Create instances of Availability until what time.
     * @return The list of created and saved instances of Availability.
     */
    public List<Availability> createAvailabilities(LocalDateTime startTime, LocalDateTime endTime) {
        List<Availability> avbs = new ArrayList<>();
        List<TimeSlot> slots = new ArrayList<>();

        while (startTime.isBefore(endTime)){
            TimeSlot newSlot = new TimeSlot(startTime, startTime.plusHours(1));
            slots.add(newSlot);

            Availability newAvb = new Availability(newSlot);
//            Availability newAvb = new Availability(startTime, startTime.plusHours(1));
            avbs.add(newAvb);

            startTime = startTime.plusHours(1);
        }

        timeSlotRepository.saveAll(slots);
        List<Availability> saved = (List<Availability>)avbRepository.saveAll(avbs);

        return saved;
    }


    /* Reservation */

    public List<Reservation> getStudentReservations(Student student) {
            return rsvRepository.findByStudent_Id(student.getId());
    }

    public Availability getAvailabilityById(Long id) {
        Optional<Availability> opt = avbRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        } else {
            return opt.get().getReservation() == null ? opt.get(): null;
        }
    }

    public List<Reservation> createReservation(Student student, List<Long> availabilityIds) {
        List<Reservation> rsvToBeSaved = new ArrayList<>();
        List<Availability> avbToBeUpdated = new ArrayList<>();

        for (Long avbId: availabilityIds) {
            Availability avb = this.getAvailabilityById(avbId);

            if (avb != null) {
                Reservation newRsv = new Reservation(student, avb);
                avb.setReservation(newRsv);

                rsvToBeSaved.add(newRsv);
                avbToBeUpdated.add(avb);
            }
        }

        List<Reservation> saved = (List<Reservation>)rsvRepository.saveAll(rsvToBeSaved);
        avbRepository.saveAll(avbToBeUpdated);

        return saved;
    }
}
