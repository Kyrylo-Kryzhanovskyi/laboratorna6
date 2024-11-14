package Task4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        byte[] byteArr = new byte[64];
        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = (byte) (i + 1);
        }
        String path = System.getProperty("user.dir") + "/src/Task4/binaryFile.dat" ;
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(byteArr);
            System.out.println("Початковий масив записаний у файл:\n" + Arrays.toString(byteArr));
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            for (int i = 1; i < byteArr.length; i += 2) {
                raf.seek(i);
                raf.writeByte(-1);
            }
            System.out.println("Значення байтів із парними індексами змінено.");
        } catch (IOException e) {
            System.err.println("Помилка модифікації файлу: " + e.getMessage());
        }
        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            byte [] modArr = new byte[64];
            raf.readFully(modArr);
            System.out.println("Змінений масив записаний у файл:\n" + Arrays.toString(modArr));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
