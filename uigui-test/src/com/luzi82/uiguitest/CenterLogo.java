package com.luzi82.uiguitest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;

import com.luzi82.uigui.UgPal;
import com.luzi82.uigui.UgUi;
import com.luzi82.uigui.UgUnit;

public class CenterLogo {

	@Test
	public void centerLogo() throws IOException {
		Pal pal = new Pal();
		pal.mm = 10;
		pal.width = 800;
		pal.height = 600;

		UgUi ui = new UgUi("res/sb_centerlogo.js", pal, null);

		UgUnit root = ui.getUnit();

		UgUnit unit = root.child[0];
		Assert.assertEquals(215f, unit.x0, 0.0001);
		Assert.assertEquals(585f, unit.x1, 0.0001);
		Assert.assertEquals(115f, unit.y0, 0.0001);
		Assert.assertEquals(485f, unit.y1, 0.0001);

	}

	class Pal extends UgPal {

		@Override
		public Reader getReader(String resourceId) throws IOException {
			return new FileReader(resourceId);
		}

	}

}
