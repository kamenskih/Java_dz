package lab1.homework2;

public class task_4_6_v {

    public static void main(String[] args) {
        System.out.println(softSign(3));
        System.out.println(softSign_derivative(3));
    }

    static double softSign(double x) {
        return x / (1 + Math.abs(x));
    }

    static double softSign_derivative(double x) {
        return x / Math.pow(1 + Math.abs(x), 2);
    }

}