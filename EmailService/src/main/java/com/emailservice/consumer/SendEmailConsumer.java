package com.emailservice.consumer;

import com.emailservice.dto.SendEmailDTO;
import com.emailservice.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailConsumer {
    private final ObjectMapper objectMapper;
    private EmailService emailService;

    public SendEmailConsumer(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    // This method should be called if we receive an Event for sending an email for SignUp
    // This consumer method should register/subscribe itself to the singup topic(channel)
    @KafkaListener(topics = "signup", groupId = "emailservice") // Group ID: Represent all the Instances of a Service
    public void handleSignUpEvent(String message) {
        // Convert the String message to a Java Object using ObjectMapper
        try { // Task we are executing upon receiving the event
            SendEmailDTO sendEmailDTO = objectMapper.readValue(message, SendEmailDTO.class);
            System.out.println("SimpleEmail Start");

            // Send Email in Java SMTP with TLS Authentication
            String smtpHostServer = "smtp.gmail.com";
            String emailID = "sample@gmail.com";

            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpHostServer); // SMTP Host
            props.put("mail.smtp.port", "587"); // TLS Port
            props.put("mail.smtp.auth", "true"); // Enable Authentication
            props.put("mail.smtp.starttls.enable", "true"); // Enable START TLS

            /* Authenticator auth = new Authenticator() {
                // Override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sendEmailDTO.getFrom(), "");
                }
            }; */

            // Create Authenticator object to pass in Session.getInstance argument
            String password = "";
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sendEmailDTO.getFrom(), password); // Sample EmailId and Password
                }
            };
            Session session = Session.getInstance(props, auth);
            EmailService.sendEmail(session, sendEmailDTO.getTo(), sendEmailDTO.getSubject(), sendEmailDTO.getBody()); // "SimpleEmail Testing Subject", "SimpleEmail Testing Body"

        } catch (Exception e) {
            System.out.println("Exception occurred while converting the message to SendEmailDTO");
            e.printStackTrace();
        }
    }
}