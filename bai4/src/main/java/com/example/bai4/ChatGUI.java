package com.example.bai4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ChatGUI extends Application {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private TextArea messageArea;
    private TextField inputField;
    private Button sendButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            String serverAddress = "localhost";
            int port = 8080;

            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            BorderPane root = new BorderPane();

            messageArea = new TextArea();
            messageArea.setEditable(false);
            root.setCenter(messageArea);

            inputField = new TextField();
            inputField.setOnAction(e -> sendMessage());
            sendButton = new Button("Send");
            sendButton.setOnAction(e -> sendMessage());

            BorderPane bottomPane = new BorderPane();
            bottomPane.setCenter(inputField);
            bottomPane.setRight(sendButton);
            root.setBottom(bottomPane);

            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.setTitle("Chat Application");
            primaryStage.show();

            new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readLine();
                        if (message == null) {
                            break;
                        }
                        Platform.runLater(() -> messageArea.appendText("Server: " + message + "\n"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            primaryStage.setOnCloseRequest(e -> {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = inputField.getText();
        out.println(message);
        inputField.clear();
        messageArea.appendText("You: " + message + "\n");
    }
}
