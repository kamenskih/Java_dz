package lab2.classwork4;

public class task_3_4 {
    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        if ((a == b) && (a == c)){
            System.out.println(1);
        } else if ((a == b) ^ (a == c) ^ (b == c)) {
            System.out.println(2);
        } else {
            System.out.println(3);
        }

    }
}
