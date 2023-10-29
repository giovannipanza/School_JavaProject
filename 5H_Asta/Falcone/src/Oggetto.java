public class Oggetto {
	//attributi 
	private String nome;
	private double prezzo;
	private boolean venduto = false;
	
	
	//costruttore
	public Oggetto(String nome, double prezzo) {
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	
	//get e set
	public boolean isVenduto() {
		return venduto;
	}
	public void setVenduto(boolean venduto) {
		this.venduto = venduto;
	}

	public double getPrezzo() {
		return prezzo;
	}

}
