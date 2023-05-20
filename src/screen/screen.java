package screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import utils.Validador;

public class screen extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public screen() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);
                // Verificar a autenticação aqui

                String message = "Falha na autenticação!";
                if (Validador.validaEmail(email) && Validador.validaSenha(passwordString)) {
                    message = "Opa parece que tem um erro em nosso servidor tente Novamente";
                    boolean authenticateStatus = authenticate(email, passwordString);
                    if (authenticateStatus) {
                        message = "Autenticação bem-sucedida!";
                    }
                }
                JOptionPane.showMessageDialog(screen.this, message);
            }
        });
        panel.add(loginButton);

        getContentPane().add(panel);
    }

    private boolean authenticate(String email, String password) {
        // Lógica de autenticação (verifique o email e a senha fornecidos com algum sistema de autenticação)
        // Retorne true se a autenticação for bem-sucedida, caso contrário, retorne false
        // Aqui, estou apenas retornando true para fins de demonstração
        return true;
    }

    public static void main(String[] args) {
        try {
            // Definir a aparência do Java Swing para se parecer com a aparência nativa do sistema operacional
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new screen().setVisible(true);
            }
        });
    }
}
