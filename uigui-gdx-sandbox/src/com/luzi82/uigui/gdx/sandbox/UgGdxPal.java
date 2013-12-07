package com.luzi82.uigui.gdx.sandbox;

import java.io.IOException;
import java.io.Reader;

import com.badlogic.gdx.Gdx;
import com.luzi82.uigui.UgPal;

public class UgGdxPal extends UgPal {

	public void refreshValue() {
		mm = Gdx.graphics.getPpcX() / 10;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	@Override
	public Reader getReader(String resourceId) throws IOException {
		return Gdx.files.internal(resourceId).reader();
	}

	@Override
	public void log(String message) {
		System.err.println(message);
	}
	
}
