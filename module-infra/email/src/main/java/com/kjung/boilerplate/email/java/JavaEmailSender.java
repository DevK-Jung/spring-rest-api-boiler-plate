package com.kjung.boilerplate.email.java;

import com.kjung.boilerplate.email.MailSender;
import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Component
public class JavaEmailSender implements MailSender {
    private final String fromAddress;

    private final JavaMailSender javaMailSender;

    public JavaEmailSender(@Value("${spring.mail.address}") String fromAddress,
                           JavaMailSender javaMailSender) {

        if (StringUtils.isBlank(fromAddress))
            throw new IllegalStateException("The 'spring.mail.address' property is not configured. Please check your application settings.");

        if (javaMailSender == null)
            throw new IllegalStateException("JavaMailSender bean is missing. Please ensure 'spring.mail.*' properties are properly configured.");

        this.fromAddress = fromAddress;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String subject, String content, String toAddress, String[] cc, String[] bcc, boolean isHtml) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setSubject(encodeSubject(subject));
            helper.setText(content, isHtml);
            helper.setFrom(new InternetAddress(fromAddress, fromAddress, StandardCharsets.UTF_8.name()));
            helper.setTo(toAddress);

            Optional.ofNullable(cc).filter(arr -> arr.length > 0).ifPresent(addresses -> {
                try {
                    helper.setCc(addresses);
                } catch (MessagingException e) {
                    log.warn(">>> Failed to set CC addresses: {}", e.getMessage());
                }
            });

            Optional.ofNullable(bcc).filter(arr -> arr.length > 0).ifPresent(addresses -> {
                try {
                    helper.setBcc(addresses);
                } catch (MessagingException e) {
                    log.warn(">>> Failed to set BCC addresses: {}", e.getMessage());
                }
            });

            javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error(">>> Failed to send email: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while sending the email.", e);
        }
    }

    private String encodeSubject(String subject) throws UnsupportedEncodingException {
        return MimeUtility.encodeText(subject, StandardCharsets.UTF_8.name(), "B");
    }
}
