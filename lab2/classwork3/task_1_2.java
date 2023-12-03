package lab2.classwork3;

import java.util.Scanner;

public class task_1_2 {
    public static void main(String[] args) {

        String s1, s2;
        Scanner user_inp = new Scanner(System.in);
        s1 = user_inp.next();
        s2 = user_inp.next();

        if (s1.equals(s2)){
            System.out.printf("Equal\n");
        } else {
            System.out.printf("Non equal");
        }
        
        user_inp.close();
    }
}
    
