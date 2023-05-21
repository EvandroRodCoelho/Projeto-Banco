package screen.User;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import screen.ScreenLogin;

public class UserMenuWindow {

    private JFrame frame;

    public UserMenuWindow() {
        initialize();
    }

    private void openDepositWindow() {
        frame.dispose();
        DepositWindow deposit = new DepositWindow();
    }

    private void openWithdrawWindow() {
        frame.dispose();
        WithdrawWindow deposit = new WithdrawWindow();
    }

    private void openTransferWindow() {
        frame.dispose();
        TransferWindow deposit = new TransferWindow();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 309, 230);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int buttonWidth = 200;
        int buttonHeight = 30;
        int x = (frame.getContentPane().getWidth() - buttonWidth) / 2;
        int y = (frame.getContentPane().getHeight() - (buttonHeight + 10) * 5) / 2;

        JButton btnCloseAccount = new JButton("Encerrar Conta");
        btnCloseAccount.setBounds(23, 11, 156, 30);
        btnCloseAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmResult = JOptionPane.showOptionDialog(frame, "Deseja realmente encerrar a conta?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sim", "Não"}, "Não");
                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Lógica para encerrar conta
                    JOptionPane.showMessageDialog(frame, "Conta encerrada com sucesso!");
                    frame.dispose();
                    ScreenLogin login = new ScreenLogin();
                }
            }
        });
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(btnCloseAccount);

        JButton btnDeposit = new JButton("Depositar");
        btnDeposit.setBounds(23, 52, 156, 30);
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDepositWindow();
            }
        });
        frame.getContentPane().add(btnDeposit);

        JButton btnTransfer = new JButton("Transferir");
        btnTransfer.setBounds(203, 11, 156, 30);
        btnTransfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openTransferWindow();
            }
        });
        frame.getContentPane().add(btnTransfer);

        JButton btnWithdraw = new JButton("Sacar");
        btnWithdraw.setBounds(204, 52, 156, 30);
        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWithdrawWindow();
            }
        });
        frame.getContentPane().add(btnWithdraw);

        JButton btnCheckBalance = new JButton("Consultar Saldo");
        btnCheckBalance.setBounds(90, 105, 200, 30);
        btnCheckBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para consultar saldo
                JOptionPane.showMessageDialog(frame, "Seu saldo é de R$1000.00");
            }
        });
        frame.getContentPane().add(btnCheckBalance);

        int windowHeight = (buttonHeight + 10) * 5 + 30;
        frame.setMinimumSize(new Dimension(400, windowHeight));
        frame.setMaximumSize(new Dimension(800, windowHeight));
        frame.setPreferredSize(new Dimension(600, windowHeight));
        frame.setVisible(true);
    }
}
