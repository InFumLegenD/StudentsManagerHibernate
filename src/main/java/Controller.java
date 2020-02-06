import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add_button;

    @FXML
    private Button Edit_button;

    @FXML
    private Button Show_buttton;

    @FXML
    void initialize() {
        Show_buttton.setOnAction(event -> {
                Show_buttton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Show_menu.fxml"));

            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage newWindow= new Stage();
            newWindow.setScene(new Scene(root));

            newWindow.showAndWait();
        });
    Add_button.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
           Add_button.getScene().getWindow().hide();

           FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Add_main_menu.fxml"));

            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           Parent root = loader.getRoot();
            Stage newWindow= new Stage();
            newWindow.setScene(new Scene(root));

            newWindow.showAndWait();

        }
    });
    }
}

