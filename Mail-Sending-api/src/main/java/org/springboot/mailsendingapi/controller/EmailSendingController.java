package org.springboot.mailsendingapi.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling email sending requests.
 */
@RestController
@RequestMapping(value = "/email/api")
public class EmailSendingController {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Endpoint for sending an email.
     *
     * @param mail the recipient's email address
     * @return a confirmation message indicating the email has been sent or an error message if it failed
     */
    @PostMapping("/send")
    public String sendMail(@RequestParam String mail) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo(mail);
            mimeMessageHelper.setSubject("To Test The Email Sending API");
            mimeMessageHelper.setText("Dear User, We are sending this email because the API test was successful!");

            javaMailSender.send(message);
            return "Mail has been sent successfully to: " + mail;

        } catch (MailException | MessagingException e) {
            e.printStackTrace();
            return "Failed to send email to: " + mail + " due to: " + e.getMessage();
        }
    }
}
