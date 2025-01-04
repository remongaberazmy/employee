package org.digivisions.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter@Getter
public class BaseModel implements Serializable {
	private String replyMessage;
	private Integer replyCode;
}
