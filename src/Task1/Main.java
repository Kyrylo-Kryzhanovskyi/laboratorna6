package Task1;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String pathSource1 = "/Task1/source.txt";
        String pathSource = System.getProperty("user.dir") + "/src" + pathSource1.replace('/', File.separatorChar);
        String pathTarget1 = "/Task1/result.txt";
        String pathTarget = System.getProperty("user.dir") + "/src" + pathTarget1.replace('/', File.separatorChar);
        System.out.println(pathTarget);
        System.out.println(pathSource);
        StringBuilder allInfo = obtaining(pathSource);
        System.out.println(allInfo);
        StringBuilder resultText = textProcessing(allInfo);
        System.out.println(resultText);
        embedding(pathTarget, resultText);
    }
    static StringBuilder obtaining(String pathSource) {
        StringBuilder lines = new StringBuilder();
        try (BufferedReader inputStream = new BufferedReader(new FileReader(pathSource))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                lines.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    static StringBuilder textProcessing(StringBuilder inputText) {
        StringBuilder outputText = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Za-z]+");
        Matcher matcher = pattern.matcher(inputText);
        while (matcher.find()) {
            outputText.append(matcher.group()).append(" ");
        }
        return outputText;
    }
    static void embedding(String pathResult, StringBuilder resultText) {

        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(pathResult))) {
            int c;
            for (int i = 0; i < resultText.length(); i++) {
                outputStream.write(resultText.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
