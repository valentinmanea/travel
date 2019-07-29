package com.travel.assistant.registrationevent;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.travel.assistant.entities.User;
import com.travel.assistant.services.UserService;

@Component
public class RegistrationListener implements
  ApplicationListener<OnRegistrationCompleteEvent> {
  
    @Autowired
    private UserService service;
  
    @Autowired
    private JavaMailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
    	System.out.println("onApplicationEvent");
        this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);
         
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = "http://localhost:4200/register-confirmation/" + token;
         
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("Buna ziua, "
        		+ "Pentru activarea contului dumneavoastra de utilizator, accesati link-ul: " + confirmationUrl);
        mailSender.send(email);
    }
}