package application;
	

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	   static final String DB_URL = "jdbc:mysql://localhost:3306/videogiochi";
	   static final String USER = "root";
	   static final String PASS = "root";
	@Override
	public void start(Stage primaryStage) {
		try {
			 Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		      ) {		      
		         String sql = "select 1 from dual";
		         stmt.executeQuery(sql);
		         System.out.println("Database created successfully...");   	  
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
		 launch(args);
	}
	
	
	
	

}