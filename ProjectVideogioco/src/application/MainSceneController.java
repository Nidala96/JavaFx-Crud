package application;


import java.awt.Button;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import dao.DaoVideogioco;
import database.Database;
import entities.Videogioco;
import interfaces.IDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class MainSceneController implements Initializable {

    

	@FXML
    private TextField tfId;

    @FXML
    private TextField tftitolo;

    @FXML
    private TextField tfpiattaforma;

    @FXML
    private TextField tfgenere;

    @FXML
    private TextField tfanno;

    @FXML
    private TextField tfsviluppatore;

    @FXML
    private TextField tfvalutazione;

    @FXML
    private TextField tfprezzo;

    @FXML
    private TableView<Videogioco> videogiochitable;

    @FXML
    private TableColumn<Videogioco, Integer> colid;

    @FXML
    private TableColumn<Videogioco, String> coltitolo;

    @FXML
    private TableColumn<Videogioco, String> colpiattaforma;

    @FXML
    private TableColumn<Videogioco, String> colgenere;

    @FXML
    private TableColumn<Videogioco, Integer> colanno;

    @FXML
    private TableColumn<Videogioco, String> colsviluppatore;

    @FXML
    private TableColumn<Videogioco, Double> colvalutazione;

    @FXML
    private TableColumn<Videogioco, Double> colprezzo;
    
    @FXML
    private Text textbox;
    
    @FXML
    private TextField ftone;

    @FXML
    private TextField fttwo;

   

    
    IDao dao = new DaoVideogioco("videogiochi", "root", "root");
    
    
    public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videogioco","root","root");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
   
    public ObservableList<Videogioco> getVideogiocoList() {
    	ObservableList<Videogioco> giochiList = FXCollections.observableArrayList();
    	String nomeDb = "videogiochi";
    	Database db = new Database(nomeDb, "root", "root");
    	
    	String query = "SELECT * from videogioco";
    	
    	
    	try {
    		db.getConnection();
			PreparedStatement ps = db.getC().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
    		
    		Videogioco v;
    		while(rs.next()) {
    			v = new Videogioco(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getDouble(7),
						rs.getDouble(8)
						);
    			giochiList.add(v);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally{
    	}
    	
		return giochiList;
    	
    }
    
    public void showGiochi() {
    	ObservableList<Videogioco> list = getVideogiocoList();
    	
    	colid.setCellValueFactory(new PropertyValueFactory<Videogioco, Integer>("id"));
    	coltitolo.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("titolo"));
    	colpiattaforma.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("piattaforma"));
    	colgenere.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("genere"));
    	colanno.setCellValueFactory(new PropertyValueFactory<Videogioco, Integer>("AnnoPubblicazione"));
    	colsviluppatore.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("sviluppatore"));
    	colvalutazione.setCellValueFactory(new PropertyValueFactory<Videogioco, Double>("valutazione"));
    	colprezzo.setCellValueFactory(new PropertyValueFactory<Videogioco, Double>("prezzo"));
    	
    	videogiochitable.setItems(list);
    }
    
    public void showGiochi(ObservableList<Videogioco> list) {
    	
    	
    	colid.setCellValueFactory(new PropertyValueFactory<Videogioco, Integer>("id"));
    	coltitolo.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("titolo"));
    	colpiattaforma.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("piattaforma"));
    	colgenere.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("genere"));
    	colanno.setCellValueFactory(new PropertyValueFactory<Videogioco, Integer>("AnnoPubblicazione"));
    	colsviluppatore.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("sviluppatore"));
    	colvalutazione.setCellValueFactory(new PropertyValueFactory<Videogioco, Double>("valutazione"));
    	colprezzo.setCellValueFactory(new PropertyValueFactory<Videogioco, Double>("prezzo"));
    	
    	videogiochitable.setItems(list);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showGiochi();
	}
	
	public void insertgame() {
		Videogioco d = new Videogioco(tftitolo.getText(),tfpiattaforma.getText(),
									tfgenere.getText(),Integer.parseInt(tfanno.getText()),tfsviluppatore.getText(),
									Double.parseDouble(tfvalutazione.getText()),Double.parseDouble(tfprezzo.getText()));
		dao.create(d);
		showGiochi();
		
	}
	
	public void updategame() {
		Videogioco d = new Videogioco(Integer.parseInt(tfId.getText()),tftitolo.getText(),tfpiattaforma.getText(),
				tfgenere.getText(),Integer.parseInt(tfanno.getText()),tfsviluppatore.getText(),
				Double.parseDouble(tfvalutazione.getText()),Double.parseDouble(tfprezzo.getText()));
		dao.update(d);
		showGiochi();
	}
	
	public void deletegame() {
		dao.delete(Integer.parseInt(tfId.getText()));
		showGiochi();
		
	}
	
	public void max() {
		
		ObservableList<Videogioco> list = getVideogiocoList();
		ObservableList<Videogioco> giochiList = FXCollections.observableArrayList();
		double max = Double.MIN_VALUE;
		Videogioco maxPriceVideogioco = null;
		for (Videogioco v : list) {
		    if (v.getPrezzo() >= max) {
		        max = v.getPrezzo();
		        maxPriceVideogioco = v;
		    }
		}

		if (maxPriceVideogioco != null) {
		    giochiList.add(maxPriceVideogioco);
		}
		
		showGiochi(giochiList);
		
	}
	
	public void min() {
		ObservableList<Videogioco> list = getVideogiocoList();
		ObservableList<Videogioco> giochiList = FXCollections.observableArrayList();
		double max = Double.MAX_VALUE;
		Videogioco maxPriceVideogioco = null;
		for (Videogioco v : list) {
		    if (v.getPrezzo() <= max) {
		        max = v.getPrezzo();
		        maxPriceVideogioco = v;
		    }
		}

		if (maxPriceVideogioco != null) {
		    giochiList.add(maxPriceVideogioco);
		}
		
		showGiochi(giochiList);
	}
	
	public void btninsertall() {
		dao.insertGames();
		showGiochi();
	}
	
	public void btndeletetwo() {
		dao.deleteBetween(Integer.parseInt(ftone.getText()), Integer.parseInt(fttwo.getText()));
		showGiochi();
	}
	
	public void trovatitolo() {
		ObservableList<Videogioco> giochiList = FXCollections.observableArrayList();
    	String nomeDb = "videogiochi";
    	Database db = new Database(nomeDb, "root", "root");
    	
    	String query = "SELECT * from videogioco";
    	try {
    		db.getConnection();
			PreparedStatement ps = db.getC().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
    		
    		Videogioco v;
    		while(rs.next()) {
    			v = new Videogioco(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getDouble(7),
						rs.getDouble(8)
						);
    			if(v.getTitolo().equalsIgnoreCase(tftitolo.getText()) && tfpiattaforma.getText().equals("") && tfgenere.getText().equals("")) {
    				giochiList.add(v);
    			}
    			if(v.getPiattaforma().equalsIgnoreCase(tfpiattaforma.getText()) && tftitolo.getText().equals("") && tfgenere.getText().equals("")) {
    				giochiList.add(v);
    			}
    			if(v.getGenere().equalsIgnoreCase(tfgenere.getText()) && tftitolo.getText().equals("")&& tfpiattaforma.getText().equals("")) {
    				giochiList.add(v);
    			}
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	colid.setCellValueFactory(new PropertyValueFactory<Videogioco, Integer>("id"));
    	coltitolo.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("titolo"));
    	colpiattaforma.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("piattaforma"));
    	colgenere.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("genere"));
    	colanno.setCellValueFactory(new PropertyValueFactory<Videogioco, Integer>("AnnoPubblicazione"));
    	colsviluppatore.setCellValueFactory(new PropertyValueFactory<Videogioco, String>("sviluppatore"));
    	colvalutazione.setCellValueFactory(new PropertyValueFactory<Videogioco, Double>("valutazione"));
    	colprezzo.setCellValueFactory(new PropertyValueFactory<Videogioco, Double>("prezzo"));
    	
    	videogiochitable.setItems(giochiList);
    	
		
	}
	
	public void refresh() {
		showGiochi();
	}
		
	
}



