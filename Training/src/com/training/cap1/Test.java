package com.training.cap1;

import java.util.ArrayList;
import java.util.List;


public class Test {

	public static void main(String[] args){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		metoda(lista);
		
		for(Integer i : lista){
			System.out.println(i);
		}
	}
	
	static void metoda(List<Integer> i ){
		i.add(2);
	}
}
