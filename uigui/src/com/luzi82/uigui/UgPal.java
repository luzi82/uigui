package com.luzi82.uigui;

import java.io.IOException;
import java.io.Reader;

import org.mozilla.javascript.Context;

public abstract class UgPal {

	public float mm;

	public abstract Reader getReader(String resourceId) throws IOException;

}
