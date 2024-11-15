package Task5;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введіть ім'я архіву (з розширенням .zip): ");
        String archiveName = reader.readLine();
        String zipPath = System.getProperty("user.dir") + "/src/Task5/" + archiveName ;
        ZipOutputStream zout = new ZipOutputStream( new FileOutputStream(zipPath)); // ZipOutputStream відповідає за додавання файлів у ZIP-архів, стиснення та структурування архіву. FileOutputStream записує байти у файл
        PrintWriter out = new PrintWriter(zout); // PrintWriter - обгортка для потоків, яка дозволяє записувати текстові дані у вказаний потік
        while (true) {
            StringBuilder fileContent = new StringBuilder();
            System.out.print("Введіть ім'я файлу всередині архіву (або 'Q!' для завершення): ");
            String fileName = reader.readLine();
            if ("Q!".equals(fileName)) {
                break;
            }
            ZipEntry zipEntry = new ZipEntry(fileName); // Створення запису для файлу в архіві
            zout.putNextEntry(zipEntry); // Додавання запису до архіву
            System.out.println("Введіть дані для файлу " + fileName + ". Для завершення введення файлу введіть 'q!' на початку рядка.");


            while (true) {
                String input = reader.readLine();
                if (input.startsWith("q!")) {
                    break;
                }
                fileContent.append(input).append(System.lineSeparator());
            }

            out.println(fileContent); // записує fileContent у поточний файл всередині архіву.
            out.flush(); // забезпечує запис усіх даних у потік, щоб вони потрапили в архів.
            zout.closeEntry(); // закриває поточний файл у архіві (це означає, що ми завершили запис у поточний файл)

            System.out.println("Файл " + fileName + " додано в архів.");

        }
        out.close();
        zout.close();
        System.out.println("Архів " + archiveName + " успішно створено.");
    }
}
