import java.util.Scanner;

public class ThreadPersona implements Runnable {
    private String nome;
    private int id;

    private Bo bo;
    public ThreadPersona(String nome,Bo bo) {
        this.nome = nome;
        this.bo = bo;
    }
    @Override
    public void run() {
        Scanner s = new Scanner(System.in);
        synchronized (bo) {
            System.out.println("inserisci dati");
                bo.stampa(s.nextLine());

        }

    }
}
