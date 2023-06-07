package model;

public class Conta {
    private String numConta;
	private String titular;
    private int tipoConta;
	protected double saldo;
    private int usuarioId;
    protected int id;
	
	public Conta(String numConta, String titular, double saldo, int tipoConta, int usuarioId, int id) {
        this.numConta = numConta;
        this.titular = titular;
		this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.usuarioId = usuarioId;
        this.id = id;
	}

	public String getTitular() {
        return titular;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
