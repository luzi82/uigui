package com.luzi82.uiguitest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;
import org.mozilla.javascript.Context;

import com.luzi82.uigui.UgGraphicsRecorder;
import com.luzi82.uigui.UgPal;
import com.luzi82.uigui.UgUi;
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

		UgGraphicsRecorder graphicsRecorder = new UgGraphicsRecorder();
		ui.paint(graphicsRecorder);

		UgGraphicsRecorder.Record[] recordAry = graphicsRecorder.getRecordAry();
		UgGraphicsRecorder.Record record;

		int i = 0;
		UgGraphicsRecorder.Clear clear;

		record = recordAry[i++];
		Assert.assertTrue(record instanceof UgGraphicsRecorder.Clear);
		clear = (UgGraphicsRecorder.Clear) record;
		Assert.assertEquals(0xff7f7f7f, clear.color);

		Assert.assertEquals(i, recordAry.length);
	}

	@Test
	public void konata0() throws IOException {
		Pal pal = new Pal();
		pal.mm = 10;

		UgUi ui = new UgUi("res/konata0.js", pal, null);

		UgGraphicsRecorder graphicsRecorder = new UgGraphicsRecorder();
		ui.paint(graphicsRecorder);

		UgGraphicsRecorder.Record[] recordAry = graphicsRecorder.getRecordAry();
		UgGraphicsRecorder.Record record;

		int i = 0;
		UgGraphicsRecorder.Clear clear;
		UgGraphicsRecorder.Image image;

		record = recordAry[i++];
		Assert.assertTrue(record instanceof UgGraphicsRecorder.Clear);
		clear = (UgGraphicsRecorder.Clear) record;
		Assert.assertEquals(0xff7f7f7f, clear.color);

		record = recordAry[i++];
		Assert.assertTrue(record instanceof UgGraphicsRecorder.Image);
		image = (UgGraphicsRecorder.Image) record;
		Assert.assertEquals(19.0d, image.x0, 0.0001);
		Assert.assertEquals(19.0d, image.y0, 0.0001);
		Assert.assertEquals(81.0d, image.x1, 0.0001);
		Assert.assertEquals(81.0d, image.y1, 0.0001);
		Assert.assertEquals(0.0d, image.u0, 0.0001);
		Assert.assertEquals(0.0d, image.u1, 0.0001);
		Assert.assertEquals(1.0d, image.v0, 0.0001);
		Assert.assertEquals(1.0d, image.v1, 0.0001);
		Assert.assertEquals(0.618d, image.alpha, 0.001);
		Assert.assertEquals("res/menu_btn.png", image.img);

		Assert.assertEquals(i, recordAry.length);
	}

	class Pal extends UgPal {

		@Override
		public Reader getReader(String resourceId) throws IOException {
			return new FileReader(resourceId);
		}

	}

}
