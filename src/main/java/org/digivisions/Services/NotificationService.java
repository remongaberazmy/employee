package org.digivisions.Services;

public interface NotificationService {
	void sendEmail(String to, String subject, String body);
}
