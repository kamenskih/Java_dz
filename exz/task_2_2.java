package exz;
import java.util.ArrayList;
import java.util.List;

public class task_2_2 {

    public static void main(String[] args) {
        // Список: 1, 9, 2, 8, 3, 7, 4, 6, 5. x = 8
        // Очікуваний результат: 1, 2, 3, 7, 4, 6, 5, 8, 9
        List<Integer> array = new ArrayList<>(
                List.of(1, 9, 2, 8, 3, 7, 4, 6, 5)
        );
        reOrderList(array, 8);
        System.out.println(array);

        // Список: 3, 6, 4, 5, 9, 4, 2, 1. x = 5
        // Очікуваний результат: 3, 4, 4, 2, 1, 5, 6, 9
        array = new ArrayList<>(
                List.of(3, 6, 4, 5, 9, 4, 2, 1)
        );
        reOrderList(array, 5);
        System.out.println(array);
    }

    // Функція для перегрупування списку згідно вказаного числа x
    static void reOrderList(List<Integer> array, int x) {
        array.sort((a, b) -> {
            if (a < x && b < x) { // Якщо обидва елементи менше x, то зберегти порядок
                return 0;
            } else if (a < x) {  // Якщо лише a менше x, то a має з'явитися першим
                return -1;
            } else if (b < x) { // Якщо лише b менше x, то b має з'явитися першим
                return 1;
            } else if (a > x && b > x) { // Якщо обидва елементи більше x, то зберегти порядок
                return 0;
            } else if (a > x) { // Якщо лише a більше x, то a має з'явитися останнім
                return 1;
            } else {
                return -1; // Якщо лише b більше x, то b має з'явитися останнім
            }
        });
    }
}