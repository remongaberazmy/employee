package org.digivisions.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter
public class MailgunValidationResponse implements Serializable {
	private boolean is_valid;
}
