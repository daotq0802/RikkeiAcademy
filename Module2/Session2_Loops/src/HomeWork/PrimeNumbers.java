package HomeWork;

import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        System.out.println("Prime numbers from 1 to 100");
        for(int i = 1; i<=100;i++){
            int count = 0;
            for(int j = i; j>=1;j--){
                if(i%j==0){
                    count++;
                }
            }
            if(count==2){
                System.out.print(i + "\t");
            }
        }
    }
}
