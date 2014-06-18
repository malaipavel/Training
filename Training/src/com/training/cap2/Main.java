package com.training.cap2;

import java.awt.Point;

public class Main {

	
	public static void main(String[] args){
		
		//2.1
		Rectangle patrat; //declaration
		Rectangle patrat2;
		patrat = new Rectangle(10, 20, new Point(10,20)); //initialization, using an anonymous class
		patrat2 = new Rectangle(34, 65);
		System.out.println(patrat.equals(patrat2));
		patrat.setLatime(20);
		patrat.setLungime(50);
		patrat.addPoints(new Point(), new Point(3, 5), new Point(7, 4));
		patrat = null; //ready to go to garbage
		
		//2.3
		StudentInfo student = new StudentInfo("Vasile");
		System.out.println(student.toString());
		
		Cerc c1 = new Cerc();
		Cerc c2 = new Cerc(1, 1, 1);
		Cerc.printInstanceCounter();
		
		System.out.println("--------------------------");
		//trimiterea parametrilor la o metoda, se face doar prin valoare, insa 
		
		int i = 0;
		Cerc c3 = new Cerc(1, 1, 1);
		
		System.out.println("Inaintea apelarea metodei");
		System.out.println("i = " + i);
		System.out.println(c3);
		
		c1.metoda(i, c2);
		
		System.out.println("Dupa apelarea metodei");
		
		System.out.println("i = " + i);
		System.out.println(c2);
		
		//---------------------------------------
		//2.6
		
		WrapperClass.InnerStaticClass innerStatic = new WrapperClass.InnerStaticClass();
		
		WrapperClass wrapper = new WrapperClass();
		WrapperClass.InnerClass innerClass = wrapper.new InnerClass();
		
		System.out.println("------------------------------------------------");
		Complex comp1 = new Complex(2,4);
		Complex comp2 = new Complex(6, 7);
		Complex suma = comp1.Aduna(comp2);
		
		System.out.println(comp1.equals(comp2));
		System.out.println(suma);
		
		//2.9
		// pre 1.5
		Integer obi = new Integer(1);
		int k = obi.intValue();
		
		//post 1.5
		Integer obi2 = new Integer(5);
		int k2 = obi2;
		
		//2.10
		Semafor culoare = Semafor.GALBEN;
		Semafor culoare2 = Semafor.VERDE;
	}
}
