package com.pack.bank.api.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api")
public class DateTimeController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // V1 API: Add days to the current date
    @GetMapping("/v1/date")
    public ResponseEntity<String> calculateFutureDateV1(@RequestParam int days) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(days);
        return ResponseEntity.ok("Future Date (V1): " + futureDate.format(FORMATTER));
    }

    // V2 API: Add months and days to the current date
    @GetMapping("/v2/date")
    public ResponseEntity<String> calculateFutureDateV2(@RequestParam int months, @RequestParam int days) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusMonths(months).plusDays(days);
        return ResponseEntity.ok("Future Date (V2): " + futureDate.format(FORMATTER));
    }

    // V3 API: Add days to a specific date in dd-MM-yyyy format
    @GetMapping("/v3/date")
    public ResponseEntity<String> calculateFutureDateV3(@RequestParam String specificDate, @RequestParam int days) {
        try {
            LocalDate startDate = LocalDate.parse(specificDate, FORMATTER);
            LocalDate futureDate = startDate.plusDays(days);
            return ResponseEntity.ok("Future Date (V3): " + futureDate.format(FORMATTER));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use 'dd-MM-yyyy'.");
        }
    }

    // V4 API: Add months and days to a specific date in dd-MM-yyyy format
    @GetMapping("/v4/date")
    public ResponseEntity<String> calculateFutureDateV4(
            @RequestParam String specificDate,
            @RequestParam int months,
            @RequestParam int days) {
        try {
            LocalDate startDate = LocalDate.parse(specificDate, FORMATTER);
            LocalDate futureDate = startDate.plusMonths(months).plusDays(days);
            return ResponseEntity.ok("Future Date (V4): " + futureDate.format(FORMATTER));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use 'dd-MM-yyyy'.");
        }
    }
}

