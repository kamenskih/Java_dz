package lab4.homework8;

    import java.util.Arrays; 
 
public class task_4_16 { 
 
    public static void main(String[] args) { 
        BoolMatrix matrix1 = new BoolMatrix(new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}}); 
        BoolMatrix matrix2 = new BoolMatrix(new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 1, 0}}); 
 
        System.out.println("matrix1:"); 
        matrix1.printMatrix(matrix1.matrix); 
        System.out.println("matrix2:"); 
        matrix2.printMatrix(matrix2.matrix); 
        System.out.println("matrix1.add(matrix2):"); 
        matrix1.printMatrix(matrix1.add(matrix2)); 
        System.out.println("matrix1.multiply(matrix2):"); 
        matrix1.printMatrix(matrix1.multiply(matrix2)); 
        System.out.println("matrix1.invert():"); 
        matrix1.printMatrix(matrix1.invert()); 
        System.out.println("matrix1.countOnes():"); 
        System.out.println(matrix1.countOnes()); 
        System.out.println("matrix1.lexicographicallySort():"); 
        matrix1.printMatrix(matrix1.lexicographicallySort()); 
    } 
 
 
    static class BoolMatrix { 
        int[][] matrix; 
 
        public BoolMatrix(int[][] matrix) { 
            this.matrix = matrix; 
        } 
 
        public int[][] add(BoolMatrix other) { 
            int[][] result = new int[matrix.length][matrix[0].length]; 
            for (int i = 0; i < matrix.length; i++) { 
                for (int j = 0; j < matrix[0].length; j++) { 
                    if ((matrix[i][j] == 1) || (other.matrix[i][j] == 1)) { 
                        result[i][j] = 1; 
                    } else { 
                        result[i][j] = 0; 
                    } 
                } 
            } 
            return result; 
        } 
 
        public int[][] multiply(BoolMatrix other) { 
            int[][] result = new int[matrix.length][other.matrix[0].length]; 
            for (int i = 0; i < matrix.length; i++) { 
                for (int j = 0; j < other.matrix[0].length; j++) { 
                    for (int k = 0; k < matrix[0].length; k++) { 
                        if ((matrix[i][k] == 1) && (other.matrix[k][j] == 1)) { 
                            result[i][j] = 1; 
                        } else { 
                            result[i][j] = 0; 
                        } 
                    } 
                } 
            } 
            return result; 
        } 
 
        public int[][] invert() { 
            int[][] result = new int[matrix.length][matrix[0].length]; 
            for (int i = 0; i < matrix.length; i++) { 
                for (int j = matrix[0].length - 1; j >= 0; j--) { 
                    result[i][j] = matrix[i][matrix[0].length - 1 - j]; 
                } 
            } 
            return result; 
        } 
 
        public int countOnes() { 
            int count = 0; 
            for (int[] row : matrix) { 
                for (int element : row) { 
                    if (element == 1) { 
                        count++; 
                    } 
                } 
            } 
            return count; 
        } 
 
        public int[][] lexicographicallySort() { 
            int[][] result = new int[matrix.length][matrix[0].length]; 
            int[] temp = new int[matrix.length * matrix[0].length]; 
            int k = 0; 
            for (int[] row : matrix) { 
                for (int element : row) { 
                    temp[k] = element; 
                    k++; 
                } 
            } 
            Arrays.sort(temp); 
            k = 0; 
            for (int i = 0; i < result.length; i++) { 
                for (int j = 0; j < result[0].length; j++) { 
                    result[i][j] = temp[k]; 
                    k++; 
                } 
            } 
            return result; 
        } 
 
        void printMatrix(int[][] matrix) { 
            for (int[] row : matrix) { 
                for (int element : row) { 
                    System.out.print(element + " "); 
                } 
                System.out.println(); 
            } 
        } 
 
    } 
}
    

