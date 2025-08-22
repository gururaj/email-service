package com.workfall.email.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workfall.email.service.CsvReaderService;
import com.workfall.email.service.EmailService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/csv")
@AllArgsConstructor
public class CsvEmailController {

    private CsvReaderService csvReaderService;

    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmailsFromCsv() {
        var rows = csvReaderService.readCsv();

        for (String[] row : rows) {
            String name = row[0];
            String email = row[1];
            String subject = "Email Testing from Spring Boot";
            String message = "Hi " + name + ",\n\nThis is a test email from Spring Boot CSV Reader.";
            emailService.sendEmail(email, subject, message);
        }

        return "Emails sent successfully to " + rows.size() + " recipients.";
    }
}
