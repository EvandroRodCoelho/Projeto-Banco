
public class ContaCorrente extends Conta{
	
	private double TAXA = 3.5;
	private double TAXATRANS = 5.5;


	public ContaCorrente(String nConta, String titular, double saldo) {
		super(nConta, titular, saldo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void depositar(double valor) {
		if(valor <= 0) {
			throw new IllegalArgumentException("Valor solicitado é menor que 0.");
		}

		this.saldo += valor;
	}
	
	public void sacar(double valor){
		if(valor + TAXA > this.saldo) {
			throw new IllegalArgumentException("Valor solicitado é menor que o saldo da conta.");
		}

		this.saldo-=valor;
	}
	
	public void tranferir(double valor, Conta destino) {
		if(valor + TAXATRANS > this.saldo) {
			throw new IllegalArgumentException("Valor a ser transferido é maior que o saldo da conta.");
		}

		this.saldo-=valor+TAXATRANS;
		destino.saldo+=valor;
	}
}
