package com.training.cap5;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

import com.training.cap3.Stack.StackException;

public class TestMain {

	public static void afiseaza(Stack stack){
		System.out.println("Continutul stivei : " + stack);
	}

	public static void main(String[] args){

		//------------------------------------------
		// Stack testing
		Stack s1 = new StackArray();
		Stack s2 = new StackDynamic();

		try {
			s1.push("a");
			s1.push("b");
			s1.push("c");
			afiseaza(s1);

			s2.push(new Boolean(false));
			s2.push(new Integer(5));
			s2.push(new Float(4));
			afiseaza(s2);

		} catch (StackException e) {
			e.printStackTrace();
		}

		//--------------------------------------
		// FilenameFilter testing
		System.out.println('\n');
		
		File director = new File(".");
		FiltruFisiere filtru = new FiltruFisiere("classpath");

		String[] list;
		String[] list2;

		list = director.list(filtru);
		list2 = director.list(new FilenameFilter() {

			@Override
			public boolean accept (File dir, String name) {
				return name.endsWith("idea");
			}
		});

		for(int i = 0; i<list.length; i++){
			System.out.println(list[i]);
		}
		for(int i = 0; i<list.length; i++){
			System.out.println(list2[i]);
		}
		

		//-----------------------------------------
		//Comparable, Comparator
		System.out.println('\n');
		
		Persoana p1 = new Persoana("ion", 3665);
		Persoana p2 = new Persoana("adg", 353665);
		Persoana p3 = new Persoana("bsdsfd", 633665);
		Persoana p4 = new Persoana("cdgsfz", 360165);
		
		Persoana[] array = {p1, p2, p3, p4};
		
		Arrays.sort(array);
		
		Arrays.sort(array, new Comparator<Persoana>() {
			@Override
			public int compare (Persoana o1, Persoana o2) {
				return o1.nume.compareTo(o2.nume);
			}});
		
		System.out.println("Ordonat dupa cod:");
		for(int i = 0;i<array.length; i++)
			System.out.println(array[i]);
		
		System.out.println("Ordonat dupa nume:");
		for(int i = 0;i<array.length; i++)
			System.out.println(array[i]);
	}
}
