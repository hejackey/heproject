package com.bfb.drool.model;

import java.util.Date;

public class Applicant {
	public Date date;
	public boolean valid;
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
