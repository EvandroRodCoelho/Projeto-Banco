package screen.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WithdrawWindow {

    private JFrame frame;
    private JTextField amountField;
    private double saldoDisponivel = 1000; // Saldo disponível do usuário

    public WithdrawWindow() {
        initialize();
    }
    private void openMenu() {
    	frame.dispose();
    	UserMenuWindow User = new UserMenuWindow();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblAmount = new JLabel("Valor:");
        lblAmount.setBounds(50, 50, 80, 14);
        frame.getContentPane().add(lblAmount);

        amountField = new JTextField();
        amountField.setBounds(140, 50, 100, 20);
        frame.getContentPane().add(amountField);
        amountField.setColumns(10);

        JLabel lblSaldo = new JLabel("Saldo disponível: R$" + saldoDisponivel);
        lblSaldo.setBounds(50, 80, 200, 14);
        frame.getContentPane().add(lblSaldo);

        JButton btnWithdraw = new JButton("Sacar");
        btnWithdraw.setBounds(100, 110, 100, 23);
        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = 0.0;
                try {
                    amount = Double.parseDouble(amountField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Valor de saque inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (amount > saldoDisponivel) {
                    JOptionPane.showMessageDialog(frame, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Realize o processamento do saque aqui
                // Exemplo: atualizar saldo, registrar transação, etc.

                saldoDisponivel -= amount; // Atualiza o saldo disponível

                JOptionPane.showMessageDialog(frame, "Saque realizado com sucesso!");
            }
        });
        frame.getContentPane().add(btnWithdraw);
        
        JButton btnReturn = new JButton("Voltar");
        btnReturn.setBounds(41, 11, 89, 23);
        frame.getContentPane().add(btnReturn);
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openMenu();
            }
         });
        
        frame.setVisible(true);
    }
}
