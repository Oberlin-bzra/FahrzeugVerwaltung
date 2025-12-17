package NObwz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class KundenPanel extends JPanel {
    private Kundenverwaltung verwaltung;
    private MainWindow mainWindow;
    private JTable table;
    private DefaultTableModel tableModel;

    public KundenPanel(Kundenverwaltung verwaltung, MainWindow mainWindow) {
        this.verwaltung = verwaltung;
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initComponents();
        aktualisiereTabelle();
    }

    private void initComponents() {
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.add(new JLabel("Kundenverwaltung - Alle registrierten Kunden"));
        add(infoPanel, BorderLayout.NORTH);

        String[] columnNames = {"Name", "Vorname", "Strasse", "PLZ", "Wohnort",
                "Tel. Privat", "Tel. Mobil", "E-Mail", "Geburtsdatum"};
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton neuButton = new JButton("Neuer Kunde");
        neuButton.addActionListener(e -> neuerKunde());
        buttonPanel.add(neuButton);

        JButton bearbeitenButton = new JButton("Bearbeiten");
        bearbeitenButton.addActionListener(e -> kundeBearbeiten());
        buttonPanel.add(bearbeitenButton);

        JButton loeschenButton = new JButton("Löschen");
        loeschenButton.addActionListener(e -> kundeLoeschen());
        buttonPanel.add(loeschenButton);

        JButton speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(e -> mainWindow.speichereDaten());
        buttonPanel.add(speichernButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void aktualisiereTabelle() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        tableModel.setRowCount(0);
        List<Kunde> kunden = verwaltung.getAlleKunden();

        for (Kunde k : kunden) {
            Object[] row = {
                    k.getName(),
                    k.getVorname(),
                    k.getStrasseNummer(),
                    k.getPlz(),
                    k.getWohnort(),
                    k.getTelefonPrivat(),
                    k.getTelefonMobile(),
                    k.getEmail(),
                    k.getGeburtsdatum() != null ? sdf.format(k.getGeburtsdatum()) : ""
            };
            tableModel.addRow(row);
        }
    }

    private void neuerKunde() {
        KundenDialog dialog = new KundenDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this), null);
        dialog.setVisible(true);

        Kunde neuerKunde = dialog.getKunde();
        if (neuerKunde != null) {
            verwaltung.kundeErfassen(neuerKunde);
            aktualisiereTabelle();
            mainWindow.speichereDaten();
        }
    }

    private void kundeBearbeiten() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Bitte wählen Sie einen Kunden aus.",
                    "Keine Auswahl", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Kunde> kunden = verwaltung.getAlleKunden();
        Kunde kunde = kunden.get(selectedRow);

        KundenDialog dialog = new KundenDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this), kunde);
        dialog.setVisible(true);

        if (dialog.wurdeGeaendert()) {
            verwaltung.kundeBearbeiten(kunde);
            aktualisiereTabelle();
            mainWindow.speichereDaten();
        }
    }

    private void kundeLoeschen() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Bitte wählen Sie einen Kunden aus.",
                    "Keine Auswahl", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Möchten Sie diesen Kunden wirklich löschen?",
                "Löschen bestätigen", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            List<Kunde> kunden = verwaltung.getAlleKunden();
            verwaltung.kundeLoeschen(kunden.get(selectedRow));
            aktualisiereTabelle();
            mainWindow.speichereDaten();
        }
    }
}