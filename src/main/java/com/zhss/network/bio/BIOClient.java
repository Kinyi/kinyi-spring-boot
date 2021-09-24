package com.zhss.network.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Kinyi_Chan
 * @since 2021-09-22
 */
public class BIOClient {

    private void input() {
        try (Socket socket = new Socket("localhost", 8989)) {
            OutputStream outputStream = socket.getOutputStream();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String content = scanner.next();
                outputStream.write(content.getBytes());

                if ("bye".equals(content)) {
                    scanner.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BIOClient().input();
    }
}
