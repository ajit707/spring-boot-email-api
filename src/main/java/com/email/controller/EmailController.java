package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("welcome")
	public String welcome() {

		return "Hello, I am Ajit kumar sending email regaring email API";
	}

	@PostMapping("/sendemail")
	public ResponseEntity<?> sendMail(@RequestBody EmailRequest emailRequest) {
		boolean email = emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(),
				emailRequest.getMessage());

		if (email) {
			return ResponseEntity.ok("Email is send successfully...");
		} else {
			return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Email is not send successfully...");
		}

	}
}
