
public abstract class Conta {
	private String nConta;
	private String titular;
	protected double saldo;
	
	public Conta(String nConta, String titular, double saldo) {
		this.nConta = nConta;
		this.titular = titular;
		this.saldo = saldo;
	}

	public String getnConta() {
		return nConta;
	}

	public void setnConta(String nConta) {
		this.nConta = nConta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public abstract void sacar(double valor); 
	
	public void depositar(double valor){
 		if(valor <= 0) {
			throw new IllegalArgumentException("Valor solicitado é menor que 0.");
		}
		
		this.saldo += valor;	
	}
	
	public abstract void tranferir(double valor, Conta destino);
}
