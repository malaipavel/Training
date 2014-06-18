package com.training.cap2;

import java.awt.Color;

public abstract class GraphicObject {

	protected int x,y;
	protected Color color = Color.BLACK;
	
	public abstract void draw();

	public void setX (int x) {
		this.x = x;
	}

	public void setY (int y) {
		this.y = y;
	}

	public void setColor (Color color) {
		this.color = color;
	}
}
