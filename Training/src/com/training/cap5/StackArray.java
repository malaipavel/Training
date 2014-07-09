package com.training.cap5;

import com.training.cap3.Stack.StackException;

public class StackArray implements Stack{

	private Object[] data;
	private int position;
	
	public StackArray (int len) {
		data = new Object[len];
		position = 0;
	}
	
	public StackArray(){
		this(100);
	}
	
	@Override
	public void push (Object obj) throws StackException {
		if(position == data.length)
			throw new StackException("Stack is full!");
		data[position++] = obj;
	}

	@Override
	public void pop () throws StackException {
		if(empty())
			throw new StackException("Stack is empty!");
		data[--position] = null;
	}

	@Override
	public Object peek () throws StackException {
		if(empty())
			throw new StackException("Stack is empty");
		return data[position - 1];
	}

	@Override
	public boolean empty () {
		if(position == 0){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString () {
		String s = "";
		for(int i = 0 ; i < position; i++){
			s+= data[i].toString() + " ";
		}
		return s;
	}
}
