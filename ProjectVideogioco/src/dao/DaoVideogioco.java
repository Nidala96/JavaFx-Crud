package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import database.Database;
import entities.Videogioco;
import interfaces.IDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoVideogioco implements IDao
{

	private Database db;

	public DaoVideogioco(String percorso, String username, String password)
	{
		db = new Database(percorso, username, password);
	}

	@Override
	public List<Videogioco> read() 
	{
		List<Videogioco> elenco = new ArrayList<Videogioco>();
		Videogioco v = null;

		try
		{
			db.getConnection();
			String query = "select * from videogioco";
			PreparedStatement ps = db.getC().prepareStatement(query);
			ResultSet tabella = ps.executeQuery();

			while(tabella.next())
			{
				v = new Videogioco(
						tabella.getInt(1),
						tabella.getString(2),
						tabella.getString(3),
						tabella.getString(4),
						tabella.getInt(5),
						tabella.getString(6),
						tabella.getDouble(7),
						tabella.getDouble(8)
						);

				if(v != null)
				{
					elenco.add(v);
				}
			}

			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			db.closeConnection();
		}

		return elenco;
	}

	@Override
	public void create(Videogioco v) 
	{
		//Preparo una variabile String con la query da lanciare su MySql
		String query = "insert into videogioco(titolo, piattaforma, genere, annoPubblicazione, sviluppatore, valutazione, prezzo) values\r\n"
				+ "(?,?,?,?,?,?,?)";

		try
		{
			db.getConnection();
			PreparedStatement ps = db.getC().prepareStatement(query);

			ps.setString(1, v.getTitolo());
			ps.setString(2, v.getPiattaforma());
			ps.setString(3, v.getGenere());
			ps.setInt(4, v.getAnnoPubblicazione());
			ps.setString(5, v.getSviluppatore() );
			ps.setDouble(6, v.getValutazione() );
			ps.setDouble(7, v.getPrezzo());

			ps.executeUpdate();

			System.out.println("Videogioco inserito correttamente");
		}
		catch(Exception e)
		{
			System.out.println("Impossibile inserire Videogioco");
			e.printStackTrace();
		}
		finally
		{
			db.closeConnection();
		}
	}

	@Override
    public void update(Videogioco v) 
    {
        String query = "UPDATE videogioco SET titolo = ?, piattaforma = ?, genere = ?, annoPubblicazione = ?, sviluppatore = ?, valutazione = ?, prezzo = ? WHERE id = ?";

        try
        {
            db.getConnection();
            PreparedStatement ps = db.getC().prepareStatement(query);

            ps.setString(1, v.getTitolo());
			ps.setString(2, v.getPiattaforma());
			ps.setString(3, v.getGenere());
			ps.setInt(4, v.getAnnoPubblicazione());
			ps.setString(5, v.getSviluppatore() );
			ps.setDouble(6, v.getValutazione() );
			ps.setDouble(7, v.getPrezzo());
            ps.setInt(8, v.getId());

            ps.executeUpdate();

            System.out.println("Videogioco aggiornato correttamente");
        }
        catch(Exception e)
        {
            System.out.println("Impossibile aggiornare Videogioco");
            e.printStackTrace();
        }
        finally
        {
            db.closeConnection();
        }
    }

	@Override
	public void delete(int id) 
	{
		String query = "delete from videogioco where id =" + id;

		try
		{
			db.getConnection();
			PreparedStatement ps = db.getC().prepareStatement(query);

			ps.executeUpdate();

			System.out.println("Videogioco cancellato correttamente");
		}
		catch(Exception e)
		{
			System.out.println("Impossibile cancellare Videogioco");
			e.printStackTrace();
		}
		finally
		{
			db.closeConnection();
		}
	}

	public void update(Videogioco v, String campoDaModificare) {
		String query = "UPDATE videogioco SET " ;
		switch (campoDaModificare) {
		case "titolo":
			query += "titolo = ? WHERE id = ?";

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setString(1, v.getTitolo());
				ps.setInt(2, v.getId());

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "piattaforma":
			query += "piattaforma = ? WHERE id = ?";

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setString(1, v.getPiattaforma());
				ps.setInt(2, v.getId());

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "genere":
			query += "genere = ? WHERE id = ?";

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setString(1, v.getGenere());
				ps.setInt(2, v.getId());

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "anno di pubblicazione":
			query += "annoPubblicazione = ? WHERE id = ?";

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setInt(1, v.getAnnoPubblicazione());
				ps.setInt(2, v.getId());

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "sviluppatore":
			query += "sviluppatore = ? WHERE id = ?";

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setString(1, v.getSviluppatore());
				ps.setInt(2, v.getId());

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "valutazione":
			query += "valutazione = ? WHERE id = ?";

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setDouble(1, v.getValutazione());
				ps.setInt(2, v.getId());

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "prezzo":
			query += "prezzo = ? WHERE id = ?";

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setDouble(1, v.getPrezzo());
				ps.setInt(2, v.getId());

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		default:
			break;
		}

	}

	public void update(int id, String campoDaModificare) {
		String query = "UPDATE videogioco SET " ;
		Scanner t = new Scanner(System.in);
		String modificaString;
		int modificaInt;
		double modificaDouble;

		switch (campoDaModificare) {
		case "titolo":
			query += "titolo = ? WHERE id = ?";
			System.out.println("Inserisci il nuovo titolo:");
			modificaString = t.nextLine();
			try
			{

				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setString(1, modificaString);
				ps.setInt(2, id);

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "piattaforma":
			query += "piattaforma = ? WHERE id = ?";

			System.out.println("Inserisci il nuovo titolo:");
			modificaString = t.nextLine();
			try
			{

				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setString(1, modificaString);
				ps.setInt(2, id);

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;


		case "genere":
			query += "genere = ? WHERE id = ?";
			System.out.println("Inserisci il nuovo titolo:");
			modificaString = t.nextLine();

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setString(1, modificaString);
				ps.setInt(2, id);

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "anno di pubblicazione":
			query += "annoPubblicazione = ? WHERE id = ?";
			System.out.println("Inserisci il nuovo titolo:");
			modificaInt = Integer.parseInt(t.nextLine()) ;
			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setInt(1, modificaInt);
				ps.setInt(2, id);

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "sviluppatore":
			query += "sviluppatore = ? WHERE id = ?";
			System.out.println("Inserisci il nuovo titolo:");
			modificaString = t.nextLine();
			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setString(1, modificaString);
				ps.setInt(2, id);

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "valutazione":
			query += "valutazione = ? WHERE id = ?";
			System.out.println("Inserisci il nuovo titolo:");
			modificaDouble = Double.parseDouble(t.nextLine());
			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setDouble(1, modificaDouble);
				ps.setInt(2, id);

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		case "prezzo":
			query += "prezzo = ? WHERE id = ?";
			System.out.println("Inserisci il nuovo titolo:");
			modificaDouble = Double.parseDouble(t.nextLine());

			try
			{
				db.getConnection();
				PreparedStatement ps = db.getC().prepareStatement(query);

				ps.setDouble(1, modificaDouble);
				ps.setInt(2, id);

				ps.executeUpdate();

				System.out.println("Videogioco aggiornato correttamente");
			}
			catch(Exception e)
			{
				System.out.println("Impossibile aggiornare Videogioco");
				e.printStackTrace();
			}
			finally
			{
				db.closeConnection();
			}
			break;

		default:
			break;
		}
		
		
	}
	
	@Override
    public void deleteBetween(int id1, int id2) 
    {
        String query = "delete from videogioco where id between ? and ?";

        try
        {
            db.getConnection();
            PreparedStatement ps = db.getC().prepareStatement(query);

            ps.setInt(1, id1);
            ps.setInt(2, id2);

            ps.executeUpdate();

            System.out.println("Videogioco cancellato correttamente");
        }
        catch(Exception e)
        {
            System.out.println("Impossibile cancellare Videogioco");
            e.printStackTrace();
        }
        finally
        {
            db.closeConnection();
        }

    }
	
	public void insertGames() {
		// TODO Auto-generated method stub
		 String fileName = "src/res/games.txt";
	        File file = new File(fileName);
	        String[] split;
	        try {
	            Scanner scanner = new Scanner(file);
	            
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                split = line.split(",");
	                System.out.println(split[0]);
	                Videogioco v = new Videogioco(split[0], split[1], split[2], Integer.parseInt(split[3]), split[4], Double.parseDouble(split[5]), Double.parseDouble(split[6]));   
	                create(v);
	            }
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
}

	@Override
	public void createUtente() {
		// TODO Auto-generated method stub
		
	}
	
	


}