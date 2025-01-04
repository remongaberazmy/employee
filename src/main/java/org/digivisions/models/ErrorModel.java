package org.digivisions.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter@Setter
public class ErrorModel implements Serializable {
	private List<String> errorMessages;

	public void addErrorMessage(String errorMessage){
		if(errorMessages == null) errorMessages = new LinkedList<>();
		errorMessages.add(errorMessage);
	}
}
