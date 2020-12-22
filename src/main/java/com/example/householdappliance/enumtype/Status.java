package com.example.householdappliance.enumtype;


public enum Status {
	OLD("OLD"), NEW("NEW"), SOLD("SOLD");
	
	private final String value;
	
	private Status(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static Status fromString(String value) {
		for(Status s: Status.values()) {
			if(s.value.equals(value)) {
				return s;
			}
		}
		return null;
	}
}
