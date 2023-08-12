import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Tạo một luồng mới để xử lý yêu cầu của client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Đọc dữ liệu từ client
            String inputLine1 = in.readLine();
            String inputLine2 = in.readLine();

            // Chuyển chuỗi sang số nguyên
            int number1 = Integer.parseInt(inputLine1);
            int number2 = Integer.parseInt(inputLine2);

            // Thực hiện phép tính tổng
            int sum = number1 + number2;

            // Gửi kết quả về client
            out.println("Tong = " + sum);

            // Đóng kết nối với client
            clientSocket.close();
            System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
