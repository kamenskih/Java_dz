package lab3.homework5;

public class task_1_10 {
    public static void main(String[] args) { 
    System.out.println(findPallindrom(new int[]{121, 21, 34, 45, 56, 11})); 
} 

static int findPallindrom(int[] numbers) { 
    int[] pallindroms = new int[numbers.length]; 
    int p = 0; 
    for (int number : numbers) { 
        if (String.valueOf(number).contentEquals(new StringBuilder(String.valueOf(number)).reverse())) { 
            pallindroms[p] = number; 
            p++; 
        } 
    } 
    if (pallindroms[1] != 0) { 
        return pallindroms[1]; 
    } 
    return pallindroms[0]; 
} 
}
    

