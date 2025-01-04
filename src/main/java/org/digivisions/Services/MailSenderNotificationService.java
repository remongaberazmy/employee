package org.digivisions.Services;

import org.digivisions.components.MailSenderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Profile("MailSender")
public class MailSenderNotificationService implements NotificationService{

	@Autowired
	private MailSenderClient mailSender;

	@Override
	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("remon.g.azmy@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		CompletableFuture.runAsync(() -> mailSender.send(message));
	}
}
