package com.luzi82.uigui;

import java.util.LinkedList;

public class UgGraphicsRecorder implements UgGraphics {

	LinkedList<Record> recordList = new LinkedList<Record>();

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

		public int color;

	}

	@Override
	public void clear(int color) {
		Clear r = new Clear();
		r.color = color;
		recordList.add(r);
	}

	public class Record {

	}

	public Record[] getRecordAry() {
		return recordList.toArray(new Record[0]);
	}

}
