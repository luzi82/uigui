package com.luzi82.uigui;


public class UgGraphicsRecorder implements UgGraphics {

	public class Image extends Record {

		public float x0;
		public float x1;
		public float y0;
		public float y1;
		public float u0;
		public float u1;
		public float v0;
		public float v1;
		public float alpha;
		public String img;

	}

	public class Clear extends Record {

		public Color color;

	}

	public class Record {

	}

	public Record[] getRecordAry() {
		return null;
	}

}
