import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiServerMain {
	public static void main(String[] args) {
		MultiServerConn ser=new MultiServerConn();
		try {
			ser.connessione();
		} catch (Exception ex) {
			Logger.getLogger(MultiServerMain.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
