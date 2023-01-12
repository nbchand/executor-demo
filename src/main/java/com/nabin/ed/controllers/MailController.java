package com.nabin.ed.controllers;

import com.nabin.ed.pojos.MailPOJO;
import com.nabin.ed.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * @author Narendra
 * @version 1.0
 * @since 2023-01-12
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping
    public ResponseEntity<?> sendMail(@RequestBody MailPOJO request) throws MessagingException {
        return new ResponseEntity<>(mailService.sendMail(request), HttpStatus.OK);
    }
}
