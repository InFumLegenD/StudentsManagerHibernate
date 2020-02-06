import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.hibernate.Session;
import dao.FakultetEntity;
import utils.HibernateSessionFactory;

public class Main extends Application {

    public static void main(String[] args) {

        Application.launch(args);



    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        primaryStage.setScene(new Scene(root,700,400));
        primaryStage.setTitle("Главное меню");
        primaryStage.show();


    }
}

