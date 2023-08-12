module com.example.bai4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bai4 to javafx.fxml;
    exports com.example.bai4;
}