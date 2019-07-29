package com.travel.assistant.registrationevent;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.travel.assistant.entities.User;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {
	private String appUrl;
	private Locale locale;
	private User user;
	public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
		super(user);
		this.appUrl = appUrl;
		this.locale = locale;
		this.user = user;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
