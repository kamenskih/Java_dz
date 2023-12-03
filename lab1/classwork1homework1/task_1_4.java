package lab1.classwork1homework1;

public class task_1_4 {
    
    static int counter = 0;
    
    public static void plus() {counter++;}
    
    public static int getCount() {return counter;}

    public static void main(String[] args) {
        task_1_4 obj1 = new task_1_4();
        task_1_4 obj2 = new task_1_4();
        obj1.plus();
        
        int c = obj2.getCount();
        
        System.out.println("Значення отримане з другого об'єкту класу: " + c
                        + "\nЗначення з першого об'єкту: " + obj1.getCount());

    }

}