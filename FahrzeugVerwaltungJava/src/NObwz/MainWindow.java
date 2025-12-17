package NObwz;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Fahrzeugverwaltung fahrzeugverwaltung;
    private Kundenverwaltung kundenverwaltung;
    private JsonService jsonService;
    private JTabbedPane tabbedPane;

    public MainWindow() {
        jsonService = new JsonService();
        fahrzeugverwaltung = new Fahrzeugverwaltung();
        kundenverwaltung = new Kundenverwaltung();

        // Daten laden
        for (Fahrzeug f : jsonService.ladeFahrzeuge()) {
            fahrzeugverwaltung.fahrzeugErfassen(f);
        }
        for (Kunde k : jsonService.ladeKunden()) {
            kundenverwaltung.kundeErfassen(k);
        }

        setTitle("IdealCar4You - Fahrzeugverwaltung");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        // Beim Schließen speichern
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                speichereDaten();
            }
        });
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();

        // Tab für Fahrzeugverwaltung
        FahrzeugPanel fahrzeugPanel = new FahrzeugPanel(fahrzeugverwaltung, this);
        tabbedPane.addTab("Fahrzeuge", fahrzeugPanel);

        // Tab für Kundenverwaltung
        KundenPanel kundenPanel = new KundenPanel(kundenverwaltung, this);
        tabbedPane.addTab("Kunden", kundenPanel);

        add(tabbedPane);
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
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}