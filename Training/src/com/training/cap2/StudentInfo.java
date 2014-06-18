package com.training.cap2;

//2.3

public class StudentInfo extends Student{
	private static final String specialitate = "Informatica";

	public StudentInfo (String nume) {
		super(nume);
	}

/*	@Override
	public float calcMedia(){
	}*/

	//unknown number of arguments
	public void adaugaNote(Float ... note){
		for(Float nota : note){
			this.note.add(nota);
		}
	}
	
	@Override
	public void printSpeciality () {
		System.out.println("Specialitate: "+ specialitate);
	}

	@Override
	public String toString () {
		return super.toString() + " " +specialitate;
	}
	
	
}
