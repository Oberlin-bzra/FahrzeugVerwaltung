package NObwz;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private LoginService loginService;
    private User currentUser;

    public LoginView() {
        loginService = new LoginService();
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - FahrzeugVerwaltung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("IdealCar4You");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        JLabel subtitleLabel = new JLabel("Fahrzeugverwaltung");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        mainPanel.add(subtitleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel usernameLabel = new JLabel("Benutzername:");
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(20);
        mainPanel.add(usernameField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Passwort:");
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        loginButton = new JButton("Anmelden");
        registerButton = new JButton("Registrieren");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());

        passwordField.addActionListener(e -> handleLogin());

        add(mainPanel);
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Bitte Benutzername und Passwort eingeben.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        currentUser = loginService.authenticate(username, password);
        if (currentUser != null) {
            JOptionPane.showMessageDialog(this,
                    "Willkommen, " + currentUser.getUsername() + "!\nRolle: " + currentUser.getRole(),
                    "Login erfolgreich",
                    JOptionPane.INFORMATION_MESSAGE);
            openMainApplication();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Benutzername oder Passwort falsch.",
                    "Login fehlgeschlagen",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }

    private void handleRegister() {
        JPanel registerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField newUsernameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        String[] roles = {"Fahrzeugpark Manager", "Kundenberater"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);

        registerPanel.add(new JLabel("Benutzername:"));
        registerPanel.add(newUsernameField);
        registerPanel.add(new JLabel("Passwort:"));
        registerPanel.add(newPasswordField);
        registerPanel.add(new JLabel("Passwort bestätigen:"));
        registerPanel.add(confirmPasswordField);
        registerPanel.add(new JLabel("Rolle:"));
        registerPanel.add(roleComboBox);

        int result = JOptionPane.showConfirmDialog(this, registerPanel,
                "Neuen Benutzer registrieren", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String username = newUsernameField.getText().trim();
            String password = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Benutzername und Passwort dürfen nicht leer sein.",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this,
                        "Passwörter stimmen nicht überein.",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (loginService.registerUser(username, password, role)) {
                JOptionPane.showMessageDialog(this,
                        "Benutzer erfolgreich registriert.",
                        "Erfolg",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Benutzername bereits vorhanden.",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openMainApplication() {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow(currentUser);
            mainWindow.setVisible(true);
        });
        dispose();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });
    }
}