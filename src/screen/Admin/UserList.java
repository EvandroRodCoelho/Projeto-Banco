package screen.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class UserList {

    private JFrame frame;
    private JList<String> userList;
    private DefaultListModel<String> listModel;

    public UserList() {
        initialize();
    }
    
    private void openMenu() {
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

        JButton btnLoad = new JButton("Carregar");
        btnLoad.setBounds(10, 240, 100, 23);
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Simulation of data loading
                List<User> users = loadUsers();

                // Clear the list before adding the new users
                listModel.clear();

                // Add the loaded users to the list
                for (User user : users) {
                    listModel.addElement(user.toString());
                }
            }
        });
        frame.getContentPane().add(btnLoad);
        
        JButton btnBack = new JButton("Voltar");
        btnBack.setBounds(301, 240, 89, 23);
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openMenu();
        	}
        });
        frame.getContentPane().add(btnBack);

        // Load the list of users automatically when the screen opens
        btnLoad.doClick();
        frame.setVisible(true);
    }

    // Simulation method to load users
    private List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John", "12345678901", "(11) 9999-9999", "Street A, 123", "Checking"));
        users.add(new User(2, "Mary", "98765432109", "(11) 8888-8888", "Street B, 456", "Savings"));
        users.add(new User(3, "Peter", "45678912305", "(11) 7777-7777", "Street C, 789", "Checking"));
        users.add(new User(4, "Ann", "78912345603", "(11) 6666-6666", "Street D, 987", "Checking"));
        users.add(new User(5, "Carlos", "32165498707", "(11) 5555-5555", "Street E, 654", "Savings"));
        return users;
    }

    // Inner class to represent a user
    private class User {
        private int id;
        private String name;
        private String cpf;
        private String phone;
        private String address;
        private String accountType;

        public User(int id, String name, String cpf, String phone, String address, String accountType) {
            this.id = id;
            this.name = name;
            this.cpf = cpf;
            this.phone = phone;
            this.address = address;
            this.accountType = accountType;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Name: " + name + " | CPF: " + cpf + " | Phone: " + phone + " | Address: " + address + " | Account Type: " + accountType;
        }
    }
}
