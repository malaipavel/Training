package com.training.cap2;

public class Cerc extends GraphicObject{

	private static int instanceCount;
	static {
		instanceCount = 0;
	}
	
	private int raza;
	
	public Cerc(){
		instanceCount++;
	}
	
	public Cerc(int x, int y, int raza){
		this();
		this.x = x;
		this.y = y;
		this.raza = raza;
		
		//does not have the wanted effect, due to the fact that parameters are passed only by value
		
		/* x = this.x;
			y = this.y;*/
	}
	public void setAttributes(int x, int y, int raza){
		this.x = x;
		this.y = y;
		this.raza = raza;
	}
	@Override
	public String toString () {
		return "x = " + x + " y = " + y + " raza = " + raza;
	}
	
	public static void printInstanceCounter(){
		System.out.println("Au fost instantiate " + instanceCount + " cercuri");
	}


	public void metoda(int i, Cerc c){
		
		i++;
		
		c.setAttributes(100, 100, 100);
		
		c = new Cerc();
		c.setAttributes(200, 200, 200);
	}

	@Override
	public void draw () {
	}
}
