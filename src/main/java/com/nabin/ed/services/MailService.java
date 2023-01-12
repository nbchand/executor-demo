package com.nabin.ed.services;

import com.nabin.ed.pojos.MailPOJO;

import javax.mail.MessagingException;

/**
 * @author Narendra
 * @version 1.0
 * @since 2023-01-12
 */
public interface MailService {
    String sendMail(MailPOJO request) throws MessagingException;
}
