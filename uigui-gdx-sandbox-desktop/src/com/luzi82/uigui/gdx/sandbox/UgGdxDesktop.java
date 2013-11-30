package com.luzi82.uigui.gdx.sandbox;

import java.io.IOException;
import java.io.Reader;

import com.badlogic.gdx.Gdx;
import com.luzi82.uigui.UgPal;

public class UgGdxDesktop {

	public static class Pal extends UgGdxPal {

		@Override
		public void refreshValue() {
			mm = Gdx.graphics.getPpcX() / 10;
			width = Gdx.graphics.getWidth();
			height = Gdx.graphics.getHeight();
		}

		@Override
		public Reader getReader(String resourceId) throws IOException {
			// // TODO Auto-generated method stub
			// return null;
			return Gdx.files.internal(resourceId).reader();
		}

	}

}
