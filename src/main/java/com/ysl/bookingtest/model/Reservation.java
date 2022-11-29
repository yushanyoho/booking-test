package com.ysl.bookingtest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

// Database will be created for each model with @Entity
@Entity(name = "Reservation")
@Data
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    Long id;

    @JsonManagedReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @JsonManagedReference
    @NotNull
    @OneToOne(mappedBy = "reservation")
    Availability availability; // the slot booked by this reservation

    public Reservation(Student student, Availability avb) {
        this.student = student;
        this.availability = avb;
    }

}
