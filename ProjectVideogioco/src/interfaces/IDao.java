package interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Videogioco;


public interface IDao {

	
	//CRUD
	//Read
	public List<Videogioco> read();
	//Create
	public void create(Videogioco v);
	//Update
	public void update(Videogioco v);
	//Delete
	public void delete(int id);
	
	default String stampaElenco() 
	{
		String ris = "";
	for(Videogioco v : read())
	{
		ris += v.toString();
	}
	return ris;
	}
	
	default String affordable() 
	{
	    String stampa = "";
	    String ris = "";
	    
	    ArrayList<Integer> ids = new ArrayList<Integer>();
	    Scanner tastiera = new Scanner(System.in);
	    System.out.println("Inserisci il tuo budget");
	    int i = 0;
	    
	    double budget = Double.parseDouble(tastiera.nextLine());
	    double iniz = budget;
	    do {
	        	int index = (int) (Math.random() * read().size());
	        	Videogioco v = read().get(index);
	        	
	        	if (v.getPrezzo() <= budget && !ids.contains(v.getId())) 
	        	{ 
	        		stampa += "★" +v.getTitolo() + " -" + v.getPrezzo() + "€\n";
	        		i++;
		        	
		            ids.add(v.getId());
		            budget -= v.getPrezzo();
		            
		            
		            
		           // Affordabili.add(v.getTitolo() + "\n");
		      
	        	}
	        	
	        	
	        	
	    	}while (budget >= prezzoMin() && i < read().size()); // && Affordabili.size() < read().size
	    
	    ris = "---------------\nSei rimasto con " + String.format("%.2f", budget) + " euro di budget" + "\n";
	    //tastiera.close();
	    return "Con un budget di " + iniz + "€\nhai potuto acquistare questi " + i + " giochi! \n---------------\n" + stampa + ris;
	 }
	default String prezzoMin2() 
    {
        String ris = "";
        double min = Double.MAX_VALUE;
        for (Videogioco v : read()) 
        {
            if (v.getPrezzo() <= min) 
            {
                min = v.getPrezzo();
                ris = "Il gioco meno costoso e' " + v.toString();

            }
        }
        return ris;
    }
	
		default Double prezzoMin() 
		{
	        double min = Double.MAX_VALUE;
	        for (Videogioco v : read()) 
	        {
	            if (v.getPrezzo() <= min) 
	            {
	                min = v.getPrezzo();
	            }
	        }
	        return min;
	    }
		
		default String prezzoMax() 
	    {
	        String ris = "";
	        double max = Double.MIN_VALUE;
	        for (Videogioco v : read()) 
	        {
	            if (v.getPrezzo() >= max) 
	            {
	                max = v.getPrezzo();
	                ris = "Il gioco più costoso e' " + v.toString();
	            }
	        }
	        return ris;
	    }
		
		default String trovaPerGenere(String genere) 
		{

	        String ris = "";
	        for (Videogioco v : read()) 
	        {
	            if (v.getGenere().toLowerCase().contains(genere.toLowerCase())) 
	            {
	                ris += v.toString();
	            }
	        }

	        return ris;
	    }

	    default String trovaPerConsole(String console) 
	    {

	        String ris = "";
	        for (Videogioco v : read()) {
	            if (v.getPiattaforma().toLowerCase().contains(console.toLowerCase())) 
	            {
	                ris += v.toString();
	            }
	        }

	        return ris;
	    }
		
		
		default String trovaPerNome(String nome)
		{
			String ris = "";
	        for (Videogioco v : read()) 
	        {
	            if (v.getTitolo().toLowerCase().contains(nome.toLowerCase())) 
	            {
	                ris += v.toString();
	            }
	        }

	        return "Cercavi questo? " + ris;
		}
		
		
		
		default String compara(String nome1, String nome2)
		{
			String ris = "";
			double voto1 = 0;
			double voto2 = 0;
			String temp1 = "";
			String temp2 = "";
	        for (Videogioco v : read()) 
	        {
	        	 if (v.getTitolo().toLowerCase().contains(nome1.toLowerCase())) 
		            {
		                ris += v.toString();
		                voto1 = v.getValutazione();
		                temp1 = v.toString();
		            }
	        	 
	        	 if (v.getTitolo().toLowerCase().contains(nome2.toLowerCase())) 
		            {
		                ris += v.toString();
		                voto2 = v.getValutazione();
		                temp2 = v.toString();
		            }
	        	 
	        	 if(voto1 > voto2)
	        	 {
	        		 ris = "Il migliore tra i due è: " + temp1;
	        	 }
	        	 else
	        	 {
	        		 ris = "Il migliore tra i due è: " + temp2;
	        	 }
	        	 
	        	 
	        }

	        return ris;
		}
		
		default String contacaratteri(String parola1) 
		{
		    
		    String ris = "";
		    String lettere = "";
		    int maxCount = 0;
		    int conta = 0;
		    String imigliori = "";
		    Videogioco maxVideogioco = null;

		    for (Videogioco v : read()) 
		    {
		        String titolo = v.getTitolo().toLowerCase();
		        int count = 0;
		        int length = Math.min(parola1.length(), titolo.length());
		        for (int i = 0; i < length; i++) 
		        {
		            if (parola1.charAt(i) == titolo.charAt(i)) 
		            {
		                lettere += parola1.charAt(i);
		                count++;
		            }
		        }
		        
//		        if(count > conta)
//		        {
//		        	System.out.println("Ciao");
//		        	conta = count;
//		        	imigliori += v.toString() + "\n" + "Con " + count + " lettere in comune \n";
//		        	
//		        }
		        
		        if (count > maxCount) 
		        {
		            maxCount = count;
		            maxVideogioco = v;
		        }
		    }
		    ris += "Il videogioco con il maggior numero di lettere in comune con \n\"" + parola1 + "\" è:\n" + maxVideogioco.toString() + "\nCon " + maxCount + " lettere in comune.\n";
		    
		    return ris + "\n" + imigliori;
		}
		
		
		
		
		default String scontoGiochi(String titolo) 
	    {
	        String ris = "";
	        int annoCorrente = 2023, sconto = 10;
	        double prezzoScontato;

	        for (Videogioco v : read()) 
	        {
	            if (v.getTitolo().equalsIgnoreCase(titolo)) 
	            {
	                int annoGioco = v.getAnnoPubblicazione();
	                if (annoCorrente - annoGioco >= 8) 
	                {
	                    prezzoScontato = v.getPrezzo() - sconto;
	                    ris = titolo + " e' scontato e costa " + String.format("%.2f", prezzoScontato);
	                } 
	                else 
	                {
	                    ris = titolo + " non e' scontato";
	                }
	                break; // Esci dal ciclo una volta trovato il videogioco corrispondente al titolo
	            }
		     }
		        return ris;
		 }
		
		
		
		
		
		
		
		
	
		void insertGames();
		void createUtente();
		
		void update(int id, String campoDaModificare);
		void deleteBetween(int id1, int id2);
	
	
	
	
	
	
	
	}

	
	
	
