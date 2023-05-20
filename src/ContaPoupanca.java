
public class ContaPoupanca extends Conta{

	public ContaPoupanca(String nConta, String titular, double saldo) {
		super(nConta, titular, saldo);
	}
	
	public void sacar(double valor){
		if(valor > this.saldo) {
			throw new IllegalArgumentException("Valor solicitado é menor que o saldo da conta.");
		}
		
		this.saldo-=valor;
	}
	
	public void tranferir(double valor, Conta destino) {
		if(valor > this.saldo) {
			throw new IllegalArgumentException("Valor a ser transferido é maior que o saldo da conta.");
		}

		this.saldo-=valor;
		destino.saldo+=valor;
	}
}
