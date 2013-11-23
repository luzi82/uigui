package com.luzi82.uigui;

public class UgJs {

	int uniqueStringInt = 1;

	public String uniqueString() {
		String ret = String.format("UG%08d", uniqueStringInt++);
		return ret;
	}

	public String cursorState(String cursorId) {
		// TODO dummy
		return "up";
	}

}
