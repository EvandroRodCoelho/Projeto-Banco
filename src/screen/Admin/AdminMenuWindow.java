package screen.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminMenuWindow {

    private JFrame frame;

    public AdminMenuWindow() {
        initialize();
    }
    
    private void openRegistrationScreen() {
        frame.dispose();
        RegistrationWindow registration = new RegistrationWindow();
    }
    
    private void openSearchScreen() {
        frame.dispose();
        SearchWindow search = new SearchWindow();
    }
    
    private void openUserListScreen() {
        frame.dispose();
        UserListWindow userList = new UserListWindow();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnRegister = new JButton("Cadastrar conta");
        btnRegister.setBounds(150, 83, 150, 30);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openRegistrationScreen();
            }
        });
        frame.getContentPane().add(btnRegister);

        JButton btnSearch = new JButton("Buscar");
        btnSearch.setBounds(150, 136, 150, 30);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchScreen();
            }
        });
        frame.getContentPane().add(btnSearch);

        JButton btnUserList = new JButton("Listar");
        btnUserList.setBounds(150, 193, 150, 30);
        btnUserList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openUserListScreen();
            }
        });
        frame.getContentPane().add(btnUserList);
        
        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setBounds(203, 28, 97, 30);
        frame.getContentPane().add(lblMenu);

        frame.setVisible(true);
    }

}