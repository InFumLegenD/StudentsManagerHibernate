import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import dao.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;

public class AddMainMenu_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_fakultet;

    @FXML
    private Button add_fakultet;

    @FXML
    private DatePicker date_potok;

    @FXML
    private Button add_potok;

    @FXML
    private ComboBox<String> fakultet_combobox;

    @FXML
    private TextField name_subject;

    @FXML
    private Button add_subject;

    @FXML
    private Button add_group;

    @FXML
    private Button add_student;

    @FXML
    private ComboBox<Integer> potok_combobox;

    @FXML
    private ComboBox<Integer> group_combobox;

    @FXML
    private TextField FIO_text;

    @FXML
    private Button add_session;

    @FXML
    private ComboBox<String> student_combobox;

    @FXML
    private DatePicker date_session;

    @FXML
    private ComboBox<String> subject_combobox;

    @FXML
    private TextField mark_text;
    @FXML
    private Button Back_to_main;
    @FXML
    void initialize() {
        Back_to_main.setOnAction(event -> {
            Back_to_main.getScene().getWindow().hide();
            Stage primaryStage= new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(new Scene(root,700,400));
            primaryStage.setTitle("Главное меню");
            primaryStage.show();

        });
        add_session.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    Session session = HibernateSessionFactory.getSessionFactory().openSession();
                    session.beginTransaction();
                    SessionsEntity sessionEntity= new SessionsEntity();

                    sessionEntity.setDate(date_session.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    sessionEntity.setMark(Integer.parseInt(mark_text.getText()));
                    sessionEntity.setStudent(student_combobox.getValue());
                    sessionEntity.setSubject(subject_combobox.getValue());

                    session.save(sessionEntity);
                    session.getTransaction().commit();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно");
                    alert.setContentText("Вы успешно занесли данные");
                    alert.showAndWait();
                    session.close();

                }
                catch (NullPointerException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setContentText("Вы не ввели данные!!");
                    alert.showAndWait();
                }
            }
        });
        subject_combobox.setOnShown(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                subject_combobox.getItems().clear();
                Session session = HibernateSessionFactory.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("select nameSubject from SubjectsEntity ");

                List list = query.list();
                ObservableList<String> langs = FXCollections.observableArrayList(list);
                subject_combobox.setItems(langs);
                session.close();
            }
        });
        student_combobox.setOnShown(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                student_combobox.getItems().clear();
                Session session = HibernateSessionFactory.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("select fio from StudentsEntity ");

                List list = query.list();
                ObservableList<String> langs = FXCollections.observableArrayList(list);
                student_combobox.setItems(langs);
                session.close();
            }
        });
        potok_combobox.setOnShown(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                potok_combobox.getItems().clear();
                Session session = HibernateSessionFactory.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("select idPotok from PotokEntity ");

                List list = query.list();
                ObservableList<Integer> langs = FXCollections.observableArrayList(list);
                potok_combobox.setItems(langs);
//              fakultet_combobox.setValue(langs.get(0).toString());
                session.close();
            }
        });
        add_group.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    Session session = HibernateSessionFactory.getSessionFactory().openSession();
                    session.beginTransaction();
                    GroupsEntity groupEntity = new GroupsEntity();

                    groupEntity.setPotok_id(potok_combobox.getValue());


                    session.save(groupEntity);
                    session.getTransaction().commit();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно");
                    alert.setContentText("Вы успешно занесли данные");
                    alert.showAndWait();
                    session.close();

                }
                catch (NullPointerException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setContentText("Вы не ввели данные!!");
                    alert.showAndWait();
                }
            }
        });
        group_combobox.setOnShown(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                group_combobox.getItems().clear();
                Session session = HibernateSessionFactory.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("select idGroup from GroupsEntity ");

                List list = query.list();
                ObservableList<Integer> langs = FXCollections.observableArrayList(list);
                group_combobox.setItems(langs);
//              fakultet_combobox.setValue(langs.get(0).toString());
                session.close();
            }
        });
        add_student.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    Session session = HibernateSessionFactory.getSessionFactory().openSession();
                    session.beginTransaction();
                    StudentsEntity studentEntity = new StudentsEntity();

                    studentEntity.setFio(FIO_text.getText());
                    studentEntity.setGroup(group_combobox.getValue());


                    session.save(studentEntity);
                    session.getTransaction().commit();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно");
                    alert.setContentText("Вы успешно занесли данные");
                    alert.showAndWait();
                    session.close();

                }
                catch (NullPointerException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setContentText("Вы не ввели данные!!");
                    alert.showAndWait();
                }
            }
        });
        add_subject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    Session session = HibernateSessionFactory.getSessionFactory().openSession();
                    session.beginTransaction();
                    SubjectsEntity subjectEntity = new SubjectsEntity();

                    subjectEntity.setNameSubject(name_subject.getText());


                    session.save(subjectEntity);
                    session.getTransaction().commit();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно");
                    alert.setContentText("Вы успешно занесли данные");
                    alert.showAndWait();
                    session.close();

                }
                catch (NullPointerException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setContentText("Вы не ввели данные!!");
                    alert.showAndWait();
                }
                name_subject.setText("");
            }
        });
        fakultet_combobox.setOnShown(new EventHandler<Event>() {
            public void handle(Event event) {
                fakultet_combobox.getItems().clear();
                Session session = HibernateSessionFactory.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("select nameFakultet from FakultetEntity ");

                List list = query.list();
                ObservableList<String> langs = FXCollections.observableArrayList(list);
              fakultet_combobox.setItems(langs);
//              fakultet_combobox.setValue(langs.get(0).toString());
                session.close();
            }
        });
        date_potok.setOnShown(new EventHandler<Event>() {
            public void handle(Event event) {
                date_potok.setValue(LocalDate.now());
            }
        });
        add_potok.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try
                {
                    Session session = HibernateSessionFactory.getSessionFactory().openSession();
                    session.beginTransaction();
                    PotokEntity potokEntity = new PotokEntity();

                    potokEntity.setYear(date_potok.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    potokEntity.setFakultet(fakultet_combobox.getValue());

                    session.save(potokEntity);
                    session.getTransaction().commit();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно");
                    alert.setContentText("Вы успешно занесли данные");
                    alert.showAndWait();
                    session.close();

                }
                catch (NullPointerException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setContentText("Вы не ввели данные!!");
                    alert.showAndWait();
                }
            }

        });
       add_fakultet.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent event) {
               try
               {
                   Session session = HibernateSessionFactory.getSessionFactory().openSession();
                   session.beginTransaction();
                   FakultetEntity fakultetEntity = new FakultetEntity();

                   fakultetEntity.setNameFakultet(name_fakultet.getText());


                   session.save(fakultetEntity);
                   session.getTransaction().commit();
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Успешно");
                   alert.setContentText("Вы успешно занесли данные");
                   alert.showAndWait();
                   session.close();

               }
               catch (NullPointerException e){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Ошибка");
                   alert.setContentText("Вы не ввели название!!");
                   alert.showAndWait();
               }
           }
       });

    }
}

