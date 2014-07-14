package com.training.cap7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestPerformanta{

	static int n = 10000;
	
	public static void testAdd(List l){
		long t1 = System.currentTimeMillis();
		for(int i = 0; i<n; i++){
			l.add(new Integer(n));
		}
		long t2 = System.currentTimeMillis();
		
		System.out.println("Adaugarea: " + (t2-t1));
	}
	
	public static void testGet(List l){
		long t1 = System.currentTimeMillis();
		for(int i = 0; i<n; i++){
			l.get(i);
		}
		long t2 = System.currentTimeMillis();
		
		System.out.println("Extragerea: " + (t2-t1));
	}
	
	public static void testRemove(List l){
		long t1 = System.currentTimeMillis();
		for(int i = 0; i<n; i++){
			l.remove(0);
		}
		long t2 = System.currentTimeMillis();
		
		System.out.println("Stergerea: " + (t2-t1));
	}
	
	public static void main(String[] args){
		System.out.println("ArrayList-ul: ");
		List l1 = new ArrayList();
		TestPerformanta.testAdd(l1);
		TestPerformanta.testGet(l1);
		TestPerformanta.testRemove(l1);
		
		System.out.println("\nLinkedList:");
		List l2 = new LinkedList<>();
		TestPerformanta.testAdd(l2);
		TestPerformanta.testGet(l2);
		TestPerformanta.testRemove(l2);
	}
}
