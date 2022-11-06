package com.ysl.bookingtest.controller;

import com.ysl.bookingtest.model.Availability;
import com.ysl.bookingtest.model.CreateReservationRequest;
import com.ysl.bookingtest.model.Reservation;
import com.ysl.bookingtest.model.Student;
import com.ysl.bookingtest.service.BookingService;
import com.ysl.bookingtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/{username}/reservation")
@RestController
public class ReservationController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;


    /**
     * List all reservations booked by the provided username.
     * @param username The username provided when booking.
     * @return A list of all reservations booked by the provided username.
     */
    @GetMapping("/view")
    public List<Reservation> list(@PathVariable("username") String username) {

        Student student = userService.getStudentByName(username);
        return bookingService.getStudentReservations(student);
    }

    /**
     * Create a series of reservations under the provided username on the provided availability id in the request.
     * @param username The username in all the reservations to be created.
     * @param request A studentName and a list of availability id's to be linked in a reservation
     * @return The saved (created) list of new reservations.
     */
    @PostMapping("/book")
    public List<Reservation> create(@PathVariable String username,
                                    @RequestBody CreateReservationRequest request) {

        Student student = userService.getStudentByName(username);

        return bookingService.createReservation(student, request.getAvailabilityIds());
    }
}
