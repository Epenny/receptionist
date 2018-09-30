package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import library.Users;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private TextField idField;
    
    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField pagesField;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Users> TableView;
    
    @FXML
    private TableColumn<Users, Integer> idColumn;
    
    @FXML
    private TableColumn<Users, String> titleColumn;

    @FXML
    private TableColumn<Users, String> authorColumn;

    @FXML
    private TableColumn<Users, Integer> yearColumn;

    @FXML
    private TableColumn<Users, Integer> pagesColumn;

    @FXML
    private void insertButton() {
    	String query = "insert into books values("+idField.getText()+",'"+titleField.getText()+"','"+authorField.getText()+"',"+yearField.getText()+","+pagesField.getText()+")";
    	executeQuery(query);
    	showBooks();
    }
    
    
    @FXML 
    private void updateButton() {
    String query = "UPDATE books SET Title='"+titleField.getText()+"',Author='"+authorField.getText()+"',Year="+yearField.getText()+",Pages="+pagesField.getText()+" WHERE ID="+idField.getText()+"";
    executeQuery(query);
	showBooks();
    }
    
    @FXML
    private void deleteButton() {
    	String query = "DELETE FROM books WHERE ID="+idField.getText()+"";
    	executeQuery(query);
    	showBooks();
    }
    @FXML
//    private void statsButton() {
//
//
//    }
    public void statsButton(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Chart.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void logoutButton(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void executeQuery(String query) {
    	Connection conn = getConnection();
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	showBooks();
    }
    
    public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/receptionist","root","");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public ObservableList<Users> getBooksList(){
    	ObservableList<Users> booksList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM books Acs";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Users users;
			while(rs.next()) {
				users = new Users(rs.getInt("Id"),rs.getString("Title"),rs.getString("Author"),rs.getInt("Year"),rs.getInt("Pages"));
				booksList.add(users);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return booksList;
    }
    
    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showBooks() {
    	ObservableList<Users> list = getBooksList();
    	
    	idColumn.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
    	titleColumn.setCellValueFactory(new PropertyValueFactory<Users,String>("title"));
    	authorColumn.setCellValueFactory(new PropertyValueFactory<Users,String>("author"));
    	yearColumn.setCellValueFactory(new PropertyValueFactory<Users,Integer>("year"));
    	pagesColumn.setCellValueFactory(new PropertyValueFactory<Users,Integer>("pages"));
    	
    	TableView.setItems(list);
    }

}
