package com.lucas.springbootessentials.exception;

public class ResourceNotFoundDetails extends ErrorDetail{

	public ResourceNotFoundDetails(String title, int status, String detail, long timestamp, String developerMessage) {
		super();
		setTitle(title);
		setStatus(status);
		setDetail(detail);
		setTimestamp(timestamp);
		setDeveloperMessage(developerMessage);
	}
}
