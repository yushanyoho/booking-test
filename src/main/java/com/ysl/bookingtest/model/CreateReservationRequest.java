package com.ysl.bookingtest.model;

import java.util.List;

public class CreateReservationRequest {

    String studentName;
    List<Long> availabilityIds;

    public String getStudentName() {
        return studentName;
    }

    public List<Long> getAvailabilityIds() {
        return availabilityIds;
    }
}
