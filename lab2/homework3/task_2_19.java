package lab2.homework3;

public class task_2_19 {
     public static void main(String[] args) { 
     
        System.out.println(getControlBit(0b0010101)); 
        System.out.println(get28BitNumber(0b0010101)); 
    } 
     
    static int getControlBit(int number) { 
        int controlBit = 0; 
        for (int i = 0; i < 7; i++) { 
            controlBit = controlBit ^ (number >> i & 1); 
        } 
        return controlBit; 
    } 
     
    static int get28BitNumber(int number) { 
        int controlBit = getControlBit(number); 
        return (number << 1) | controlBit; 
    } 
}
