package com.ysl.bookingtest;

import com.ysl.bookingtest.model.Availability;
import com.ysl.bookingtest.model.Reservation;
import com.ysl.bookingtest.model.Student;
import com.ysl.bookingtest.service.BookingService;
import com.ysl.bookingtest.service.UserService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookingTestApplicationTests {
	@Autowired
	BookingService bookingService;

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateAvailability() {
		// test saving data
		LocalDateTime start = LocalDateTime.of(2022, 10, 24, 10, 0);
		LocalDateTime end = LocalDateTime.of(2022, 10, 24, 16, 0);
		List<Availability> saved = bookingService.createAvailabilities(start, end);
		Assertions.assertEquals(6, saved.size());
	}

	@Test
	public void testGetAvailability() {
		// TODO: insert data


		// test get all data
		List<Availability> allAvb = bookingService.getAvailabilities();
		Assertions.assertEquals(6, allAvb.size());

		// test get data between
		// test case 1
		LocalDateTime start = LocalDateTime.of(2022, 10, 24, 11, 0);
		LocalDateTime end = LocalDateTime.of(2022, 10, 24, 13, 0);
		List<Availability> avbBetween = bookingService.getAvailabilities(start, end);
		Assertions.assertEquals(2, avbBetween.size());

		// test case 2
		start = LocalDateTime.of(2022, 10, 24, 11, 0);
		end = LocalDateTime.of(2022, 10, 24, 16, 0);
		avbBetween = bookingService.getAvailabilities(start, end);
		Assertions.assertEquals(5, avbBetween.size());
	}

	@Test
	public void testReservation() {
		Student student = userService.createStudentAccount("yyy");
		Student searched = userService.getStudentByName("yyy");
		Assertions.assertNotNull(searched);
		Assertions.assertEquals(student.getId(), searched.getId());

		List<Availability> allAvb = bookingService.getAvailabilities();
		List<Long> availabilityIds = List.of(allAvb.get(0).getId(), allAvb.get(1).getId(), allAvb.get(2).getId());

		List<Reservation> created = bookingService.createReservation(student, availabilityIds);
		Assertions.assertEquals(3, created.size());

		availabilityIds = List.of(allAvb.get(2).getId(), allAvb.get(3).getId());
		created = bookingService.createReservation(student, availabilityIds);
		Assertions.assertEquals(1, created.size());

		List<Reservation> allRsv = bookingService.getStudentReservations(student);
		Assertions.assertEquals(4, allRsv.size());

		searched = userService.getStudentByName("yyy");
		System.out.println(searched.getId());
	}
}
