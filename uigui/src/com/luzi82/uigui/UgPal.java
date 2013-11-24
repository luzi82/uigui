package com.luzi82.uigui;

import java.io.IOException;
import java.io.Reader;

public abstract class UgPal {

	public float mm;

	public int width;

	public int height;

	public abstract Reader getReader(String resourceId) throws IOException;

}
