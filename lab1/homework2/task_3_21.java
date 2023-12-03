package lab1.homework2;

public class task_3_21 {

    public static void main(String[] args) {
        System.out.println(t3_21(4, 2, 3));
        t3_22_a(4, 2, 3);
        t3_22_b(4, 2, 3);
        t3_22_c(4, 2, 3);
    }

    static double t3_21(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    static void t3_22_a(double a, double b, double c) {
        double m1 = Math.sqrt(2 * Math.pow(a, 2) + 2 * Math.pow(b, 2) - Math.pow(c, 2)) / 2.0;
        double m2 = Math.sqrt(2 * Math.pow(b, 2) + 2 * Math.pow(c, 2) - Math.pow(a, 2)) / 2.0;
        double m3 = Math.sqrt(2 * Math.pow(c, 2) + 2 * Math.pow(a, 2) - Math.pow(b, 2)) / 2.0;
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
    }

    static void t3_22_b(double a, double b, double c) {
        double l1 = (2 * Math.sqrt(a * b * (a + b + c) * (a + b - c))) / (a + b);
        double l2 = (2 * Math.sqrt(b * c * (a + b + c) * (b + c - a))) / (b + c);
        double l3 = (2 * Math.sqrt(c * a * (a + b + c) * (c + a - b))) / (c + a);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
    }

    static void t3_22_c(double a, double b, double c) {
        double p = (a + b + c) / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        double h1 = 2 * s / a;
        double h2 = 2 * s / b;
        double h3 = 2 * s / c;
        System.out.println(h1);
        System.out.println(h2);
        System.out.println(h3);
    }
}