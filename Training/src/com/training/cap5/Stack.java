package com.training.cap5;

import com.training.cap3.Stack.StackException;

public interface Stack {

	void push(Object obj) throws StackException;
	
	void pop() throws StackException;
	
	Object peek() throws StackException;
	
	boolean empty();
	
	String toString();
}
