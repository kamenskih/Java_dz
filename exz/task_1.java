package exz;

import java.io.*; 
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.List; 

public class task_1 { 
 
    public static void main(String[] args) { 
    //отримання списку рядків із файла "input.txt"
        List<String> strings = getStringsFromFile("C:\\дз прога 3 курс\\Java_dz\\Java_dz\\exz\\input.txt"); 
    //перевернення в зворотній порядок
        Collections.reverse(strings); 
    //записуємо отриманий новий список рядків в файл "output"
        writeToFile("C:\\дз прога 3 курс\\Java_dz\\Java_dz\\exz\\output.txt\\", strings); 
    } 
    //метод для отримаання рядків з файлу
    static List<String> getStringsFromFile(String path) { 
        List<String> strings = new ArrayList<>(); 
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) { 
            String line; 
            //зчитувати рядок з файлу й додавати до списку
            while ((line = reader.readLine()) != null) { 
                strings.add(line); 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
 
        return strings; 
    } 
    //метод для запису рядків у файл 
    static void writeToFile(String path, List<String> strings) { 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) { 
            //записувати кожен рядок зі списку у файл 
            for (String string : strings) { 
                writer.write(string); 
                writer.newLine(); //перехід на новий рядок 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
}

