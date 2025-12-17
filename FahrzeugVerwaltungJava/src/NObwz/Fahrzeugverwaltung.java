package NObwz;

import java.util.ArrayList;
import java.util.List;

public class Fahrzeugverwaltung {
    private List<Fahrzeug> fahrzeuge;

    public Fahrzeugverwaltung() {
        this.fahrzeuge = new ArrayList<>();
    }

    public void fahrzeugErfassen(Fahrzeug fahrzeug) {
        fahrzeuge.add(fahrzeug);
    }

    public void fahrzeugBearbeiten(Fahrzeug fahrzeug) {}

    public List<Fahrzeug> fahrzeugSuchen(String marke, String modell) {
        List<Fahrzeug> ergebnisse = new ArrayList<>();

        for (Fahrzeug f : fahrzeuge) {
            boolean markePasst = marke == null || marke.isEmpty() ||
                    f.getMarke().toLowerCase().contains(marke.toLowerCase());
            boolean modellPasst = modell == null || modell.isEmpty() ||
                    f.getModell().toLowerCase().contains(modell.toLowerCase());

            if (markePasst && modellPasst) {
                ergebnisse.add(f);
            }
        }

        return ergebnisse;
    }

    public List<Fahrzeug> getAlleFahrzeuge() {
        return new ArrayList<>(fahrzeuge);
    }

    public void fahrzeugLoeschen(Fahrzeug fahrzeug) {
        fahrzeuge.remove(fahrzeug);
    }
}