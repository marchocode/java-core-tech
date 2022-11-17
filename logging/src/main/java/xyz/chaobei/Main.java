package xyz.chaobei;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        InputStream inputStream = ClassLoader.getSystemResourceAsStream("test.txt");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            System.out.printf(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 自动关闭
        try (InputStream inputStream1 = ClassLoader.getSystemResourceAsStream("test.txt")) {
            inputStream1.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}