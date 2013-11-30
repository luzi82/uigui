package com.luzi82.uigui.gdx.sandbox;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.luzi82.uigui.UgPal;
import com.luzi82.uigui.UgUi;
import com.luzi82.uigui.UgUnit;

public class SandboxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	// private Texture texture;
	// private Sprite sprite;
	private UgGdxPal pal;
	private UgUi ui;
	private UgUnit unit;
	private HashMap<String, Texture> imgMap;
	private LinkedList<Sprite> spriteList;

	public SandboxGame(UgGdxPal pal) {
		this.pal = pal;
	}

	@Override
	public void create() {
		pal.refreshValue();
		try {
			ui = new UgUi("data/sb_centerlogo.js", pal, null);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error(e);
		}

		imgMap = new HashMap<String, Texture>();
		List<String> preloadImgList = ui.getPreloadImgList();
		for (String preloadImg : preloadImgList) {
			Texture texture = new Texture(Gdx.files.internal(preloadImg));
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			imgMap.put(preloadImg, texture);
		}

		unit = ui.getUnit();
		spriteList = new LinkedList<Sprite>();
		fillSprite(unit);

		camera = new OrthographicCamera(pal.width, pal.height);
		camera.translate(pal.width / 2, pal.height / 2);
		camera.update();
		batch = new SpriteBatch();

		// TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		//
		// sprite = new Sprite(region);
		// sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		// sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		// sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

	}

	@Override
	public void dispose() {
		batch.dispose();
		// texture.dispose();
		for (Texture t : imgMap.values()) {
			t.dispose();
		}
		imgMap.clear();
	}

	@Override
	public void render() {
		// Gdx.gl.glClearColor(1, 1, 1, 1);
		if (unit.clearColor != null) {
			float[] argb = toArgb(unit.clearColor);
			Gdx.gl.glClearColor(argb[1], argb[2], argb[3], argb[0]);
		}
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// sprite.draw(batch);
		for (Sprite sprite : spriteList) {
			sprite.draw(batch);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	private static float[] toArgb(int argb) {
		float[] ret = new float[4];
		int v;
		v = (argb >> 24) & 0xff;
		ret[0] = v / 255f;
		v = (argb >> 16) & 0xff;
		ret[1] = v / 255f;
		v = (argb >> 8) & 0xff;
		ret[2] = v / 255f;
		v = argb & 0xff;
		ret[3] = v / 255f;
		return ret;
	}

	private void fillSprite(UgUnit unit) {
		if (unit.img != null) {
			TextureRegion tr=new TextureRegion(imgMap.get(unit.img));
			Sprite sprite = new Sprite(tr);
			sprite.setBounds(unit.x0, unit.y0, unit.x1 - unit.x0, unit.y1
					- unit.y0);
			spriteList.addFirst(sprite);
		}
		if (unit.child != null) {
			for (UgUnit u : unit.child) {
				fillSprite(u);
			}
		}
	}

}
