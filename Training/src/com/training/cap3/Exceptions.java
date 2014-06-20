package com.training.cap3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.training.cap3.Stack.StackException;

public class Exceptions {

	public static void main(String[]args) throws FileNotFoundException, IOException, StackException{

		int v[] = new int[10];

		try{
			//System.out.println(v[10]);
		}
		catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}

		readFile("assets/file.txt");
		
		readFile2("assets/file.txt");
		
		Stack stack = new Stack();
		stack.remove();
	}


	public static void readFile(String filename){

		FileReader fr = null;

		try{
			fr = new FileReader(filename);

			int c;
			while((c = fr.read()) != -1){
				System.out.print((char)c);
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch( Exception e){
			e.printStackTrace();
		}
		catch(Throwable e){
			e.printStackTrace();
		}
		finally{
			if(fr != null){
				try{
					fr.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			System.out.println("\nReading finished succesfully");
		}
	}

	public static void readFile2(String filename) throws  FileNotFoundException, IOException{
		
		FileReader fr = new FileReader(filename);

		int c;
		while((c = fr.read()) != -1){
			System.out.print((char)c);
		}
		fr.close();
		System.out.println("\nReading finished succesfully 2");
	}
}
