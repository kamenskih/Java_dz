package lab2.homework4;

import java.util.Random;

 
public class task_4_7 { 
 
    public static void main(String[] args) { 
        Random random = new Random(); 
 
        int obtuseTriangleCount = 0; 
 
        for (int i = 0; i < 10000; i++) { 
            double a = random.nextDouble(); 
            double b = random.nextDouble(); 
            double c = random.nextDouble(); 
 
            boolean isObtuse = (a + b > c) && (Math.pow(a, 2) + Math.pow(b, 2) >= Math.pow(c, 2)); 
 
            if (isObtuse) { 
                obtuseTriangleCount++; 
            } 
        } 
        System.out.println("Ймовірність того, що трикутник тупий: " + (obtuseTriangleCount / 10000.0)); 
    } 
}