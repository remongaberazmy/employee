package org.digivisions.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailSenderClient {

	@Autowired
	private JavaMailSender mailSender;

	@Retryable(maxAttemptsExpression = "${mail-sender.retry.max-attempts:3}",
			backoff = @Backoff(delayExpression = "${mail-sender.retry.delay:1000}",
					maxDelayExpression = "${mail-sender.retry.max-delay:5000}",
					multiplierExpression = "${mail-sender.retry.multiplier:2}"))
	public void send(SimpleMailMessage message){
		mailSender.send(message);
	}

	@Recover
	public void recover(Throwable e, SimpleMailMessage message){
		log.error("Failed to send email due to", e);
	}
}
