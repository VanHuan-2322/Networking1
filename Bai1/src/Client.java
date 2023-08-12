import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 8080;

        try {
            // Tạo kết nối đến Server
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to Server");

            // Tạo luồng để gửi dữ liệu đến Server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Đọc hai số nguyên từ người dùng
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nhap so thu nhat: ");
            String number1 = br.readLine();
            System.out.print("Nhap so thu hai: ");
            String number2 = br.readLine();

            // Gửi hai số nguyên đến Server
            out.println(number1);
            out.println(number2);

            // Nhận kết quả từ Server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = in.readLine();
            System.out.println("Ket qua tu Server: " + result);

            // Đóng kết nối
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
