package screen;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import screen.Admin.Admin;
import utils.Validador;

public class ScreenLogin {

	private JFrame frame;
	private JTextField emailField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ScreenLogin window = new ScreenLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScreenLogin() {
		initialize();
	}

	private boolean authenticate(String email, String password) {
		return true;
	}

	private void openAdminScreen() {
		frame.dispose();
		Admin adminScreen = new Admin();
	}

	private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(frame, message, "Falha na autenticação", JOptionPane.ERROR_MESSAGE);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		emailField = new JTextField();
		emailField.setBounds(31, 52, 368, 20);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(31, 29, 46, 14);
		frame.getContentPane().add(lblUser);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(31, 103, 46, 14);
		frame.getContentPane().add(lblSenha);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(163, 195, 89, 23);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				char[] password = passwordField.getPassword();
				String passwordString = new String(password);

				//if (Validador.validaEmail(email) && Validador.validaSenha(passwordString)) {
					boolean authenticateStatus = authenticate(email, passwordString);
					if (authenticateStatus) {
						openAdminScreen();
					} else {
						showErrorDialog("Falha na autenticação!");
					}
				} /*else {
					showErrorDialog("Email ou senha inválidos!");
				}*/

		});
		frame.getContentPane().add(btnLogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(31, 128, 368, 20);
		frame.getContentPane().add(passwordField);
		frame.setVisible(true);
	}
}

