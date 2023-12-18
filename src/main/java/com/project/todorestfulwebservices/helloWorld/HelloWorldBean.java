package com.project.todorestfulwebservices.helloWorld;

public class HelloWorldBean {

	
	private String message;
	
	public HelloWorldBean() {}
	
	public HelloWorldBean(String Message) {
		this.message=Message;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
	
	
}
