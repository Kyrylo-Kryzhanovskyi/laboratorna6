package Task56;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
// cd src
// javac Task56/Task6.java
// java Task56/Task6 /Task56/zipped.zip
public class Task6 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Помилка: вкажіть шлях до архіву. Приклад: /Task56/zipped.zip");
            return;
        }
        String pathZip = System.getProperty("user.dir") +  args[0].replace('/', File.separatorChar);
        ZipInputStream zin = new ZipInputStream( new FileInputStream(pathZip) );
        BufferedReader zs = new BufferedReader( new InputStreamReader( zin ) );
        ZipEntry ze;
        while ( ( ze = zin.getNextEntry() ) != null ) {
            PrintWriter outputStream = new PrintWriter(new FileWriter( (System.getProperty("user.dir") + "/Task56/" + ze.getName()).replace('/', File.separatorChar)));
            String s;
            while( (s = zs.readLine()) != null ){
                outputStream.println(s);
            }
            outputStream.close();
            zin.closeEntry();
        }
    }
}