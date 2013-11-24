package com.luzi82.uigui;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaObject;
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
	UgUnit rootUnit;

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
		if (rootUnit != null)
			return rootUnit;
		Context cx = Context.enter();
		rootUnit = toUnit(cx, root);
		Context.exit();
		return rootUnit;
	}

	private UgUnit toUnit(Context cx, Scriptable scriptable) {
		if (scriptable == null)
			return null;
		UgUnit ret = new UgUnit();
		if (scriptable.has("enable", scriptable)) {
			ret.enable = (boolean) unwrap(scriptable, "enable");
		} else {
			ret.enable = true;
		}
		// when not enabled, no need to output other value
		if (!ret.enable)
			return ret;
		if (scriptable.has("clearColor", scriptable)) {
			String colorString = (String) unwrap(scriptable, "clearColor");
			ret.clearColor = UgUtil.color(colorString);
		}
		if (scriptable.has("dx", scriptable)) {
			ret.dx = ((Number) unwrap(scriptable, "dx")).floatValue();
		}
		if (scriptable.has("dy", scriptable)) {
			ret.dy = ((Number) unwrap(scriptable, "dy")).floatValue();
		}
		if (scriptable.has("x0", scriptable)) {
			ret.x0 = ((Number) unwrap(scriptable, "x0")).floatValue();
		}
		if (scriptable.has("x1", scriptable)) {
			ret.x1 = ((Number) unwrap(scriptable, "x1")).floatValue();
		}
		if (scriptable.has("y0", scriptable)) {
			ret.y0 = ((Number) unwrap(scriptable, "y0")).floatValue();
		}
		if (scriptable.has("y1", scriptable)) {
			ret.y1 = ((Number) unwrap(scriptable, "y1")).floatValue();
		}
		if (scriptable.has("u0", scriptable)) {
			ret.u0 = ((Number) unwrap(scriptable, "u0")).floatValue();
		}
		if (scriptable.has("u1", scriptable)) {
			ret.u1 = ((Number) unwrap(scriptable, "u1")).floatValue();
		}
		if (scriptable.has("v0", scriptable)) {
			ret.v0 = ((Number) unwrap(scriptable, "v0")).floatValue();
		}
		if (scriptable.has("v1", scriptable)) {
			ret.v1 = ((Number) unwrap(scriptable, "v1")).floatValue();
		}
		if (scriptable.has("cursor", scriptable)) {
			ret.cursor = (boolean) unwrap(scriptable, "cursor");
		} else {
			ret.cursor = false;
		}
		if (scriptable.has("cursorId", scriptable)) {
			ret.cursorId = (String) unwrap(scriptable, "cursorId");
		}
		if (scriptable.has("refresh", scriptable)) {
			NativeArray na = (NativeArray) scriptable
					.get("refresh", scriptable);
			int naSize = na.size();
			ret.refresh = new String[naSize];
			for (int i = 0; i < naSize; ++i) {
				ret.refresh[i] = (String) na.get(i);
			}
		}
		if (scriptable.has("alpha", scriptable)) {
			ret.alpha = ((Number) unwrap(scriptable, "alpha")).floatValue();
		}
		if (scriptable.has("child", scriptable)) {
			NativeArray sv = (NativeArray) scriptable.get("child", scriptable);
			int svSize = sv.size();
			ret.child = new UgUnit[svSize];
			for (int i = 0; i < svSize; ++i) {
				ret.child[i] = toUnit(cx, (Scriptable) sv.get(i));
			}
		}
		return ret;
	}

	public static Object unwrap(Object obj) {
		if (obj == null)
			return null;
		if (!(obj instanceof NativeJavaObject)) {
			return obj;
		}
		NativeJavaObject njo = (NativeJavaObject) obj;
		return unwrap(njo.unwrap());
	}

	public static Object unwrap(Scriptable scriptable, String name) {
		return unwrap(scriptable.get(name, scriptable));
	}

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
