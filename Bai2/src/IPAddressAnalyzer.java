import java.util.Scanner;

public class IPAddressAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap dia chi IP: ");
        String ipAddress = scanner.nextLine();

        if (isValidIPAddress(ipAddress)) {
            System.out.println("Dia chi IP hop le.");
            analyzeIPAddress(ipAddress);
        } else {
            System.out.println("Dia chi IP khong hop le.");
        }

        scanner.close();
    }

    // Phuong thuc kiem tra tinh hop le cua dia chi IP
    public static boolean isValidIPAddress(String ipAddress) {
        String[] parts = ipAddress.split("\\.");

        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            try {
                int value = Integer.parseInt(part);
                if (value < 0 || value > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    // Phuong thuc phan tich dia chi IP
    public static void analyzeIPAddress(String ipAddress) {
        String[] parts = ipAddress.split("\\.");

        System.out.println("Phan tich dia chi IP:");
        for (int i = 0; i < parts.length; i++) {
            System.out.println("Octet " + (i + 1) + ": " + parts[i]);
        }
    }
}
