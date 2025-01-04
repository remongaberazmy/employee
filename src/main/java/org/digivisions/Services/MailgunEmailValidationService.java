package org.digivisions.Services;

import org.digivisions.models.MailgunValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("emailValidationService")
@Profile("mailgun")
public class MailgunEmailValidationService implements EmailValidationService{

	@Value("${mailgun.api.key}")
	private String mailgunApiKey;

	@Value("${mailgun.base.url}")
	private String mailgunBaseUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean isValidEmail(String email) {
		String url = mailgunBaseUrl + "/v4/address/validate";

		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("api", mailgunApiKey);

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<MailgunValidationResponse> response = restTemplate.exchange(
				url + "?address=" + email,
				HttpMethod.GET,
				requestEntity,
				MailgunValidationResponse.class
		);

		// Check the response
		MailgunValidationResponse validationResponse = response.getBody();
		return validationResponse != null && validationResponse.is_valid();
	}
}
