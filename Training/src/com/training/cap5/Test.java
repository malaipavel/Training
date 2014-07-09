package com.training.cap5;

import com.training.cap3.Stack.StackException;

public class Test {

	public static void afiseaza(Stack stack){
		System.out.println("Continutul stivei : " + stack);
	}
	
	public static void main(String[] args){
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
		
	}
}
