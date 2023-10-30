public class OggettoAsta {
    private String oggettoAllAsta;
    private  double prezzoPartenza;

    private int numeroOfferta=0;
    private final int  numeroOfferteMax=4;

    //prezzo massimo che l'ultimo cliente ha offerto
    private  double prezzoMassimo;

    public OggettoAsta(String oggettoAllAsta, double prezzoPartenza) {
        this.oggettoAllAsta = oggettoAllAsta;
        this.prezzoPartenza = prezzoPartenza;
        this.prezzoMassimo = prezzoPartenza;
    }

    public String getOggettoAllAsta() {
        return oggettoAllAsta;
    }

    public double getPrezzoPartenza() {
        return prezzoPartenza;
    }

    public void setPrezzoMassimo(double prezzoMassimo) {
        this.prezzoMassimo = prezzoMassimo;
    }

    public  double getPrezzoMassimo() {
        return prezzoMassimo;
    }

    public int getNumeroOfferteMax() {
        return numeroOfferteMax;
    }

    public void setNumeroOfferta()
    {
        numeroOfferta++;
    }

    public int getNumeroOfferta()
    {
        return numeroOfferta;
    }

}
