package com.training.cap2;

import java.awt.Point;
import java.util.ArrayList;

	//2.1

public class Rectangle extends GraphicObject{
	private float lungime;
	private float latime;
	private Point origine;
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public transient static final float PI = 3.14f; //class var, cannot be changed, does not get serialized

	//constructors
	public Rectangle(){
		//
	}
	public Rectangle(float lungime, float latime){
		this.lungime = lungime;
		this.latime = latime;
	}
	public Rectangle(float lungime, float latime, Point origine){
		this(lungime, latime);
		
		//copiere defensiva
		this.origine = (Point)origine.clone();
	}
	
	public void addPoints(Point...points){
		for(Point point:points){
			this.points.add(point);
		}
	}

	
	//setters and getters
	public float getLungime () {
		return lungime;
	}
	public void setLungime (float lungime) {
		this.lungime = lungime;
	}
	public float getLatime () {
		return latime;
	}
	public void setLatime (float latime) {
		this.latime = latime;
	}
	public Point getOrigine () {
		return origine;
	}
	public void setOrigine (Point origine) {
		this.origine = origine;
	}
	@Override
	public void draw () {
	}
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(latime);
		result = prime * result + Float.floatToIntBits(lungime);
		result = prime * result + ((origine == null) ? 0 : origine.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		return result;
	}
	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Rectangle other = (Rectangle)obj;
		if (Float.floatToIntBits(latime) != Float.floatToIntBits(other.latime)) return false;
		if (Float.floatToIntBits(lungime) != Float.floatToIntBits(other.lungime)) return false;
		if (origine == null) {
			if (other.origine != null) return false;
		} else if (!origine.equals(other.origine)) return false;
		if (points == null) {
			if (other.points != null) return false;
		} else if (!points.equals(other.points)) return false;
		return true;
	}
	
	@Override
		protected void finalize () throws Throwable {
			super.finalize();
			System.out.println("Apelata inainte de a elibera obiectul din memorie");
		}
}
