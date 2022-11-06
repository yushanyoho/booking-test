package com.ysl.bookingtest.controller;

import com.ysl.bookingtest.model.Availability;
import com.ysl.bookingtest.model.CreateAvailabilityRequest;
import com.ysl.bookingtest.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/{username}/availability")
@RestController
public class AvailabilityController {
    @Autowired
    BookingService bookingService;

//    @Autowired
//    UserService userService;

    /**
     * List records of Availabilities.
     * @param startTime Optional, default to null
     * @param endTime Optional, default to null
     * @return If startTime and endTime are provided, return unreserved availabilities between.
     *         If either one is missing, return all unreserved availabilities.
     */
    @GetMapping("/view")
    public List<Availability> list(@RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime,
                                   @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endTime) {
        if (startTime != null && endTime != null) {
            return bookingService.getAvailabilities(startTime, endTime);
        } else {
            return bookingService.getAvailabilities();
        }
    }

    /**
     * Receive a create availability request and divide every 1 hour.
     * @param request A request object with LocalDateTime start and end.
     * @return Return the list of availability created from the provided time trunk.
     */
    @PostMapping("/post")
    public List<Availability> create(@RequestBody CreateAvailabilityRequest request) {

        return bookingService.createAvailabilities(request.getFrom(), request.getTo());
    }
}
