package com.training.cap1;

import java.util.Arrays;

public class Exemplu {


	// 1.5.1
	int a;
	public void metoda(int b){
		a = b;
		int c = 15;
		for(int i = 0; i<15; i++){
			c--;
		}

		try{
			//a = b/c; //esueaza - impartire la zero
		}
		catch(ArithmeticException e){
			e.printStackTrace();
		}

		for(int i = 0;i<100;i++){
			//
		}
		//i++; <- out of scope


		//1.5.2-------------------------------------------------

		for(int i = 0, j = 1000; i < 100 && j > 0; i++, j--){
			//instructiuni multiple in for, separate prin virgula
		}

		//1.5.4----------------------------------------------------
/*		int i = 0;
		eticheta:
			while(i<10){
				System.out.println("i="+i);
				int j = 0;
				while(j < 10){
					j++;
					if(j == 5) continue eticheta; //ajunge aici si porneste eticheta din nou, ruleaza la infinit
					if(j == 7) break eticheta;
					System.out.println("j = "+j);
				}
				i++;
			}*/
		
		//1.6.-----------------------------------------------------
		int v1[] = {1,2,3,4};
		int v2[] = new int[4];
		
		//copierea
		
		for(int k = 0; k < v1.length; k++)
			v2[k] = v1[k];
		
		System.arraycopy(v1, 0, v2, 0, v1.length);
		
		//sortarea
		Arrays.sort(v2); //ramane la fel
		
		//cautarea
		Arrays.binarySearch(v1, 3); //returneaza indexul 2
		
		System.out.println(Arrays.equals(v1, v2));
		
		//1.8-------------------------------------------------------
		String x1 = "a" + 1 + "b"; 
		String x2 = new StringBuffer("a").append(1).append(b).toString(); 
		
		
		// java NumeApp arg1 arg2 (argumente separate prin spatiu, cele cu spatiu, se pun in ghilimele)
		//argumentele sunt de tip string, trbuie parsate, ex Integer.parseInt(atgs[0]);
		
	}
	public static void main(String[] args)	{
		Exemplu ex = new Exemplu();
		ex.metoda(10);
	}
}
