package com.training.cap5;

@SuppressWarnings("rawtypes")
public class Persoana implements Comparable{

	String nume;
	int cod;
	public Persoana (String nume, int cod) {
		this.nume = nume;
		this.cod = cod;
	}
	
	@Override
	public String toString () {
		return nume + " " + cod;
	}

	@Override
	public int compareTo (Object o) {
		if(o == null){
			throw new NullPointerException();
		}
		if(!(o instanceof Persoana)){
			throw new ClassCastException("Nu se pot compara!");
		}
		
		Persoana p = (Persoana)o;
		return cod - p.cod;
	}
	
	@Override
	public boolean equals (Object obj) {
		if(!(obj instanceof Persoana)){
			return false;
		}
		Persoana p = (Persoana)obj;
		
		return (cod == p.cod) && (nume.equals(p.nume));
	}
}
