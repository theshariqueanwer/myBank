package com.pack.bank.api.serviceimplementation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class DateTimeHoursService {

    public String calculateDateTime(Map<String, String> params, String apiVersion) {
        try {
            // Extract parameters from the map
            String actionType = params.getOrDefault("actionType", "post");
            String hoursParam = params.get("hours");
            String daysParam = params.get("days");
            String monthsParam = params.get("months");
            String yearsParam = params.get("years");
            String specificDate = params.get("specificDate"); // Optional for v1, v2, v3 and v4

            // Convert values to integers or default to 0
            int hours = parseInteger(hoursParam);
            int days = parseInteger(daysParam);
            int months = parseInteger(monthsParam);
            int years = parseInteger(yearsParam);

            // Get the base date
            LocalDateTime baseDate = (specificDate != null && !specificDate.isEmpty())
                    ? LocalDateTime.parse(specificDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                    : LocalDateTime.now();

            // Adjust based on actionType
            if ("pre".equalsIgnoreCase(actionType)) {
                baseDate = baseDate.minusHours(hours)
                        .minusDays(days)
                        .minusMonths(months)
                        .minusYears(years);
            } else if ("post".equalsIgnoreCase(actionType)) {
                baseDate = baseDate.plusHours(hours)
                        .plusDays(days)
                        .plusMonths(months)
                        .plusYears(years);
            } else {
                throw new IllegalArgumentException("Invalid actionType: " + actionType);
            }

            // Return formatted date and time
            return baseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input parameters: " + e.getMessage(), e);
        }
    }

    private int parseInteger(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric value: " + value);
        }
    }
}

