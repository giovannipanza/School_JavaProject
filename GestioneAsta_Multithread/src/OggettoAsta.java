public class OggettoAsta {
    private String oggettoAllAsta;
    private  double prezzoPartenza;

    //prezzo massimo che l'ultimo cliente ha offerto
    private  double prezzoMassimo;

    public OggettoAsta(String oggettoAllAsta, double prezzoPartenza, double prezzoMassimo) {
        this.oggettoAllAsta = oggettoAllAsta;
        this.prezzoPartenza = prezzoPartenza;
        this.prezzoMassimo = prezzoMassimo;
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

}
