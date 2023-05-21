package screen.Admin;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cadastro{

    private JFrame frame;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JTextField enderecoField;

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
    
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(50, 80, 80, 14);
        frame.getContentPane().add(lblCpf);
    
        cpfField = new JTextField();
        cpfField.setBounds(140, 80, 200, 20);
        frame.getContentPane().add(cpfField);
        cpfField.setColumns(10);
    
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(50, 110, 80, 14);
        frame.getContentPane().add(lblTelefone);
    
        telefoneField = new JTextField();
        telefoneField.setBounds(140, 110, 200, 20);
        frame.getContentPane().add(telefoneField);
        telefoneField.setColumns(10);
    
        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(50, 140, 80, 14);
        frame.getContentPane().add(lblEndereco);
    
        enderecoField = new JTextField();
        enderecoField.setBounds(140, 140, 200, 20);
        frame.getContentPane().add(enderecoField);
        enderecoField.setColumns(10);
    
        JLabel lblTipoConta = new JLabel("Tipo de Conta:");
        lblTipoConta.setBounds(50, 170, 100, 14);
        frame.getContentPane().add(lblTipoConta);
    
        String[] tiposConta = {"Poupança", "Corrente"};
        JComboBox<String> tipoContaComboBox = new JComboBox<>(tiposConta);
        tipoContaComboBox.setBounds(140, 170, 200, 20);
        frame.getContentPane().add(tipoContaComboBox);
    
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(150, 210, 100, 23);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String telefone = telefoneField.getText();
                String endereco = enderecoField.getText();
                String tipoConta = (String) tipoContaComboBox.getSelectedItem();
    
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
