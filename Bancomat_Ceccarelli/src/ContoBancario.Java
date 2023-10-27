public class ContoBancario {
    private double saldo = 1000;

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized boolean preleva(double importo) {
        if (saldo >= importo) {
            saldo -= importo;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void deposita(double importo) {
        saldo += importo;
    }
}