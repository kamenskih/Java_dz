package lab1.classwork2;

import java.util.Scanner;

public class task_2_2 {
    public static void main(String[] args) {

    System.out.println("Enter two whole numbers with a space: ");
    
    Scanner user_inp = new Scanner(System.in);
    String a = user_inp.next();
    Double b = user_inp.nextDouble();
    user_inp.close();

    int a_l = a.length();
    int b_l = (int)(Math.log10(b) + 1);
    System.out.println("Amount of digits of number а = " + a_l + ", b = " + b_l);
    
    Double garm = 1/(1/Double.parseDouble(a) + 1/b);
    System.out.printf("The harmonic mean of these numbers = %.2f\n", garm);
    }
}
