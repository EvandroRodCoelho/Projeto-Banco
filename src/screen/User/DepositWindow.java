package screen.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class DepositWindow {

    private JFrame frame;
    private JTextField valueField;

    public DepositWindow() {
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

        JLabel lblValue = new JLabel("Valor:");
        lblValue.setBounds(50, 50, 80, 14);
        frame.getContentPane().add(lblValue);

        valueField = new JTextField();
        valueField.setBounds(140, 50, 100, 20);
        frame.getContentPane().add(valueField);
        valueField.setColumns(10);

        JButton btnDeposit = new JButton("Depositar");
        btnDeposit.setBounds(100, 100, 100, 23);
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double valor = 0.0;
                try {
                    valor = Double.parseDouble(valueField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Realize o processamento do depósito aqui
                // Exemplo: atualizar o saldo da conta

                JOptionPane.showMessageDialog(frame, "Depósito realizado com sucesso!");
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnDeposit);
        
        JButton btnReturn = new JButton("Voltar");
        btnReturn.setBounds(50, 11, 89, 23);
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openMenu();
            }
        });
        frame.getContentPane().add(btnReturn);

        frame.setVisible(true);
    }
}
