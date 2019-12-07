package genetikalogarithm;

import java.util.Scanner;

public class Main {
     static String kata;
     static int[] target;
     static int generation=0;
     static int idividualSize=0;
    public static void main(String[] args) {
       input();
       GenetikAlogarithm demo = new  GenetikAlogarithm(target,idividualSize,generation);
       int i=0;
        while (i<generation) {            
           demo.test(); 
           i++;
        }
        demo.convertToString();
        System.out.println("last generation = "+i);
    }
     public static  void input(){
       Scanner input = new Scanner(System.in);
       System.out.print("masukan kata :");
       kata=input.nextLine();
       target=new int[kata.length()];
       for(int i=0;i<kata.length();i++){
         target[i]=(int)kata.charAt(i)-96;
       }
       System.out.print("masukan jumlah individu:");
       idividualSize=Integer.parseInt(input.nextLine());
       System.out.print("masukan jumlah generasi :");
       generation=Integer.parseInt(input.nextLine());
      
    }
    
}
