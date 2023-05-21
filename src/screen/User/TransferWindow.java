package screen.User;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferWindow {

    private JFrame frame;
    private JTextField recipientField;
    private JTextField amountField;
    private double saldoDisponivel= 1000; // Saldo disponível do remetente
    private JButton btnReturn;

    public TransferWindow() {
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

        JLabel lblRecipient = new JLabel("Destinatário:");
        lblRecipient.setBounds(34, 51, 100, 14);
        frame.getContentPane().add(lblRecipient);

        recipientField = new JTextField();
        recipientField.setBounds(99, 48, 120, 20);
        frame.getContentPane().add(recipientField);
        recipientField.setColumns(10);

        JLabel lblAmount = new JLabel("Valor:");
        lblAmount.setBounds(34, 87, 100, 14);
        frame.getContentPane().add(lblAmount);

        amountField = new JTextField();
        amountField.setBounds(99, 84, 120, 20);
        frame.getContentPane().add(amountField);
        amountField.setColumns(10);

        JButton btnTransfer = new JButton("Transferir");
        btnTransfer.setBounds(99, 127, 120, 23);
        btnTransfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String recipient = recipientField.getText();
                double amount = 0.0;
                try {
                    amount = Double.parseDouble(amountField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Valor de transferência inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (amount > saldoDisponivel) {
                    JOptionPane.showMessageDialog(frame, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Realize o processamento da transferência aqui
                // Exemplo: atualizar saldos, registrar transação, etc.

                saldoDisponivel -= amount; // Atualiza o saldo disponível

                JOptionPane.showMessageDialog(frame, "Transferência realizada com sucesso!");
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnTransfer);
        
        btnReturn = new JButton("Voltar");
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openMenu();
            }
         });
        
        btnReturn.setBounds(34, 17, 89, 23);
        frame.getContentPane().add(btnReturn);

        frame.setVisible(true);
    }
}
