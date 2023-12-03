package lab3.homework6;

public class task_2_9 {
    public static void main(String[] args) { 
        int[][] matrix = fillMatrixRandom(5, 5); 
        printMatrix(matrix); 
        System.out.println(); 
        printMatrix(removeArithmeticAvg(matrix)); 
    } 
 
    static int[][] removeArithmeticAvg(int[][] matrix) { 
        int avg = findMatrixAvg(matrix); 
        System.out.println("avg = " + avg); 
        for (int[] row : matrix) { 
            for (int i = 0; i < row.length; i++) { 
                row[i] -= avg; 
            } 
        } 
        return matrix; 
    } 
 
    static void printMatrix(int[][] matrix) { 
        for (int[] row : matrix) { 
            for (int element : row) { 
                System.out.print(element + " "); 
            } 
            System.out.println(); 
        } 
    } 
 
    static int[][] fillMatrixRandom(int rows, int columns) { 
        int[][] matrix = new int[rows][columns]; 
        for (int i = 0; i < matrix.length; i++) { 
            for (int j = 0; j < matrix[i].length; j++) { 
                matrix[i][j] = (int) (Math.random() * 10); 
            } 
        } 
        return matrix; 
    } 
 
    static int findMatrixAvg(int[][] matrix) { 
        int sum = 0; 
        int count = 0; 
        for (int[] row : matrix) { 
            for (int element : row) { 
                sum += element; 
                count++; 
            } 
        } 
        return sum / count; 
    } 
}
    

