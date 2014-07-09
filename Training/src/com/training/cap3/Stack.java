package com.training.cap3;

public class Stack {

	int[] elements = new int[100];
	
	int n = 0;
	
	public void add(int x) throws StackException{
		if(n == 100)
			throw new StackException("Stack is full!");
		elements[n++] = x;
	}
	
	public int remove() throws StackException {
		if( n == 0)
			throw new StackException("Stack is empty!");
		return elements[n--];
	}
	
	public static class StackException extends Exception {
		
		public StackException(){
			super();
		}
		
		public StackException(String msg){
			super(msg);
		}
	}
}
