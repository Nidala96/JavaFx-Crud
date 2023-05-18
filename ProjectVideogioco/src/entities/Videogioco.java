package entities;

public class Videogioco 
{
	private int id;
    private String titolo;
    private String piattaforma;
    private String genere;
    private int annoPubblicazione;
    private String sviluppatore;
    private double valutazione;
    private double prezzo;

    public Videogioco(int id, String titolo, String piattaforma, String genere, int annoPubblicazione, String sviluppatore, double valutazione, double prezzo) 
    {
        setId(id);
        setTitolo(titolo);
        setPiattaforma(piattaforma);
        setGenere(genere);
        setAnnoPubblicazione(annoPubblicazione);
        setSviluppatore(sviluppatore);
        setValutazione(valutazione);
        setPrezzo(prezzo);
    }
    
    public Videogioco(String titolo, String piattaforma, String genere, int annoPubblicazione, String sviluppatore, double valutazione, double prezzo) 
    {
        setTitolo(titolo);
        setPiattaforma(piattaforma);
        setGenere(genere);
        setAnnoPubblicazione(annoPubblicazione);
        setSviluppatore(sviluppatore);
        setValutazione(valutazione);
        setPrezzo(prezzo);
    }
    
    public Videogioco ()
    {
    	
    }
    
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getTitolo() 
    {
        return titolo;
    }

    public void setTitolo(String titolo) 
    {
        this.titolo = titolo;
    }

    public String getPiattaforma() 
    {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) 
    {
        this.piattaforma = piattaforma;
    }

    public String getGenere() 
    {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getSviluppatore() {
        return sviluppatore;
    }

    public void setSviluppatore(String sviluppatore) {
        this.sviluppatore = sviluppatore;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
    public String toString() 
    {
        return "\nID: " 						+ 	id  				+ 
        		"\nTitolo: " 					+ 	titolo 				+ 
        		"\nPiattaforma: " 				+ 	piattaforma 		+ 
        		"\nGenere: " 					+ 	genere 				+ 
        		"\nAnno di pubblicazione: " 	+ 	annoPubblicazione +
        		"\nSviluppatore: " 				+ 	sviluppatore 		+ 
        		"\nValutazione: " 				+ 	valutazione 		+ 
        		"\nPrezzo: " 					+   prezzo 				+ "\n-----------\n";
    }
}
