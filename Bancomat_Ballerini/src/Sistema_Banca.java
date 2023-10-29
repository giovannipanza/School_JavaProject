import java.util.*;

public class Sistema_Banca {
    
    private Scanner sc;
    private double saldo;
    private int scelta;

    public Sistema_Banca() {
        sc = new Scanner(System.in);
        saldo = 1000;
        scelta = 0;
    }

    public int getScelta() {
        return scelta;
    }

    public void setScelta(int scelta) {
        this.scelta = scelta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void sistema(){
    do{
        menu();
        switch(scelta){
            case 1: Prelievo(); break;
            case 2: Versamento(); break;
            case 3: Saldo(); break;
            default: 
                System.out.println("Arrivederci");
                break;
        }
    }
    while(scelta != 4);
    }

    public void Prelievo(){
        System.out.println("Inserire l'importo da prelevare");
        int temp_rem = sc.nextInt();
        if(temp_rem >= saldo){
            System.out.println("Non ci sono abbastanza soldi.");
        }
        else{
            saldo -= temp_rem;
        }
    }

    public void Versamento(){
        System.out.println("Inserire l'importo da Versare");
        int temp_add = sc.nextInt();
        if (temp_add > 0) {
            saldo += temp_add;
        }
    }

    public void Saldo(){
        System.out.println("Saldo attuale: "+saldo);
    }

    public void menu(){
        System.out.println("+---------------+");
        System.out.println("| 1) Prelievo   |");
        System.out.println("| 1) Versamento |");
        System.out.println("| 3) Saldo      |");
        System.out.println("| 4) Esci       |");
        System.out.print("+---------------+--> ");
    }
    
}

