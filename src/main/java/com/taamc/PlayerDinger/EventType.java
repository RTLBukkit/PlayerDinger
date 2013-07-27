package com.taamc.PlayerDinger;

public enum EventType {
	JOIN("join"), QUIT("quit");

	final String configVal;

	EventType(String s) {
		configVal = s;
	}

	public Object getConfig() {
		return configVal;
	}

}
