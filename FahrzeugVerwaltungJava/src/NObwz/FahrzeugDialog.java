package NObwz;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FahrzeugDialog extends JDialog {
    private Fahrzeug fahrzeug;
    private boolean geaendert = false;

    private JTextField markeField, modellField, hubraumField, kmStandField;
    private JTextField leistungField, farbeField, leergewichtField, erstZulassungField;
    private JComboBox<String> treibstoffCombo;
    private JComboBox<String> typCombo;
    private JComboBox<String> aufbauCombo;
    private JCheckBox naviCheckbox;
    private JTextField zuladungField;
    private JPanel spezifischPanel;
    private CardLayout cardLayout;

    public FahrzeugDialog(JFrame parent, Fahrzeug fahrzeug) {
        super(parent, fahrzeug == null ? "Neues Fahrzeug" : "Fahrzeug bearbeiten", true);
        this.fahrzeug = fahrzeug;

        setSize(500, 650);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initComponents();

        if (fahrzeug != null) {
            fuelleFelderAus();
        }
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        int row = 0;

        // Fahrzeugtyp
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Fahrzeugtyp:*"), gbc);
        gbc.gridx = 1;
        typCombo = new JComboBox<>(new String[]{"Auto", "Transporter"});
        typCombo.addActionListener(e -> zeigeSpezifischeFelder());
        mainPanel.add(typCombo, gbc);
        row++;

        // Marke
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Marke:*"), gbc);
        gbc.gridx = 1;
        markeField = new JTextField(20);
        mainPanel.add(markeField, gbc);
        row++;

        // Modell
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Modell:*"), gbc);
        gbc.gridx = 1;
        modellField = new JTextField(20);
        mainPanel.add(modellField, gbc);
        row++;

        // Hubraum
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Hubraum (ccm):*"), gbc);
        gbc.gridx = 1;
        hubraumField = new JTextField(20);
        mainPanel.add(hubraumField, gbc);
        row++;

        // Treibstoffart
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Treibstoffart:*"), gbc);
        gbc.gridx = 1;
        treibstoffCombo = new JComboBox<>(new String[]{"Benzin", "Diesel", "Elektrisch"});
        mainPanel.add(treibstoffCombo, gbc);
        row++;

        // KM-Stand
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("KM-Stand:*"), gbc);
        gbc.gridx = 1;
        kmStandField = new JTextField(20);
        mainPanel.add(kmStandField, gbc);
        row++;

        // Leistung
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Leistung (PS):*"), gbc);
        gbc.gridx = 1;
        leistungField = new JTextField(20);
        mainPanel.add(leistungField, gbc);
        row++;

        // Erstzulassung
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Erstzulassung (TT.MM.JJJJ):*"), gbc);
        gbc.gridx = 1;
        erstZulassungField = new JTextField(20);
        mainPanel.add(erstZulassungField, gbc);
        row++;

        // Aussenfarbe
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Aussenfarbe:*"), gbc);
        gbc.gridx = 1;
        farbeField = new JTextField(20);
        mainPanel.add(farbeField, gbc);
        row++;

        // Leergewicht
        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Leergewicht (kg):*"), gbc);
        gbc.gridx = 1;
        leergewichtField = new JTextField(20);
        mainPanel.add(leergewichtField, gbc);
        row++;

        // Spezifische Felder (Auto/Transporter)
        cardLayout = new CardLayout();
        spezifischPanel = new JPanel(cardLayout);

        // Auto spezifisch
        JPanel autoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints autoGbc = new GridBagConstraints();
        autoGbc.fill = GridBagConstraints.HORIZONTAL;
        autoGbc.insets = new Insets(5, 5, 5, 5);

        autoGbc.gridx = 0; autoGbc.gridy = 0;
        autoPanel.add(new JLabel("Aufbau:*"), autoGbc);
        autoGbc.gridx = 1;
        aufbauCombo = new JComboBox<>(new String[]{"Kleinwagen", "Limousine", "Kombi", "SUV", "Cabriolet"});
        autoPanel.add(aufbauCombo, autoGbc);

        autoGbc.gridx = 0; autoGbc.gridy = 1;
        autoPanel.add(new JLabel("Navigationssystem:"), autoGbc);
        autoGbc.gridx = 1;
        naviCheckbox = new JCheckBox();
        autoPanel.add(naviCheckbox, autoGbc);

        // Transporter spezifisch
        JPanel transporterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints transGbc = new GridBagConstraints();
        transGbc.fill = GridBagConstraints.HORIZONTAL;
        transGbc.insets = new Insets(5, 5, 5, 5);

        transGbc.gridx = 0; transGbc.gridy = 0;
        transporterPanel.add(new JLabel("Max. Zuladung (kg):*"), transGbc);
        transGbc.gridx = 1;
        zuladungField = new JTextField(20);
        transporterPanel.add(zuladungField, transGbc);

        spezifischPanel.add(autoPanel, "Auto");
        spezifischPanel.add(transporterPanel, "Transporter");

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        mainPanel.add(spezifischPanel, gbc);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(e -> speichern());
        buttonPanel.add(speichernButton);

        JButton abbrechenButton = new JButton("Abbrechen");
        abbrechenButton.addActionListener(e -> {
            geaendert = false;
            dispose();
        });
        buttonPanel.add(abbrechenButton);

        add(buttonPanel, BorderLayout.SOUTH);

        zeigeSpezifischeFelder();
    }

    private void zeigeSpezifischeFelder() {
        String typ = (String) typCombo.getSelectedItem();
        cardLayout.show(spezifischPanel, typ);
    }

    private void fuelleFelderAus() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        markeField.setText(fahrzeug.getMarke());
        modellField.setText(fahrzeug.getModell());
        hubraumField.setText(String.valueOf(fahrzeug.getHubraum()));
        treibstoffCombo.setSelectedItem(fahrzeug.getTreibstoffart());
        kmStandField.setText(String.valueOf(fahrzeug.getKmStand()));
        leistungField.setText(String.valueOf(fahrzeug.getLeistung()));
        if (fahrzeug.getErstZulassung() != null) {
            erstZulassungField.setText(sdf.format(fahrzeug.getErstZulassung()));
        }
        farbeField.setText(fahrzeug.getAussenfarbe());
        leergewichtField.setText(String.valueOf(fahrzeug.getLeergewicht()));

        if (fahrzeug instanceof Auto) {
            typCombo.setSelectedItem("Auto");
            Auto auto = (Auto) fahrzeug;
            aufbauCombo.setSelectedItem(auto.getAufbau());
            naviCheckbox.setSelected(false); // Standardwert, da nicht in Klasse
        } else if (fahrzeug instanceof Transporter) {
            typCombo.setSelectedItem("Transporter");
            Transporter trans = (Transporter) fahrzeug;
            zuladungField.setText(String.valueOf(trans.getMaxZuladung()));
        }

        typCombo.setEnabled(false); // Typ kann nicht geändert werden
    }

    private void speichern() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            // Validierung
            if (markeField.getText().trim().isEmpty() ||
                    modellField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Bitte füllen Sie alle Pflichtfelder aus.",
                        "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String marke = markeField.getText().trim();
            String modell = modellField.getText().trim();
            int hubraum = Integer.parseInt(hubraumField.getText().trim());
            String treibstoff = (String) treibstoffCombo.getSelectedItem();
            int kmStand = Integer.parseInt(kmStandField.getText().trim());
            int leistung = Integer.parseInt(leistungField.getText().trim());

            Date erstZulassung = null;
            try {
                erstZulassung = sdf.parse(erstZulassungField.getText().trim());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this,
                        "Bitte geben Sie ein gültiges Datum ein (TT.MM.JJJJ).",
                        "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String farbe = farbeField.getText().trim();
            int leergewicht = Integer.parseInt(leergewichtField.getText().trim());

            String typ = (String) typCombo.getSelectedItem();

            if (fahrzeug == null) {
                // Neues Fahrzeug erstellen
                if ("Auto".equals(typ)) {
                    String aufbau = (String) aufbauCombo.getSelectedItem();
                    boolean navi = naviCheckbox.isSelected();

                    fahrzeug = new Auto(marke, modell, hubraum, treibstoff, kmStand,
                            leistung, erstZulassung, farbe, leergewicht,
                            aufbau, navi);
                } else {
                    int zuladung = Integer.parseInt(zuladungField.getText().trim());

                    fahrzeug = new Transporter(marke, modell, hubraum, treibstoff,
                            kmStand, leistung, erstZulassung,
                            farbe, leergewicht, zuladung);
                }
            } else {
                // Bestehendes Fahrzeug aktualisieren
                fahrzeug.setMarke(marke);
                fahrzeug.setModell(modell);
                fahrzeug.setHubraum(hubraum);
                fahrzeug.setTreibstoffart(treibstoff);
                fahrzeug.setKmStand(kmStand);
                fahrzeug.setLeistung(leistung);
                fahrzeug.setErstZulassung(erstZulassung);
                fahrzeug.setAussenfarbe(farbe);
                fahrzeug.setLeergewicht(leergewicht);

                if (fahrzeug instanceof Auto) {
                    Auto auto = (Auto) fahrzeug;
                    auto.setAufbau((String) aufbauCombo.getSelectedItem());
                } else if (fahrzeug instanceof Transporter) {
                    Transporter trans = (Transporter) fahrzeug;
                    trans.setMaxZuladung(Integer.parseInt(zuladungField.getText().trim()));
                }
            }

            geaendert = true;
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Bitte geben Sie gültige Zahlen ein.",
                    "Fehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ein Fehler ist aufgetreten: " + e.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Fahrzeug getFahrzeug() {
        return fahrzeug;
    }

    public boolean wurdeGeaendert() {
        return geaendert;
    }
}