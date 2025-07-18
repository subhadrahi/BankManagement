package com.Bank.DTO;

import java.util.Random; 

public class TransactionID {

	public static long generateTransactionId() {
		Random rd=new Random();
		
		long num=0;
		long ans=0;
		int k=0;
		num=rd.nextLong();
		
			if(num<0) {
				num=num*-1;
			}
		
		while(k<15) {
			long rem=num%10;
			ans=(ans*10)+rem;
			num/=10;
			k++;
		}
		return ans;
	}

}
