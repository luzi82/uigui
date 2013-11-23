package com.luzi82.uiguitest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;

import com.luzi82.uigui.UgPal;
import com.luzi82.uigui.UgUi;
import com.luzi82.uigui.UgUnit;
import com.luzi82.uigui.UgUtil;

public class Konata0 {

	@Test
	public void color() {
		Assert.assertEquals(0x00000000, UgUtil.color("#00000000"));
		Assert.assertEquals(0x01020304, UgUtil.color("#01020304"));
		Assert.assertEquals(0xffffffff, UgUtil.color("#ffffffff"));
	}

	@Test
	public void clear() throws IOException {
		UgUi ui = new UgUi("res/clear.js", new Pal(), null);

		UgUnit unit = ui.getUnit();
		Assert.assertEquals(0xff7f7f7f, (int) unit.clearColor);
	}

	@Test
	public void translate() throws IOException {
		UgUi ui = new UgUi("res/translate.js", new Pal(), null);

		UgUnit unit = ui.getUnit();
		Assert.assertEquals(1, unit.dx, 0.001f);
		Assert.assertEquals(2, unit.dy, 0.001f);
	}

	@Test
	public void child() throws IOException {
		UgUi ui = new UgUi("res/child.js", new Pal(), null);

		UgUnit unit = ui.getUnit();

		Assert.assertEquals(3, unit.child.length);
		Assert.assertEquals(1, unit.child[0].dx, 0.001f);
		Assert.assertEquals(2, unit.child[1].dx, 0.001f);
		Assert.assertEquals(3, unit.child[2].dx, 0.001f);
	}

	@Test
	public void enable() throws IOException {
		UgUi ui = new UgUi("res/enable.js", new Pal(), null);

		UgUnit unit = ui.getUnit();

		Assert.assertEquals(3, unit.child.length);
		Assert.assertEquals(true, unit.child[0].enable);
		Assert.assertEquals(true, unit.child[1].enable);
		Assert.assertEquals(false, unit.child[2].enable);
	}

	@Test
	public void konata0() throws IOException {
		Pal pal = new Pal();
		pal.mm = 10;

		UgUi ui = new UgUi("res/konata0.js", pal, null);

		UgUnit unit = ui.getUnit();

		Assert.assertEquals(0xff7f7f7f, (int) unit.clearColor);
		Assert.assertEquals(2, unit.child.length);

		Assert.assertEquals(true, unit.child[0].enable);
		Assert.assertEquals(1, unit.child[0].child.length);
		
		Assert.assertEquals(2, unit.child[0].child[0].child.length);

		Assert.assertEquals(0d, unit.child[0].child[0].child[0].x0, 0.0001);
		Assert.assertEquals(0d, unit.child[0].child[0].child[0].x1, 0.0001);
		Assert.assertEquals(100d, unit.child[0].child[0].child[0].y0, 0.0001);
		Assert.assertEquals(100d, unit.child[0].child[0].child[0].y1, 0.0001);
		Assert.assertEquals(true, unit.child[0].child[0].child[0].cursor);
		Assert.assertNotNull(unit.child[0].child[0].child[0].cursorId);

		Assert.assertEquals(19d, unit.child[0].child[0].child[1].x0, 0.0001);
		Assert.assertEquals(19d, unit.child[0].child[0].child[1].x1, 0.0001);
		Assert.assertEquals(81d, unit.child[0].child[0].child[1].y0, 0.0001);
		Assert.assertEquals(81d, unit.child[0].child[0].child[1].y1, 0.0001);
		Assert.assertEquals(0d, unit.child[0].child[0].child[1].u0, 0.0001);
		Assert.assertEquals(1d, unit.child[0].child[0].child[1].u1, 0.0001);
		Assert.assertEquals(0d, unit.child[0].child[0].child[1].v0, 0.0001);
		Assert.assertEquals(1d, unit.child[0].child[0].child[1].v1, 0.0001);
		Assert.assertEquals(1, unit.child[0].child[0].child[1].refresh.length);
		Assert.assertEquals(unit.child[0].child[0].child[0].cursorId,
				unit.child[0].child[0].child[1].refresh[0]);
		Assert.assertEquals(0.618d, unit.child[0].child[0].child[1].alpha, 0.001);

		Assert.assertEquals(false, unit.child[1].enable);
	}

	class Pal extends UgPal {

		@Override
		public Reader getReader(String resourceId) throws IOException {
			return new FileReader(resourceId);
		}

	}

}
