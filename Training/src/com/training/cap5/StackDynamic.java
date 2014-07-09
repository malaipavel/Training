package com.training.cap5;

import com.training.cap3.Stack.StackException;

public class StackDynamic implements Stack{

	Node top = null;

	@Override
	public void push (Object obj) throws StackException {
		Node nod = new Node(obj, top);
		top = nod;
	}

	@Override
	public void pop () throws StackException {
		if(empty())
			throw new StackException("Stack is empty!");
		Node temp = top;
		top = top.link;
		temp = null;
	}

	@Override
	public Object peek () throws StackException {
		if(empty())
			throw new StackException("Stack is empty!");
		return top.item;
	}

	@Override
	public boolean empty () {
		if(top == null)
			return true;
		return false;
	}
	
	@Override
	public String toString () {
		Node temp = top;
		String s = "";
		while(temp != null){
			s += temp.item.toString() + ' ';
			temp = temp.link;
		}
		return s;
	}

	public class Node {
		 Object item;
		 Node link;

		public Node(Object item, Node link)	{
			this.item = item;
			this.link = link;
		}
	}
}
