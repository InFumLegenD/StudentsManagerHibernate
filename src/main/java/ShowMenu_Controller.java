import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import dao.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;

public class ShowMenu_Controller {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Button back_button;

    @FXML
    private URL location;

    @FXML
    private TableView<SessionsEntity> table;
    @FXML
    private Button show_button;

    @FXML
    private Button del_button;
    @FXML
    private TableColumn<SessionsEntity, Integer> id_col;
    @FXML
    private TableColumn<SessionsEntity, String> student_col;

    @FXML
    private TableColumn<SessionsEntity, Integer> group_col;

    @FXML
    private TableColumn<SessionsEntity, String> subject_col;

    @FXML
    private TableColumn<SessionsEntity, Integer> mark_col;

    @FXML
    private TableColumn<SessionsEntity, Date> date_col;
    ResultSet rs = null;
    private ObservableList<SessionsEntity> data;
    @FXML
    void initialize() {
        back_button.setOnAction(event -> {
            back_button.getScene().getWindow().hide();
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
           Session session = HibernateSessionFactory.getSessionFactory().openSession();
           session.beginTransaction();

           student_col.setCellValueFactory(new PropertyValueFactory<>("student"));
           id_col.setCellValueFactory(new PropertyValueFactory<>("idSession"));
           mark_col.setCellValueFactory(new PropertyValueFactory<>("mark"));
           date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
           subject_col.setCellValueFactory(new PropertyValueFactory<>("subject"));
//           Query query = session.createQuery("select SessionsEntity.student,StudentsEntity.group, SessionsEntity.subject,SessionsEntity.mark, SessionsEntity.date from SessionsEntity inner join StudentsEntity on (StudentsEntity.fio = SessionsEntity.student) ");
//           Query query = session.createQuery("select student,subject,mark,date FROM SessionsEntity");
//
//           List list = query.list();
//
//           ObservableList<SessionsEntity> langs = FXCollections.observableArrayList(list);
//           System.out.println(list);
//            table.getColumns().add(student_col);
//           table.getColumns().add(mark_col);
//           table.getColumns().add(date_col);
//           table.getColumns().add(subject_col);
                table.setItems(getEnseignant());
            del_button.setOnAction(event -> {
                Session session2 = HibernateSessionFactory.getSessionFactory().openSession();
                session2.beginTransaction();
                TableView.TableViewSelectionModel<SessionsEntity> selectionModel = table.getSelectionModel();


                SessionsEntity selectedItem=table.getSelectionModel().getSelectedItem();
                        table.getItems().remove(selectedItem);
                        Query query = session2.createQuery("DELETE FROM SessionsEntity WHERE idSession = :idSession");
                        query.setParameter("idSession", selectedItem.getIdSession());
                        query.executeUpdate();
                        session2.close();

            });

           session.close();



    }
    public void del_rows(){

    }
    public ObservableList<SessionsEntity> getEnseignant() {
        ObservableList<SessionsEntity> enseignantList = FXCollections.observableArrayList();
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<SessionsEntity> eList = session.
                createCriteria(SessionsEntity.class).list();
        for (SessionsEntity ent : eList) {
            enseignantList.add(ent);
        }
        return enseignantList;
    }
}
