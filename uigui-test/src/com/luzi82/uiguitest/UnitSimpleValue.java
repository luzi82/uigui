package com.luzi82.uiguitest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;

import com.luzi82.uigui.UgPal;
import com.luzi82.uigui.UgUi;
import com.luzi82.uigui.UgUnit;

public class UnitSimpleValue {

	@Test
	public void unitSimpleValue() throws IOException {
		UgUi ui = new UgUi("res/unitSimpleValue.js", new Pal(), null);

		UgUnit unit = ui.getUnit();
		Assert.assertEquals(0x11223344, (int) unit.clearColor);
		Assert.assertEquals(1, unit.dx, 0.001f);
		Assert.assertEquals(2, unit.dy, 0.001f);
		Assert.assertEquals(3, unit.x0, 0.001f);
		Assert.assertEquals(4, unit.x1, 0.001f);
		Assert.assertEquals(5, unit.y0, 0.001f);
		Assert.assertEquals(6, unit.y1, 0.001f);
		Assert.assertEquals(7, unit.u0, 0.001f);
		Assert.assertEquals(8, unit.u1, 0.001f);
		Assert.assertEquals(9, unit.v0, 0.001f);
		Assert.assertEquals(10, unit.v1, 0.001f);
		Assert.assertEquals(true, unit.cursor);
		Assert.assertEquals("test_cur", unit.cursorId);
		Assert.assertEquals(3, unit.refresh.length);
		Assert.assertEquals("ref_a", unit.refresh[0]);
		Assert.assertEquals("ref_b", unit.refresh[1]);
		Assert.assertEquals("ref_c", unit.refresh[2]);
		Assert.assertEquals(0.5, unit.alpha, 0.001f);
		Assert.assertEquals(3, unit.preloadImg.length);
		Assert.assertEquals("img_a", unit.preloadImg[0]);
		Assert.assertEquals("img_b", unit.preloadImg[1]);
		Assert.assertEquals("img_c", unit.preloadImg[2]);
		Assert.assertEquals("imgg", unit.img);
	}

	class Pal extends UgPal {

		@Override
		public Reader getReader(String resourceId) throws IOException {
			return new FileReader(resourceId);
		}

	}

}
