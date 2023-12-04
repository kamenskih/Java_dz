package lab3.homework5;

public class task_1_20 {
    public static void main(String[] args) { 
        System.out.println(findControlDigit("020131452")); 
    } 
 
    static int findControlDigit(String isbn) { 
        String[] isbnParts = new StringBuilder(isbn).reverse().toString().split(""); 
 
        int sum = 0; 
        for (int i = 0; i < isbn.length(); i++) { 
            sum += Integer.parseInt(isbnParts[i]) * (i + 2); 
            System.out.println(Integer.parseInt(isbnParts[i]) + " * " + (i + 2)); 
        } 
        System.out.println("sum = " + sum); 
        return sum % 11 - 1; 
    } 
    
}
