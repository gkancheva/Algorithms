package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L03_RecursiveDrawing {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());

        draw(num);
    }

    private static void draw(int num) {
        if(num <= 0){
            return;
        }
        String result = new String(new char[num])
                .replace("\0", "*");
        System.out.println(result);
        draw(num - 1);
        result = new String(new char[num])
                .replace("\0", "#");
        System.out.println(result);
    }
}