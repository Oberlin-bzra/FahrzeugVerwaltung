package NObwz;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KundenDialog extends JDialog {
    private Kunde kunde;
    private boolean geaendert = false;

    private JTextField nameField, vornameField, strasseField, plzField;
    private JTextField wohnortField, telPrivatField, telMobileField, emailField;
    private JTextField geburtsdatumField;

    public KundenDialog(JFrame parent, Kunde kunde) {
        super(parent, kunde == null ? "Neuer Kunde" : "Kunde bearbeiten", true);
        this.kunde = kunde;

        setSize(450, 550);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        initComponents();

        if (kunde != null) {
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

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Name:*"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        mainPanel.add(nameField, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Vorname:*"), gbc);
        gbc.gridx = 1;
        vornameField = new JTextField(20);
        mainPanel.add(vornameField, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Strasse und Nummer:*"), gbc);
        gbc.gridx = 1;
        strasseField = new JTextField(20);
        mainPanel.add(strasseField, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("PLZ:*"), gbc);
        gbc.gridx = 1;
        plzField = new JTextField(20);
        mainPanel.add(plzField, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Wohnort:*"), gbc);
        gbc.gridx = 1;
        wohnortField = new JTextField(20);
        mainPanel.add(wohnortField, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Telefon Privat:"), gbc);
        gbc.gridx = 1;
        telPrivatField = new JTextField(20);
        mainPanel.add(telPrivatField, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Telefon Mobil:"), gbc);
        gbc.gridx = 1;
        telMobileField = new JTextField(20);
        mainPanel.add(telMobileField, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("E-Mail:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        mainPanel.add(emailField, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        mainPanel.add(new JLabel("Geburtsdatum (TT.MM.JJJJ):*"), gbc);
        gbc.gridx = 1;
        geburtsdatumField = new JTextField(20);
        mainPanel.add(geburtsdatumField, gbc);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);

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
    }

    private void fuelleFelderAus() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        nameField.setText(kunde.getName());
        vornameField.setText(kunde.getVorname());
        strasseField.setText(kunde.getStrasseNummer());
        plzField.setText(kunde.getPlz());
        wohnortField.setText(kunde.getWohnort());
        telPrivatField.setText(kunde.getTelefonPrivat());
        telMobileField.setText(kunde.getTelefonMobile());
        emailField.setText(kunde.getEmail());
        if (kunde.getGeburtsdatum() != null) {
            geburtsdatumField.setText(sdf.format(kunde.getGeburtsdatum()));
        }
    }

    private void speichern() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            if (nameField.getText().trim().isEmpty() ||
                    vornameField.getText().trim().isEmpty() ||
                    strasseField.getText().trim().isEmpty() ||
                    plzField.getText().trim().isEmpty() ||
                    wohnortField.getText().trim().isEmpty() ||
                    geburtsdatumField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Bitte füllen Sie alle Pflichtfelder aus.",
                        "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = nameField.getText().trim();
            String vorname = vornameField.getText().trim();
            String strasse = strasseField.getText().trim();
            String plz = plzField.getText().trim();
            String wohnort = wohnortField.getText().trim();
            String telPrivat = telPrivatField.getText().trim();
            String telMobile = telMobileField.getText().trim();
            String email = emailField.getText().trim();

            Date geburtsdatum = null;
            try {
                geburtsdatum = sdf.parse(geburtsdatumField.getText().trim());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this,
                        "Bitte geben Sie ein gültiges Datum ein (TT.MM.JJJJ).",
                        "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (kunde == null) {
                kunde = new Kunde(name, vorname, strasse, plz, wohnort,
                        telPrivat, telMobile, email, geburtsdatum);
            } else {
                kunde.setName(name);
                kunde.setVorname(vorname);
                kunde.setStrasseNummer(strasse);
                kunde.setPlz(plz);
                kunde.setWohnort(wohnort);
                kunde.setTelefonPrivat(telPrivat);
                kunde.setTelefonMobile(telMobile);
                kunde.setEmail(email);
                kunde.setGeburtsdatum(geburtsdatum);
            }

            geaendert = true;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ein Fehler ist aufgetreten: " + e.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Kunde getKunde() {
        return kunde;
    }

    public boolean wurdeGeaendert() {
        return geaendert;
    }
}