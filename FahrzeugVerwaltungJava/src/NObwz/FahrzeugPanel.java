package NObwz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class FahrzeugPanel extends JPanel {
    private Fahrzeugverwaltung verwaltung;
    private MainWindow mainWindow;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField suchMarkeField;
    private JTextField suchModellField;

    public FahrzeugPanel(Fahrzeugverwaltung verwaltung, MainWindow mainWindow) {
        this.verwaltung = verwaltung;
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initComponents();
        aktualisiereTabelle();
    }

    private void initComponents() {
        // Suchbereich
        JPanel suchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        suchPanel.add(new JLabel("Marke:"));
        suchMarkeField = new JTextField(15);
        suchPanel.add(suchMarkeField);

        suchPanel.add(new JLabel("Modell:"));
        suchModellField = new JTextField(15);
        suchPanel.add(suchModellField);

        JButton suchButton = new JButton("Suchen");
        suchButton.addActionListener(e -> sucheFahrzeuge());
        suchPanel.add(suchButton);

        JButton resetButton = new JButton("Alle anzeigen");
        resetButton.addActionListener(e -> {
            suchMarkeField.setText("");
            suchModellField.setText("");
            aktualisiereTabelle();
        });
        suchPanel.add(resetButton);

        add(suchPanel, BorderLayout.NORTH);

        // Tabelle
        String[] columnNames = {"Typ", "Marke", "Modell", "Hubraum", "Treibstoff",
                "KM-Stand", "Leistung", "Erstzu.", "Farbe"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Button-Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton neuButton = new JButton("Neues Fahrzeug");
        neuButton.addActionListener(e -> neuesFahrzeug());
        buttonPanel.add(neuButton);

        JButton bearbeitenButton = new JButton("Bearbeiten");
        bearbeitenButton.addActionListener(e -> fahrzeugBearbeiten());
        buttonPanel.add(bearbeitenButton);

        JButton loeschenButton = new JButton("Löschen");
        loeschenButton.addActionListener(e -> fahrzeugLoeschen());
        buttonPanel.add(loeschenButton);

        JButton speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(e -> mainWindow.speichereDaten());
        buttonPanel.add(speichernButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void aktualisiereTabelle() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        tableModel.setRowCount(0);
        List<Fahrzeug> fahrzeuge = verwaltung.getAlleFahrzeuge();

        for (Fahrzeug f : fahrzeuge) {
            String typ = (f instanceof Auto) ? "Auto" : "Transporter";
            Object[] row = {
                    typ,
                    f.getMarke(),
                    f.getModell(),
                    f.getHubraum() + " ccm",
                    f.getTreibstoffart(),
                    f.getKmStand() + " km",
                    f.getLeistung() + " PS",
                    f.getErstZulassung() != null ? sdf.format(f.getErstZulassung()) : "",
                    f.getAussenfarbe()
            };
            tableModel.addRow(row);
        }
    }

    private void sucheFahrzeuge() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String marke = suchMarkeField.getText().trim();
        String modell = suchModellField.getText().trim();

        List<Fahrzeug> ergebnisse = verwaltung.fahrzeugSuchen(marke, modell);

        tableModel.setRowCount(0);
        for (Fahrzeug f : ergebnisse) {
            String typ = (f instanceof Auto) ? "Auto" : "Transporter";
            Object[] row = {
                    typ,
                    f.getMarke(),
                    f.getModell(),
                    f.getHubraum() + " ccm",
                    f.getTreibstoffart(),
                    f.getKmStand() + " km",
                    f.getLeistung() + " PS",
                    f.getErstZulassung() != null ? sdf.format(f.getErstZulassung()) : "",
                    f.getAussenfarbe()
            };
            tableModel.addRow(row);
        }

        if (ergebnisse.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keine Fahrzeuge gefunden.",
                    "Suche", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void neuesFahrzeug() {
        FahrzeugDialog dialog = new FahrzeugDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this), null);
        dialog.setVisible(true);

        Fahrzeug neuesFahrzeug = dialog.getFahrzeug();
        if (neuesFahrzeug != null) {
            verwaltung.fahrzeugErfassen(neuesFahrzeug);
            aktualisiereTabelle();
            mainWindow.speichereDaten();
        }
    }

    private void fahrzeugBearbeiten() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Bitte wählen Sie ein Fahrzeug aus.",
                    "Keine Auswahl", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Fahrzeug> fahrzeuge = verwaltung.getAlleFahrzeuge();
        Fahrzeug fahrzeug = fahrzeuge.get(selectedRow);

        FahrzeugDialog dialog = new FahrzeugDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this), fahrzeug);
        dialog.setVisible(true);

        if (dialog.wurdeGeaendert()) {
            verwaltung.fahrzeugBearbeiten(fahrzeug);
            aktualisiereTabelle();
            mainWindow.speichereDaten();
        }
    }

    private void fahrzeugLoeschen() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Bitte wählen Sie ein Fahrzeug aus.",
                    "Keine Auswahl", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Möchten Sie dieses Fahrzeug wirklich löschen?",
                "Löschen bestätigen", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            List<Fahrzeug> fahrzeuge = verwaltung.getAlleFahrzeuge();
            verwaltung.fahrzeugLoeschen(fahrzeuge.get(selectedRow));
            aktualisiereTabelle();
            mainWindow.speichereDaten();
        }
    }
}