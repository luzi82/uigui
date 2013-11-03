package com.luzi82.uigui;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.commonjs.module.RequireBuilder;
import org.mozilla.javascript.commonjs.module.provider.ModuleSource;
import org.mozilla.javascript.commonjs.module.provider.ModuleSourceProvider;
import org.mozilla.javascript.commonjs.module.provider.SoftCachingModuleScriptProvider;

public class UgUi {

	UgPal pal;
	Object arg;

	Scriptable scope;
	Function rootFunc;
	Scriptable root;

	UgJs ugjs;

	public UgUi(String resourceId, UgPal pal, Object arg) throws IOException {
		this.pal = pal;
		this.arg = arg;

		ugjs = new UgJs();

		Context cx = Context.enter();

		scope = cx.initStandardObjects();

		RequireBuilder requireBuilder = new RequireBuilder();
		requireBuilder
				.setModuleScriptProvider(new SoftCachingModuleScriptProvider(
						new MSP()));
		Require require = requireBuilder.createRequire(cx, scope);
		require.install(scope);

		rootFunc = (Function) cx.evaluateReader(scope,
				pal.getReader(resourceId), resourceId, 0, null);
		root = (Scriptable) rootFunc.call(cx, scope, rootFunc, new Object[] {
				ugjs, pal, arg });

		Context.exit();
	}

	public UgUnit getUnit() {
		return null;
	}

	// public void paint(UgGraphics graphics) {
	// Context cx = Context.enter();
	// paintScriptable(cx, root, graphics);
	// Context.exit();
	// }
	//
	// private void paintScriptable(Context cx, Scriptable scriptable,
	// UgGraphics graphics) {
	// if (scriptable.has("clearColor", scriptable)) {
	// String colorString = (String) scriptable.get("clearColor",
	// scriptable);
	// int color = UgUtil.color(colorString);
	// graphics.clear(color);
	// }
	// if (scriptable.has("child", scriptable)) {
	// NativeArray sv = (NativeArray) scriptable.get("child", scriptable);
	// int svSize = sv.size();
	// for (int i = svSize - 1; i >= 0; --i) {
	// paintScriptable(cx, (Scriptable) sv.get(i), graphics);
	// }
	// }
	// }

	public class MSP implements ModuleSourceProvider {

		@Override
		public ModuleSource loadSource(String arg0, Scriptable arg1, Object arg2)
				throws IOException, URISyntaxException {
			Reader reader = pal.getReader(arg0);
			return new ModuleSource(reader, null, URI.create(arg0),
					URI.create(""), arg2);
		}

		@Override
		public ModuleSource loadSource(URI arg0, URI arg1, Object arg2)
				throws IOException, URISyntaxException {
			return null;
		}

	}

}
