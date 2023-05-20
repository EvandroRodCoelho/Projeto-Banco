package screen.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Buscar{

    private JFrame frame;
    private JTextField buscaField;

 

    public Buscar() {
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

        JLabel lblBusca = new JLabel("Buscar:");
        lblBusca.setBounds(50, 50, 80, 14);
        frame.getContentPane().add(lblBusca);

        buscaField = new JTextField();
        buscaField.setBounds(140, 50, 200, 20);
        frame.getContentPane().add(buscaField);
        buscaField.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(150, 80, 100, 23);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String termoBusca = buscaField.getText();

                // Realize a busca utilizando o termo de buscaQ
                // Exemplo: consultar no banco de dados, realizar uma pesquisa, etc.

                if (termoBusca.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, digite um termo de busca.");
                } else {
                    // Exiba o resultado da busca
                    JOptionPane.showMessageDialog(frame, "Resultado da busca para: " + termoBusca);
                }
            }
        });
        frame.getContentPane().add(btnBuscar);
        
        JButton btnMenu = new JButton("Voltar menu");
        btnMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		abrirMenu();
        	}
        });
        btnMenu.setBounds(41, 11, 89, 23);
        frame.getContentPane().add(btnMenu);
        frame.setVisible(true);
    }
}
