package com.nabin.ed.services.impl;

import com.nabin.ed.pojos.MailPOJO;
import com.nabin.ed.services.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Narendra
 * @version 1.0
 * @since 2023-01-12
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final ThreadPoolTaskExecutor executor;

    @Override
    public String sendMail(MailPOJO request) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setSubject(request.getSubject());
        mimeMessageHelper.setText(request.getContent());
        mimeMessageHelper.setTo(request.getTo());

        //new thread is created by overriding the Runnable interface
        //this thread runs in background asynchronously
        //since new thread is created, the control flow of our program moves to next line returning the response
        //while the mail sending process runs in background
        executor.execute(() -> {
            try {
                mailSender.send(mimeMessage);
                log.info("Mail sending successful to: {}", request.getTo());
            } catch (Exception exception) {
                log.error("Mail sending failed to: {}", request.getTo());
                log.error("Mail can't be sent because: {}", exception.getMessage());
            }
        });

        return "Mail has been dispatched";
    }
}
