package com.training.cap2;

import java.util.ArrayList;
import java.util.List;

	//2.3

public abstract class Student {

	protected String nume;
	protected List<Float> note = new ArrayList<Float>();
	
	public Student(String nume){
		this.nume = nume;
	}
	
	// cannot be modified in child classes
	public final float calcMedia(){
		
		float media, suma = 0;
		for(Float nota : note	){
			suma += nota;
		}
		
		media = suma/note.size();
		
		return media;
	}
	
	@Override
		public String toString () {
			return nume;
		}
	
	public abstract void printSpeciality();
}
