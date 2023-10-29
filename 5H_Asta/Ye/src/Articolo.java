public class Articolo {
    private  double costoIniziale;
    private double costoCorente;
     private int id;
     private int idClient;

    public Articolo(double costoIniziale, int id) {
        this.costoIniziale = costoIniziale;
        this.costoCorente =costoIniziale;
        this.id = id;
    }

    public double getCostoIniziale() {
        return costoIniziale;
    }

    public double getCostoCorente() {
        return costoCorente;
    }

    public int getId() {
        return id;
    }

    public void setCostoIniziale(double costoIniziale) {
        this.costoIniziale = costoIniziale;
    }

    public void setCostoCorente(double costoCorente) {
        this.costoCorente = costoCorente;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean controloCosto(double   cifranuovo,int idClient){
        if ( costoCorente< cifranuovo){
            this.idClient=idClient;
            costoCorente=cifranuovo;
            return true;
        }else{
            return false   ;
        }
    }


    @Override
    public String toString() {
        return "Articolo{" +
                "costoIniziale=" + costoIniziale +
                ", costoCorente=" + costoCorente +
                ", id=" + id +
                '}';
    }

}
