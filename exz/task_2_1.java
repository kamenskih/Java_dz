package exz;
import java.util.ArrayList;
import java.util.LinkedList;

public class task_2_1 {

    public static void main(String[] args) {
        // Виведення заголовків для порівняння операцій
        System.out.printf("%-16s", "Operation");
        System.out.printf("%-16s", "ArrayList");
        System.out.printf("%-16s%n", "LinkedList");

        // Додавання елементів у ArrayList та вимірювання часу
        System.out.printf("%-16s", "add");
        long alAddTime = System.nanoTime();
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            al.add(i);
        }
        alAddTime = System.nanoTime() - alAddTime;
        System.out.printf("%-16s", alAddTime);

        // Додавання елементів у LinkedList та вимірювання часу
        long llAddTime = System.nanoTime();
        LinkedList<Integer> ll = new LinkedList<>();
        for (int i = 0; i < 1_000_000; i++) {
            ll.add(i);
        }
        llAddTime = System.nanoTime() - llAddTime;
        System.out.printf("%-16s%n", llAddTime);

        // Отримання елементів з ArrayList та вимірювання часу
        System.out.printf("%-16s", "get");
        long alGetTime = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            al.get(i);
        }
        alGetTime = System.nanoTime() - alGetTime;
        System.out.printf("%-16s", alGetTime);

        // Отримання елементів з LinkedList та вимірювання часу
        long llGetTime = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            ll.get(i);
        }
        llGetTime = System.nanoTime() - llGetTime;
        System.out.printf("%-16s%n", llGetTime);

        // Видалення елементів з ArrayList та вимірювання часу
        System.out.printf("%-16s", "remove");
        long alRemoveTime = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            al.remove(0);
        }
        alRemoveTime = System.nanoTime() - alRemoveTime;
        System.out.printf("%-16s", alRemoveTime);

        // Видалення елементів з LinkedList та вимірювання часу
        long llRemoveTime = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            ll.remove(0);
        }
        llRemoveTime = System.nanoTime() - llRemoveTime;
        System.out.printf("%-16s%n", llRemoveTime);
    }
}