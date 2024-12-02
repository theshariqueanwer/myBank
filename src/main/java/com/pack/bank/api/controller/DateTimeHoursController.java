package com.pack.bank.api.controller;

import com.pack.bank.api.serviceimplementation.DateTimeHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DateTimeHoursController {

    @Autowired
    private DateTimeHoursService dateTimeService;

    // API V1: Add hours
    @GetMapping("/v1/date-time")
    public String calculateV1(
            @RequestParam Map<String, String> params) {
        return dateTimeService.calculateDateTime(params, "v1");
    }

    // API V2: Add hours and days
    @GetMapping("/v2/date-time")
    public String calculateV2(
            @RequestParam Map<String, String> params) {
        return dateTimeService.calculateDateTime(params, "v2");
    }

    // API V3: Add hours, days, and months
    @GetMapping("/v3/date-time")
    public String calculateV3(
            @RequestParam Map<String, String> params) {
        return dateTimeService.calculateDateTime(params, "v3");
    }

    // API V4: Add hours, days, months, and years
    @GetMapping("/v4/date-time")
    public String calculateV4(
            @RequestParam Map<String, String> params) {
        return dateTimeService.calculateDateTime(params, "v4");
    }
}


//    // Method to calculate future or past date
//    private LocalDateTime calculateDateTime(LocalDateTime baseDate, Map<String, Integer> params, String action) {
//        int hours = params.getOrDefault("hours", 0);
//        int days = params.getOrDefault("days", 0);
//        int months = params.getOrDefault("months", 0);
//        int years = params.getOrDefault("years", 0);
//
//        // Perform calculations based on the action (pre or post)
//        if ("post".equalsIgnoreCase(action)) {
//            baseDate = baseDate.plusHours(hours).plusDays(days).plusMonths(months).plusYears(years);
//        } else if ("pre".equalsIgnoreCase(action)) {
//            baseDate = baseDate.minusHours(hours).minusDays(days).minusMonths(months).minusYears(years);
//        }
//        return baseDate;
//    }
//
//    // Common method for parsing date query parameter
//    private LocalDateTime parseDate(String dateStr) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//        return LocalDateTime.parse(dateStr, formatter);
//    }
//
//    // V1: Add hours to the current date or specific date
//    @GetMapping("/v1/date-and-time")
//    public String addHoursV1(@RequestParam Map<String, String> params) {
//        LocalDateTime baseDate = LocalDateTime.now(); // Default is current date and time
//
//        // If specific date is passed, parse it
//        if (params.containsKey("date")) {
//            baseDate = parseDate(params.get("date"));
//        }
//
//        int hours = Integer.parseInt(params.get("hours"));
//        String action = params.getOrDefault("action", "post");
//
//        baseDate = calculateDateTime(baseDate, Map.of("hours", hours), action);
//
//        return "Calculated DateTime: " + baseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
//    }
//
//    // V2: Add hours and days to the current date or specific date
//    @GetMapping("/v2/date-and-time")
//    public String addHoursAndDaysV2(@RequestParam Map<String, String> params) {
//        LocalDateTime baseDate = LocalDateTime.now();
//
//        if (params.containsKey("date")) {
//            baseDate = parseDate(params.get("date"));
//        }
//
//        int hours = Integer.parseInt(params.get("hours"));
//        int days = Integer.parseInt(params.get("days"));
//        String action = params.getOrDefault("action", "post");
//
//        baseDate = calculateDateTime(baseDate, Map.of("hours", hours, "days", days), action);
//
//        return "Calculated DateTime: " + baseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
//    }
//
//    // V3: Add hours, days, and months to the current date or specific date
//    @GetMapping("/v3/date-and-time")
//    public String addHoursDaysMonthsV3(@RequestParam Map<String, String> params) {
//        LocalDateTime baseDate = LocalDateTime.now();
//
//        if (params.containsKey("date")) {
//            baseDate = parseDate(params.get("date"));
//        }
//
//        int hours = Integer.parseInt(params.get("hours"));
//        int days = Integer.parseInt(params.get("days"));
//        int months = Integer.parseInt(params.get("months"));
//        String action = params.getOrDefault("action", "post");
//
//        baseDate = calculateDateTime(baseDate, Map.of("hours", hours, "days", days, "months", months), action);
//
//        return "Calculated DateTime: " + baseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
//    }
//
//    // V4: Add hours, days, months, and years to the current date or specific date
//    @GetMapping("/v4/date-and-time")
//    public String addAllDateTimeV4(@RequestParam Map<String, String> params) {
//        LocalDateTime baseDate = LocalDateTime.now();
//
//        if (params.containsKey("date")) {
//            baseDate = parseDate(params.get("date"));
//        }
//
//        int hours = Integer.parseInt(params.get("hours"));
//        int days = Integer.parseInt(params.get("days"));
//        int months = Integer.parseInt(params.get("months"));
//        int years = Integer.parseInt(params.get("years"));
//        String action = params.getOrDefault("action", "post");
//
//        baseDate = calculateDateTime(baseDate, Map.of("hours", hours, "days", days, "months", months, "years", years), action);
//
//        return "Calculated DateTime: " + baseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
//    }

