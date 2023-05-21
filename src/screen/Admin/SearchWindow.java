package screen.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SearchWindow{

    private JFrame frame;
    private JTextField buscaField;

 

    public SearchWindow() {
        initialize();
    }
    private void menuOpen() {
    	frame.dispose();
    	AdminMenuWindow admin = new AdminMenuWindow();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblSearch = new JLabel("Buscar:");
        lblSearch.setBounds(50, 50, 80, 14);
        frame.getContentPane().add(lblSearch);

        buscaField = new JTextField();
        buscaField.setBounds(140, 50, 200, 20);
        frame.getContentPane().add(buscaField);
        buscaField.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(150, 80, 100, 23);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String SerchTerm = buscaField.getText();

                // Realize a busca utilizando o termo de buscaQ
                // Exemplo: consultar no banco de dados, realizar uma pesquisa, etc.

                if (SerchTerm.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, digite um termo de busca.");
                } else {
                    // Exiba o resultado da busca
                    JOptionPane.showMessageDialog(frame, "Resultado da busca para: " + SerchTerm);
                }
            }
        });
        frame.getContentPane().add(btnBuscar);
        
        JButton btnMenu = new JButton("Voltar menu");
        btnMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuOpen();
        	}
        });
        btnMenu.setBounds(41, 11, 89, 23);
        frame.getContentPane().add(btnMenu);
        frame.setVisible(true);
    }
}
