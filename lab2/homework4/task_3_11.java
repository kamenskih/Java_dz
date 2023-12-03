package lab2.homework4;

public class task_3_11 {
    public static void main(String[] args) { 
        twoSquaresSubtractions( 
                0, 0, 2, 2, 
                1, 1, -1, -1 
        ); 
    } 
 
    static void twoSquaresSubtractions(int x11, int y11, int x12, int y12, 
                                       int x21, int y21, int x22, int y22) { 
 
        if (x21 >= x12||x22 <= x11  ||-y21 >= y12 || y22 <= y11) { 
            System.out.println("No intersection"); 
            return; 
        } 
        int intersectionX1, intersectionY1, intersectionX2, intersectionY2; 
 
        intersectionX1 = Math.max(x11, x21); 
        intersectionY1 = Math.max(y11, y21); 
        intersectionX2 = Math.min(x12, x22); 
        intersectionY2 = Math.min(y12, y22); 
 
        System.out.println("Intersection coordinates:"); 
        System.out.println(intersectionX1 + ", " + intersectionY1); 
        System.out.println(intersectionX2 + ", " + intersectionY2); 
    } 
}
    
