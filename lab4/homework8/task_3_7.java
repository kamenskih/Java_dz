package lab4.homework8;

 
public class task_3_7 { 
 
    public static void main(String[] args) { 
        Triangle triangle1 = new Triangle(3, 4, 5); 
        Triangle triangle2 = new Triangle(5, 5, 5); 
        Triangle triangle3 = new Triangle(3, 4, 5); 
        Triangle triangle4 = new Triangle(3, 4, 5); 
 
        Triangle[] triangles = new Triangle[]{triangle1, triangle2, triangle3, triangle4}; 
 
        Triangle[] isoscelesTriangles = findIsoscelesTriangles(triangles); 
        Triangle[] equilateralTriangles = findEquilateralTriangles(triangles); 
        Triangle[] rightAngledTriangles = findRightAngledTriangles(triangles); 
        Triangle[] arbitraryTriangles = findArbitraryTriangles(triangles); 
 
        System.out.println("Max square for isosceles triangles = " + findTriangleWithMaxSquare(isoscelesTriangles)); 
        System.out.println("Min square for isosceles triangles = " + findTriangleWithMinSquare(isoscelesTriangles)); 
        System.out.println("Max square for equilateral triangles = " + findTriangleWithMaxSquare(equilateralTriangles)); 
        System.out.println("Min square for equilateral triangles = " + findTriangleWithMinSquare(equilateralTriangles)); 
        System.out.println("Max square for right-angled triangles = " + findTriangleWithMaxSquare(rightAngledTriangles)); 
        System.out.println("Min square for right-angled triangles = " + findTriangleWithMinSquare(rightAngledTriangles)); 
        System.out.println("Max square for arbitrary triangles = " + findTriangleWithMaxSquare(arbitraryTriangles)); 
        System.out.println("Min square for arbitrary triangles = " + findTriangleWithMinSquare(arbitraryTriangles)); 
 
//        System.out.println("Perimeter of triangle1 = " + countPerimeter(triangle1)); 
//        System.out.println("Square of triangle1 = " + countSquare(triangle1)); 
//        System.out.println("Isosceles triangles:" + Arrays.toString(findIsoscelesTriangles(triangles))); 
//        System.out.println("Equilateral triangles:" + Arrays.toString(findEquilateralTriangles(triangles))); 
//        System.out.println("Right-angled triangles:" + Arrays.toString(findRightAngledTriangles(triangles))); 
//        System.out.println("Triangle with max square = " + findTriangleWithMaxSquare(triangles)); 
//        System.out.println("Triangle with min square = " + findTriangleWithMinSquare(triangles)); 
    } 
 
    static double countPerimeter(Triangle triangle) { 
        return triangle.getA() + triangle.getB() + triangle.getC(); 
    } 
 
    static double countSquare(Triangle triangle) { 
        double p = countPerimeter(triangle) / 2; 
        return Math.sqrt(p * (p - triangle.getA()) * (p - triangle.getB()) * (p - triangle.getC())); 
    } 
 
    static Triangle[] findIsoscelesTriangles(Triangle[] triangles) { 
        Triangle[] result = new Triangle[triangles.length]; 
        int p = 0; 
        for (Triangle triangle : triangles) { 
            if (triangle != null && triangle.getA() == triangle.getB() || triangle.getA() == triangle.getC() || triangle.getB() == triangle.getC()) { 
                result[p] = triangle; 
                p++; 
            } 
        } 
        return result; 
    } 
 
    static Triangle[] findEquilateralTriangles(Triangle[] triangles) { 
        Triangle[] result = new Triangle[triangles.length]; 
        int p = 0; 
        for (Triangle triangle : triangles) { 
            if (triangle != null && triangle.getA() == triangle.getB() && triangle.getA() == triangle.getC()) { 
                result[p] = triangle; 
                p++; 
            } 
        } 
        return result; 
    } 
 
    static Triangle[] findRightAngledTriangles(Triangle[] triangles) { 
        Triangle[] result = new Triangle[triangles.length]; 
        int p = 0; 
        for (Triangle triangle : triangles) { 
            if (triangle != null && Math.pow(triangle.getA(), 2) == Math.pow(triangle.getB(), 2) + Math.pow(triangle.getC(), 2) || 
                    Math.pow(triangle.getB(), 2) == Math.pow(triangle.getA(), 2) + Math.pow(triangle.getC(), 2) ||
                    Math.pow(triangle.getC(), 2) == Math.pow(triangle.getA(), 2) + Math.pow(triangle.getB(), 2)) { 
                result[p] = triangle; 
                p++; 
            } 
        } 
        return result; 
    } 
 
    static Triangle[] findArbitraryTriangles(Triangle[] triangles) { 
        Triangle[] result = new Triangle[triangles.length]; 
        int p = 0; 
        for (Triangle triangle : triangles) { 
            if (triangle != null && triangle.getA() != triangle.getB() && triangle.getA() != triangle.getC() && triangle.getB() != triangle.getC()) { 
                result[p] = triangle; 
                p++; 
            } 
        } 
        return result; 
    } 
 
    static Triangle findTriangleWithMaxSquare(Triangle[] triangles) { 
        Triangle result = triangles[0]; 
        for (Triangle triangle : triangles) { 
            if (triangle != null && countSquare(triangle) > countSquare(result)) { 
                result = triangle; 
            } 
        } 
        return result; 
    } 
 
    static Triangle findTriangleWithMinSquare(Triangle[] triangles) { 
        Triangle result = triangles[0]; 
        for (Triangle triangle : triangles) { 
            if (triangle != null && countSquare(triangle) < countSquare(result)) { 
                result = triangle; 
            } 
        } 
        return result; 
    } 
 
    static class Triangle { 
        private double a; 
        private double b; 
        private double c; 
 
        public Triangle(double a, double b, double c) { 
            this.a = a; 
            this.b = b; 
            this.c = c; 
        } 
 
        public double getA() { 
            return a; 
        } 
 
        public double getB() { 
            return b; 
        } 
 
        public double getC() { 
            return c; 
        } 
 
        @Override 
        public String toString() { 
            return "Triangle{" + 
                    "a=" + a + 
                    ", b=" + b + 
                    ", c=" + c + 
                    '}'; 
        } 
    } 
}
    
