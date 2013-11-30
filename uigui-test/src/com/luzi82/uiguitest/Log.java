package com.luzi82.uiguitest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;

import com.luzi82.uigui.UgPal;
import com.luzi82.uigui.UgUi;
import com.luzi82.uigui.UgUnit;

public class Log {

	@Test
	public void unitSimpleValue() throws IOException {
		Pal p = new Pal();
		UgUi ui = new UgUi("res/log.js", p, null);

		ui.getUnit();

		Assert.assertEquals("hello log", p.lastLogMessage);

	}

	class Pal extends UgPal {

		@Override
		public Reader getReader(String resourceId) throws IOException {
			return new FileReader(resourceId);
		}

		public String lastLogMessage;

		@Override
		public void log(String message) {
			lastLogMessage = message;
		}

	}

}
