package exz;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class task_2_12 {

    public static void main(String[] args) {
        // Створення списку точок
        List<Point> points = List.of(
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(3, 4),
                new Point(6, 8),
                new Point(9, 12)
        );

        // Знаходження ліній та кількості точок на кожній лінії
        HashMap<String, Integer> lines = findLines(points);
        HashMap<String, Integer> moreThanOnePoint = new HashMap<>();

        // Відбір ліній, на яких більше однієї точки
        for (String line : lines.keySet()) {
            if (lines.get(line) > 1) {
                moreThanOnePoint.put(line, lines.get(line));
            }
        }

        // Запис результатів у файл
        writeToFile("C:\\дз прога 3 курс\\Java_dz\\Java_dz\\exz\\line.txt", moreThanOnePoint);
    }

    // Метод для знаходження ліній та кількості точок на кожній лінії
    static HashMap<String, Integer> findLines(List<Point> points) {
        HashMap<String, Integer> lines = new HashMap<>();

        // Перебір всіх точок
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);

            // Перебір усіх точок після поточної
            for (int j = i + 1; j < points.size(); j++) {
                Point point1 = points.get(j);

                // Отримання формули лінії для поточних 2 точок
                String line = getLine(point, point1);

                // Підрахунок кількості точок на кожній лінії
                if (lines.containsKey(line)) {
                    lines.put(line, lines.get(line) + 1);
                } else {
                    lines.put(line, 1);
                }
            }
        }

        return lines;
    }

    // Метод для отримання формули лінії за двома точками
    static String getLine(Point point1, Point point2) {
        // Обчислення коефіцієнтів лінії
        double k = (double) (point1.y - point2.y) / (point1.x - point2.x);
        double b = point1.y - k * point1.x;

        // Округлення до двох знаків після коми
        k = Math.round(k * 100.0) / 100.0;
        b = Math.round(b * 100.0) / 100.0;

        // Визначення форми рівняння лінії з урахуванням особливостей
        if (point1.x - point2.x == 0) {
            return "x = " + point1.x;
        } else if (point1.y - point2.y == 0) {
            return "y = " + point1.y;
        } else if (b == 0) {
            return "y = " + k + "x";
        } else if (b > 0) {
            return "y = " + k + "x + " + b;
        } else {
            return "y = " + k + "x - " + Math.abs(b);
        }
    }

    // Метод для запису результатів у файл
    static void writeToFile(String path, HashMap<String, Integer> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            // Запис у файл формули ліній та кількості точок на них
            for (String line : lines.keySet()) {
                writer.write(line + " [" + lines.get(line) + "]");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Клас для зберігання координат точок
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}