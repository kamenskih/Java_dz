package lab1.classwork1homework1;

public class task_1_5 {
    public static void main(String[] args) {

        if (args.length == 3) {
            String a = args[0];
            String b = args[1];
            String c = args[2];
            System.out.println(a + " " +  b + " " + c);
        } else {
            System.out.println("Please, enter 3 arguments!");
        }
    }
}
