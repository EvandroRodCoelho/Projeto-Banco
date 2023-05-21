package screen.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class Admin {

    private JFrame frame;

    public Admin() {
        initialize();
    }
    private void abrirTelaCadastro() {
		frame.dispose();
		Cadastro cadastro = new Cadastro();
	}
    private void abrirTelaBusca() {
		frame.dispose();
		Buscar buscar = new Buscar();
	}
    private void abrirTelaLista() {
  		frame.dispose();
  		Lista lista = new Lista();
  	}
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnCadastrar = new JButton("Cadastrar conta");
        btnCadastrar.setBounds(150, 83, 150, 30);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               abrirTelaCadastro();
            }
        });
        frame.getContentPane().add(btnCadastrar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(150, 136, 150, 30);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ação para buscar
               abrirTelaBusca();
            }
        });
        frame.getContentPane().add(btnBuscar);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(150, 193, 150, 30);
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             abrirTelaLista();
            }
        });
        frame.getContentPane().add(btnListar);
        
        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setBounds(203, 28, 97, 30);
        frame.getContentPane().add(lblMenu);

        frame.setVisible(true);
    }


}