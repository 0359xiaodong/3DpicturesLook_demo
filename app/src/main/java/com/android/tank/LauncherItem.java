


package com.android.tank;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;

public class LauncherItem {
	Drawable icon;
	String name;
	ComponentName component;

	LauncherItem(Drawable d, String s, ComponentName cn) {
		icon = d;
		name = s;
		component = cn;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ComponentName getComponent() {
		return component;
	}

	public void setComponent(ComponentName component) {
		this.component = component;
	}
	
};