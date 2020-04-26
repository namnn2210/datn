package ginp14.ngongocnam.datn.service;

import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String email, String subject, String templateFileName, Context context) throws MessagingException;
}
