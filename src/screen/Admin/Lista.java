package screen.Admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Lista {

    private JFrame frame;
    private JList<String> userList;
    private DefaultListModel<String> listModel;

   

    public Lista() {
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

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBounds(10, 30, 400, 200);
        frame.getContentPane().add(scrollPane);

        JButton btnCarregar = new JButton("Carregar");
        btnCarregar.setBounds(10, 240, 100, 23);
        btnCarregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Simulação de carregamento de dados
                List<Usuario> usuarios = carregarUsuarios();

                // Limpa a lista antes de adicionar os novos usuários
                listModel.clear();

                // Adiciona os usuários carregados à listas
                for (Usuario usuario : usuarios) {
                    listModel.addElement(usuario.toString());
                }
            }
        });
        frame.getContentPane().add(btnCarregar);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(301, 240, 89, 23);
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		abrirMenu();
        	}
        });
        frame.getContentPane().add(btnVoltar);

        // Carregar a lista de usuários automaticamente ao abrir a tela
        btnCarregar.doClick();
        frame.setVisible(true);
    }

    // Método de simulação para carregar usuários
    private List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "João", 1000.0));
        usuarios.add(new Usuario(2, "Maria", 500.0));
        usuarios.add(new Usuario(3, "Pedro", 1500.0));
        usuarios.add(new Usuario(4, "Ana", 2000.0));
        usuarios.add(new Usuario(5, "Carlos", 800.0));
        return usuarios;
    }

    // Classe interna para representar um usuário
    private class Usuario {
        private int id;
        private String nome;
        private double saldo;

        public Usuario(int id, String nome, double saldo) {
            this.id = id;
            this.nome = nome;
            this.saldo = saldo;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Nome: " + nome + " | Saldo: R$" + saldo;
        }
    }
}