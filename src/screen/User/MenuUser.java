package screen.User;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuUser {

    private JFrame frame;

    public MenuUser() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        int buttonWidth = 200;
        int buttonHeight = 30;
        int x = (frame.getWidth() - buttonWidth) / 2;
        int y = 30;

        JButton btnCloseAccount = new JButton("Encerrar Conta");
        btnCloseAccount.setBounds(x, y, buttonWidth, buttonHeight);
        btnCloseAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para encerrar conta
                JOptionPane.showMessageDialog(frame, "Conta encerrada com sucesso!");
            }
        });
        panel.add(btnCloseAccount);

        JButton btnDeposit = new JButton("Depositar");
        btnDeposit.setBounds(x, y + buttonHeight + 10, buttonWidth, buttonHeight);
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para depositar
                JOptionPane.showMessageDialog(frame, "Depósito realizado com sucesso!");
            }
        });
        panel.add(btnDeposit);

        JButton btnTransfer = new JButton("Transferir");
        btnTransfer.setBounds(x, y + (buttonHeight + 10) * 2, buttonWidth, buttonHeight);
        btnTransfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para transferir
                JOptionPane.showMessageDialog(frame, "Transferência realizada com sucesso!");
            }
        });
        panel.add(btnTransfer);

        JButton btnWithdraw = new JButton("Sacar");
        btnWithdraw.setBounds(x, y + (buttonHeight + 10) * 3, buttonWidth, buttonHeight);
        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para sacar
                JOptionPane.showMessageDialog(frame, "Saque realizado com sucesso!");
            }
        });
        panel.add(btnWithdraw);

        JButton btnCheckBalance = new JButton("Consultar Saldo");
        btnCheckBalance.setBounds(x, y + (buttonHeight + 10) * 4, buttonWidth, buttonHeight);
        btnCheckBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para consultar saldo
                JOptionPane.showMessageDialog(frame, "Seu saldo é de R$1000.00");
            }
        });
        panel.add(btnCheckBalance);

        int windowHeight = y + (buttonHeight + 10) * 5 + 30;
        frame.setMinimumSize(new Dimension(400, windowHeight));
        frame.setMaximumSize(new Dimension(800, windowHeight));
        frame.setPreferredSize(new Dimension(600, windowHeight));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
