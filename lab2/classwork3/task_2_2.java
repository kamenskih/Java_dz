package lab2.classwork3;

public class task_2_2 {
    
    public static void main(String[] args) {

        int a = 0x7FFFFFFF; 
        System.out.println("decimal a: " + a + ", binary a: " + Integer.toBinaryString(a));
        while (a != 0){
            a = a >> 1;
            System.out.println("a: " + Integer.toBinaryString(a));
        }

        int b = 0b11011011; // Двійкове число
        System.out.println("decimal b: " + b + ", binary b: " + Integer.toBinaryString(b));
        b = b << 24;
        System.out.println("After <<, decimal b: " + b + ", binary b: " + Integer.toBinaryString(b));
        while (b != 0){
            b = b >>> 1;
            System.out.println("b: " + Integer.toBinaryString(b));
        }

    }
}
