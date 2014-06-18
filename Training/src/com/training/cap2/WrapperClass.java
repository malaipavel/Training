package com.training.cap2;

public class WrapperClass {
	private int i = 0;

	public class InnerClass{
		int k = i;
	}

	public static class InnerStaticClass{
		
	}

	void metoda(){
		final int var1 = 1;
		int var2 = 2;
		
		class InnerClass2{
			int var3 = var1;
			//int var4 = var2;
		}
	}
}
