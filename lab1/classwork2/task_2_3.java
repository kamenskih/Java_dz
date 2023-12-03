package lab1.classwork2;

import java.lang.Math;
import java.util.Scanner;

public class task_2_3 {
    public static void main(String[] args) {

    System.out.println("Enter two whole numbers with a space: ");

    Scanner user_inp = new Scanner(System.in);
    Float a = user_inp.nextFloat();
    Float b = user_inp.nextFloat();
    user_inp.close();

    Double c = Math.sqrt(a*b);
    System.out.printf("Geometric mean in science %e and in decimal %f representations.", c, c);
    }
}
    

