package com.luzi82.uiguitest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.luzi82.uigui.UgEnvironment;
import com.luzi82.uigui.UgGraphicsRecorder;
import com.luzi82.uigui.UgUi;

public class Konata0 {

	@Test
	public void clear() {
		UgEnvironment env = new UgEnvironment();

		UgUi ui = UgUi.readFile("res/clear.js", env, null);

		UgGraphicsRecorder graphicsRecorder = new UgGraphicsRecorder();
		ui.paint(graphicsRecorder);

		UgGraphicsRecorder.Record[] recordAry = graphicsRecorder.getRecordAry();
		UgGraphicsRecorder.Record record;

		int i = 0;
		UgGraphicsRecorder.Clear clear;

		record = recordAry[i++];
		Assert.assertTrue(record instanceof UgGraphicsRecorder.Clear);
		clear = (UgGraphicsRecorder.Clear) record;
		Assert.assertEquals(0xff7f7f7f, clear.color.argb);

		Assert.assertEquals(i, recordAry.length);
	}

	@Test
	public void konata0() {
		UgEnvironment env = new UgEnvironment();
		env.mm = 10;

		UgUi ui = UgUi.readFile("res/konata0.js", env, null);

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
		Assert.assertEquals(0xff7f7f7f, clear.color.argb);

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

}
