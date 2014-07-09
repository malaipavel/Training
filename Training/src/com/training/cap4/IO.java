package com.training.cap4;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class IO{


	public static void main(String[] args) throws IOException{
		metoda1();
		metoda2();
		metoda3();
		metoda4();
		metoda5();
		metoda6();
		
		File[] files = new File(".").listFiles();
		for(File file: files){
			metoda7(file);
		}
	}

	/*
	 * Capitolul 4.2
	 */
	public static void metoda1() {

		BufferedReader in = null;
		BufferedWriter out = null;

		System.out.println(System.getProperty("user.dir"));

		try{
			in = new BufferedReader(new FileReader("assets\\luceafarul.txt"), 1024);
			out = new BufferedWriter(new FileWriter("assets\\out1.txt"));

			//metoda 1
			int c;
			while((c = in.read()) != -1){
				out.write((char)c);
			}

			//metoda 2
			String s;
			while((s = in.readLine()) != null){
				out.write(s);
			}
		}
		catch(IOException e){
			System.err.println(e.toString());
		}
		finally{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				System.err.println(e.toString());
			}
		}	
	}

	/**
	 * Capitolul 4.2.6
	 * @throws IOException
	 */
	public static void metoda2() throws IOException{

		FileInputStream in1 = new FileInputStream("assets\\out1.txt");
		FileInputStream in2 = new FileInputStream("assets\\out2.txt");
		FileInputStream in3 = new FileInputStream("assets\\out2.txt");

		List<FileInputStream> lista = new ArrayList<FileInputStream>();
		lista.add(in1);
		lista.add(in2);
		lista.add(in3);

		Enumeration<FileInputStream> enumeration = Collections.enumeration(lista);

		SequenceInputStream seqIn = new SequenceInputStream(enumeration);

		int i;
		while((i = seqIn.read()) != -1){
			System.out.print((char)i);
		}

		seqIn.close();
	}

	public static void metoda3(){
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\nIntroduceti un numar double: ");
		System.out.println(": " + sc.nextDouble());
		System.out.println("Introduceti un boolean: ");
		System.out.println(": " + sc.nextBoolean());
	}

	/*
	 * Capitolul 4.4.2
	 */
	public static void metoda4(){
		System.out.println("\n\n");
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		try{
			while(true){
				String s = buff.readLine();

				if(s.equals("exit") || s.length() == 0){
					break;
				}

				System.out.println(s);

				try{
					Double.parseDouble(s);
					System.out.println(" : Este NUMAR!");
				}
				catch(NumberFormatException e){
					System.out.println(" : NU ESTE NUMAR");
				}
			}
		}
		catch(IOException e){
			System.err.println("Eroare la intrare!!!!");
			e.printStackTrace();
		}
	}

	/*
	 * 4.4.3
	 */
	public static void metoda5()  {
		try {

			BufferedInputStream in = new BufferedInputStream(new FileInputStream("assets\\luceafarul.txt"));
			PrintStream out = new PrintStream(new FileOutputStream("assets\\out2.txt"));
			PrintStream err = new PrintStream(new FileOutputStream("assets\\err.txt"));

			System.setIn(in);
			System.setOut(out);
			System.setErr(err);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s;
			while((s = br.readLine()) != null){
				System.out.println(s);
			}

			throw new IOException("Exceptie test");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			System.err.println("ma aflu in fisierul err.txt");
			e.printStackTrace();
		}
	}

	/*
	 *  4.4.4 StreamTokenizer
	 */
	public static void metoda6() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("assets\\luceafarul.txt"));
		StreamTokenizer st = new StreamTokenizer(br);

		int tip = st.nextToken();

		while(tip != StreamTokenizer.TT_EOF){
			switch (tip) {
			case StreamTokenizer.TT_WORD:
				System.out.println("Cuvantul : " + st.sval);
				break;
			case StreamTokenizer.TT_NUMBER:
				System.out.println("Number: " + st.nval);
			}
			tip = st.nextToken();
		}

		RandomAccessFile ra = new RandomAccessFile("assets\\out1", "rw");
	}

	/*
	 * Capitolul 4.6. File
	 */
	public static void metoda7(File f){
		String nume = f.getName();
		if(f.isFile()){
			System.out.println("Fisiser  " + nume);
		}
		else if(f.isDirectory()){
			 System.out.println("Director " + nume);
		}
		
		System.out.println("Calea absoluta : " + f.getAbsolutePath() + '\n'
									+ "Poate citi " + f.canRead() + "\n" 
									+ "Poate scrie " + f.canWrite() + '\n'
									+ "Parinte " + f.getParent() + '\n'
									+ "cale " + f.getPath() + '\n'
									+ "Lungime " + f.length() + '\n'
									+ "data mdoficarii " + new Date(f.lastModified()) + '\n'
									+ "-------------------------------------"
			);
	}
}
