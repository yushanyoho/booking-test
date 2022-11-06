package com.ysl.bookingtest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "Availability")
@Data
@NoArgsConstructor
//@Table(indexes = {
//        @Index(
//                name = "index_instructor_start_end",
//                columnList = "start, end",
//                unique = true
//        )
//})
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    Long id;

    // Instructor instructor;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "time_slot_id")
    TimeSlot timeSlot;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    public Availability(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

//    public Availability(LocalDateTime start, LocalDateTime end) {
//        this.from = start;
//        this.to = end;
//    }
}
