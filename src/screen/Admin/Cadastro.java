package screen.Admin;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cadastro{

    private JFrame frame;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField senhaField;
    private JTextField telefoneField;


    public Cadastro() {
        initialize();
    }
    
    private void abrirMenu() {
    	frame.dispose();
    	Admin admin = new Admin();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(50, 50, 80, 14);
        frame.getContentPane().add(lblNome);

        nomeField = new JTextField();
        nomeField.setBounds(140, 50, 200, 20);
        frame.getContentPane().add(nomeField);
        nomeField.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 80, 80, 14);
        frame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(140, 80, 200, 20);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 110, 80, 14);
        frame.getContentPane().add(lblSenha);

        senhaField = new JTextField();
        senhaField.setBounds(140, 110, 200, 20);
        frame.getContentPane().add(senhaField);
        senhaField.setColumns(10);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(50, 140, 80, 14);
        frame.getContentPane().add(lblTelefone);

        telefoneField = new JTextField();
        telefoneField.setBounds(140, 140, 200, 20);
        frame.getContentPane().add(telefoneField);
        telefoneField.setColumns(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(150, 180, 100, 23);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String email = emailField.getText();
                String senha = senhaField.getText();
                String telefone = telefoneField.getText();

                // Realize o processamento dos dados de cadastro aqui
                // Exemplo: validar os campos, salvar no banco de dados, etc.

                JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
            }
        });
        frame.getContentPane().add(btnCadastrar);
        
        JButton btnNewButton = new JButton("Voltar");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		abrirMenu();
        	}
        });
        btnNewButton.setBounds(25, 11, 89, 23);
        frame.getContentPane().add(btnNewButton);
        frame.setVisible(true);
    }
}
