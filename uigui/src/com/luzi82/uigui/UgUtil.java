package com.luzi82.uigui;

public class UgUtil {

	static public int color(String value) {
		if (!value.startsWith("#")) {
			return 0;
		}
		if (value.length() != 9) {
			return 0;
		}
		return (int) (Long.decode("0x"+value.substring(1)) & 0xffffffff);
	}

}
