package com.training.cap2;

public class Complex {

	private double a;
	private double b;
	
	public Complex(double a, double b){
		this.a = a;
		this.b = b;
	}
	
	public Complex(){
		this(1, 0);
	}
	
	@Override
	protected Object clone (){
		return new Complex(a,b);
	}
	
	@Override
	public String toString () {
		String semn = (b > 0 ? "+" : "-");
		return a + semn + b + "i";
	}
	
	public Complex Aduna(Complex c){
		Complex suma = new Complex(0, 0);
		suma.a = this.a + c.a;
		suma.b = this.b + c.b;
		return suma;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(a);
		result = prime * result + (int)(temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int)(temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Complex other = (Complex)obj;
		if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a)) return false;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b)) return false;
		return true;
	}
}
