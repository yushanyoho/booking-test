package com.ysl.bookingtest.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Student")
@Data
@NoArgsConstructor
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    Long id;

    @NotNull
    String username;

    @JsonBackReference
    @OneToMany(mappedBy = "student")
    List<Reservation> reservations;

    public Student(String username) {
        this.username = username;
    }
}
