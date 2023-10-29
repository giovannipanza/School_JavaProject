public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bo b = new Bo();
        Thread t1 = new Thread(new ThreadPersona("a",b));
        Thread t2 = new Thread(new ThreadPersona("b",b));
        t1.start();
        t2.start();



    }
}