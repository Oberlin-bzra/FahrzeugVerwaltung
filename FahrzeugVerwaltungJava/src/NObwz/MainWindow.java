package NObwz;

import javax.swing.*;

public class MainWindow extends JFrame {
    private Fahrzeugverwaltung fahrzeugverwaltung;
    private Kundenverwaltung kundenverwaltung;
    private JsonService jsonService;
    private JTabbedPane tabbedPane;
    private User currentUser;

    public MainWindow(User user) {
        this.currentUser = user;
        jsonService = new JsonService();
        fahrzeugverwaltung = new Fahrzeugverwaltung();
        kundenverwaltung = new Kundenverwaltung();

        for (Fahrzeug f : jsonService.ladeFahrzeuge()) {
            fahrzeugverwaltung.fahrzeugErfassen(f);
        }
        for (Kunde k : jsonService.ladeKunden()) {
            kundenverwaltung.kundeErfassen(k);
        }

        setTitle("IdealCar4You - Fahrzeugverwaltung - " + user.getUsername() + " (" + user.getRole() + ")");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                speichereDaten();
            }
        });
    }

    private void initComponents() {
        JMenuBar menuBar = new JMenuBar();
        JMenu accountMenu = new JMenu("Account");

        JMenuItem logoutItem = new JMenuItem("Abmelden");
        logoutItem.addActionListener(e -> logout());
        accountMenu.add(logoutItem);

        JMenuItem infoItem = new JMenuItem("Benutzerinfo");
        infoItem.addActionListener(e -> showUserInfo());
        accountMenu.add(infoItem);

        menuBar.add(accountMenu);
        setJMenuBar(menuBar);

        tabbedPane = new JTabbedPane();

        FahrzeugPanel fahrzeugPanel = new FahrzeugPanel(fahrzeugverwaltung, this);
        tabbedPane.addTab("Fahrzeuge", fahrzeugPanel);

        KundenPanel kundenPanel = new KundenPanel(kundenverwaltung, this);
        tabbedPane.addTab("Kunden", kundenPanel);

        add(tabbedPane);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Möchten Sie sich wirklich abmelden?",
                "Abmelden", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            speichereDaten();
            dispose();
            SwingUtilities.invokeLater(() -> {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            });
        }
    }

    private void showUserInfo() {
        JOptionPane.showMessageDialog(this,
                "Benutzer: " + currentUser.getUsername() + "\nRolle: " + currentUser.getRole(),
                "Benutzerinformationen",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void speichereDaten() {
        jsonService.speichereFahrzeuge(fahrzeugverwaltung.getAlleFahrzeuge());
        jsonService.speichereKunden(kundenverwaltung.getAlleKunden());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });
    }
}