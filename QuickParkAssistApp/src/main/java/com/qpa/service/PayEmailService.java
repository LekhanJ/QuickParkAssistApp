package com.qpa.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class PayEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReceipt(String to, String orderId, String bookId, Double amount) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        
        File logoFile = new File("src/main/resources/static/download (5).png"); // Update path accordingly
        String contentId = "companyLogo";

        // HTML Email
        String emailContent = "<div style='max-width: 500px; margin: auto; font-family: Arial, sans-serif; padding: 20px; border-radius: 10px; background: #f9f9f9; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>"
                + "<div style='text-align: center;'>"
                + "<img src='cid:" + contentId + "' alt='Company Logo' style='max-width: 150px; margin-bottom: 10px;'>"
                + "<h2 style='color: #4CAF50;'>✅ Payment Receipt</h2>"
                + "</div>"
                + "<div style='background: white; padding: 15px; border-radius: 10px;'>"
                + "<p><strong>Book ID:</strong> " + bookId + "</p>"
                + "<p><strong>Order ID:</strong> " + orderId + "</p>"
                + "<p><strong>Amount Paid:</strong> <span style='color: #4CAF50;'>₹" + amount + "</span></p>"
                + "<hr style='border: 0; height: 1px; background: #ddd;'>"
                + "<p style='text-align: center; color: gray; font-size: 12px;'>Thank you for your purchase! 😄💖</p>"
                + "</div>"
                + "</div>";

        helper.setTo(to);
        helper.setSubject("✅ Payment Receipt - Order " + orderId);
        helper.setText(emailContent, true);

        // Attach the logo as an inline image
        if (logoFile.exists()) {
            helper.addInline(contentId, logoFile);
        }

        mailSender.send(message);
    }
}
