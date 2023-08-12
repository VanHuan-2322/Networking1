import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Lắng nghe và xử lý tin nhắn từ Client
            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }
                System.out.println("Client says: " + message);
                out.println("Server: " + message); // Phản hồi lại Client
            }

            // Đóng kết nối
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
