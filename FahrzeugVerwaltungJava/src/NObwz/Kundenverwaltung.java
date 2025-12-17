package NObwz;

import java.util.ArrayList;
import java.util.List;

public class Kundenverwaltung {
    private List<Kunde> kunden;

    public Kundenverwaltung() {
        this.kunden = new ArrayList<>();
    }

    public void kundeErfassen(Kunde kunde) {
        kunden.add(kunde);
    }

    public void kundeBearbeiten(Kunde kunde) {
        // Aktualisierung erfolgt direkt am Objekt
        // Da Objekte per Referenz übergeben werden
    }

    public List<Kunde> getAlleKunden() {
        return new ArrayList<>(kunden);
    }

    public void kundeLoeschen(Kunde kunde) {
        kunden.remove(kunde);
    }
}