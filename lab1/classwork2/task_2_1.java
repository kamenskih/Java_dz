package lab1.classwork2;

import java.util.Scanner;

public class task_2_1 {
    public static void main(String[] args) {
        System.out.println("Enter surname");
        
        Scanner user_inp = new Scanner(System.in);
        String prizw = user_inp.nextLine();

        System.out.println("Hello, " + prizw);

        user_inp.close();
    }
    
}
