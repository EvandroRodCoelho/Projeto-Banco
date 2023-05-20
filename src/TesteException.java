import java.util.Scanner;

import utils.Validador;

public class TesteException {
	static String numConta;
	static String nome;
	static int saldo;
	static int opc; 
	public static void main(String[] args) {
		System.out.println("inicio do menu");
		caixa();
		System.out.println("fim do main");
	}

	/**
	 * 
	 */
	static void CriaConta(){
		System.out.println("inicio do criação de conta");

		Scanner entry = new Scanner(System.in);

		try {
			System.out.println("Numero da conta -> ");
			numConta = entry.nextLine();
			if(!Validador.validarNumConta(numConta)) {
				throw new IllegalArgumentException("Número de conta inválido. Certifique-se de que contém apenas números.");
			}
	
			System.out.println("Nome -> ");
			nome = entry.nextLine();
			if (!Validador.validarNome(nome)) {
				throw new IllegalArgumentException("Nome inválido. Certifique-se de que contém apenas letras e pelo menos dois caracteres.");
			}
	
			System.out.println("Saldo inicial ->");
			saldo = entry.nextInt();
			if (!Validador.validarSaldo(saldo)) {
				throw new IllegalArgumentException("Saldo inicial inválido. Certifique-se de que é um valor positivo.");
			}

			System.out.println("Qual opção para criação de conta você quer? ");
			System.out.println("1- Conta Corrente\t  2-Conta Poupança");
			opc = entry.nextInt();
			switch (opc) {
				case 1:
					var cc = new ContaCorrente(numConta, nome, saldo);
					caixa();
					break;
				case 2:
					var cp = new ContaPoupanca(numConta, nome, saldo);
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			System.out.println("Clique Enter para tentar de novo");
			entry.nextLine(); // limpa o buffer do scanner
			CriaConta();
		} finally {
			entry.close();
		}
}

	private static void caixa() {
		System.out.println("Caixa");
		Scanner entry = new Scanner(System.in);

		System.out.println("\n1-Criar conta \t 2-Gerenciar conta");
		opc = entry.nextInt();
		switch (opc) {
				case 1:
					CriaConta();
					break;
				case 2:
					break;
				default:
					System.out.println("Opção inválida.");

		}
		entry.close();
	}
}
