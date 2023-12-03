package lab1.classwork1homework1;

public class task_1_7 {
    public static void main(String[] args) {

        if (args.length > 0) {
            String name = args[0];
            System.out.println("Hello, " + name + "!");
        } else {
            System.out.println("User name isn't defined");
        }
    }    
}
