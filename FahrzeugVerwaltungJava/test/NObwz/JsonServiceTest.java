package NObwz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class JsonServiceTest {
    private JsonService jsonService;
    private static final String BACKUP_FAHRZEUGE = "fahrzeuge_backup.json";
    private static final String BACKUP_KUNDEN = "kunden_backup.json";
    private static final String FAHRZEUGE_FILE = "fahrzeuge.json";
    private static final String KUNDEN_FILE = "kunden.json";

    @BeforeEach
    void setUp() {
        jsonService = new JsonService();

        // Backup erstellen falls Dateien existieren
        File fzgFile = new File(FAHRZEUGE_FILE);
        File kunFile = new File(KUNDEN_FILE);

        if (fzgFile.exists()) {
            fzgFile.renameTo(new File(BACKUP_FAHRZEUGE));
        }
        if (kunFile.exists()) {
            kunFile.renameTo(new File(BACKUP_KUNDEN));
        }
    }

    @AfterEach
    void tearDown() {
        // Original-Dateien wiederherstellen
        new File(FAHRZEUGE_FILE).delete();
        new File(KUNDEN_FILE).delete();

        File backupFzg = new File(BACKUP_FAHRZEUGE);
        File backupKun = new File(BACKUP_KUNDEN);

        if (backupFzg.exists()) {
            backupFzg.renameTo(new File(FAHRZEUGE_FILE));
        }
        if (backupKun.exists()) {
            backupKun.renameTo(new File(KUNDEN_FILE));
        }
    }

    @Test
    @DisplayName("TC-PS-01: Fahrzeuge speichern und laden - Auto")
    void testFahrzeugeSpeichernUndLadenAuto() {
        List<Fahrzeug> fahrzeuge = new ArrayList<>();
        Date erstZulassung = new Date(123, 5, 15);

        Auto auto = new Auto(
                "BMW", "X5", 4395, "Benzin", 50000, 625,
                erstZulassung, "Schwarz", 2475, "SUV", true
        );
        fahrzeuge.add(auto);

        jsonService.speichereFahrzeuge(fahrzeuge);
        List<Fahrzeug> geladen = jsonService.ladeFahrzeuge();

        assertEquals(1, geladen.size());
        assertTrue(geladen.get(0) instanceof Auto);

        Fahrzeug geladenFahrzeug = geladen.get(0);
        assertEquals("BMW", geladenFahrzeug.getMarke());
        assertEquals("X5", geladenFahrzeug.getModell());
        assertEquals(4395, geladenFahrzeug.getHubraum());
        assertEquals("Benzin", geladenFahrzeug.getTreibstoffart());

        Auto geladenAuto = (Auto) geladenFahrzeug;
        assertEquals("SUV", geladenAuto.getAufbau());
    }

    @Test
    @DisplayName("TC-PS-01: Fahrzeuge speichern und laden - Transporter")
    void testFahrzeugeSpeichernUndLadenTransporter() {
        List<Fahrzeug> fahrzeuge = new ArrayList<>();

        Transporter trans = new Transporter(
                "Mercedes", "Sprinter", 2143, "Diesel", 80000, 163,
                new Date(), "Weiss", 2100, 1500
        );
        fahrzeuge.add(trans);

        jsonService.speichereFahrzeuge(fahrzeuge);
        List<Fahrzeug> geladen = jsonService.ladeFahrzeuge();

        assertEquals(1, geladen.size());
        assertTrue(geladen.get(0) instanceof Transporter);

        Transporter geladenTrans = (Transporter) geladen.get(0);
        assertEquals("Mercedes", geladenTrans.getMarke());
        assertEquals(1500, geladenTrans.getMaxZuladung());
    }

    @Test
    @DisplayName("TC-PS-01: Mehrere Fahrzeuge speichern und laden")
    void testMehrereFahrzeugeSpeichernUndLaden() {
        List<Fahrzeug> fahrzeuge = new ArrayList<>();

        fahrzeuge.add(new Auto(
                "BMW", "X5", 4395, "Benzin", 50000, 625,
                new Date(), "Schwarz", 2475, "SUV", true
        ));

        fahrzeuge.add(new Transporter(
                "Mercedes", "Sprinter", 2143, "Diesel", 80000, 163,
                new Date(), "Weiss", 2100, 1500
        ));

        fahrzeuge.add(new Auto(
                "Porsche", "GT3 RS", 3996, "Benzin", 10000, 525,
                new Date(), "Weiss", 1450, "Sportwagen", false
        ));

        jsonService.speichereFahrzeuge(fahrzeuge);
        List<Fahrzeug> geladen = jsonService.ladeFahrzeuge();

        assertEquals(3, geladen.size());
        long autos = geladen.stream().filter(f -> f instanceof Auto).count();
        long transporter = geladen.stream().filter(f -> f instanceof Transporter).count();
        assertEquals(2, autos);
        assertEquals(1, transporter);
    }

    @Test
    @DisplayName("TC-PS-02: Kunden speichern und laden")
    void testKundenSpeichernUndLaden() {
        List<Kunde> kunden = new ArrayList<>();
        Date geburtsdatum = new Date(90, 4, 15);

        Kunde kunde1 = new Kunde(
                "Müller", "Hans", "Hauptstrasse 10", "8640",
                "Rapperswil", "055 123 45 67", "079 123 45 67",
                "hans.mueller@email.ch", geburtsdatum
        );

        Kunde kunde2 = new Kunde(
                "Schmidt", "Anna", "Bahnhofstrasse 5", "8001",
                "Zürich", "044 111 22 33", "079 999 88 77",
                "anna.schmidt@email.ch", new Date(85, 10, 20)
        );

        kunden.add(kunde1);
        kunden.add(kunde2);

        jsonService.speichereKunden(kunden);
        List<Kunde> geladen = jsonService.ladeKunden();

        assertEquals(2, geladen.size());

        Kunde geladenKunde1 = geladen.get(0);
        assertEquals("Müller", geladenKunde1.getName());
        assertEquals("Hans", geladenKunde1.getVorname());
        assertEquals("8640", geladenKunde1.getPlz());
        assertEquals("hans.mueller@email.ch", geladenKunde1.getEmail());

        Kunde geladenKunde2 = geladen.get(1);
        assertEquals("Schmidt", geladenKunde2.getName());
        assertEquals("Anna", geladenKunde2.getVorname());
    }

    @Test
    @DisplayName("TC-PS-03: Leere Fahrzeug-Datei laden")
    void testLeereFahrzeugDateiLaden() {
        // Stelle sicher, dass keine Datei existiert
        new File(FAHRZEUGE_FILE).delete();

        List<Fahrzeug> geladen = jsonService.ladeFahrzeuge();

        assertNotNull(geladen, "Liste sollte nicht null sein");
        assertTrue(geladen.isEmpty(), "Liste sollte leer sein");
    }

    @Test
    @DisplayName("TC-PS-03: Leere Kunden-Datei laden")
    void testLeereKundenDateiLaden() {
        new File(KUNDEN_FILE).delete();

        List<Kunde> geladen = jsonService.ladeKunden();

        assertNotNull(geladen, "Liste sollte nicht null sein");
        assertTrue(geladen.isEmpty(), "Liste sollte leer sein");
    }

    @Test
    @DisplayName("Leere Listen speichern")
    void testLeereListen Speichern() {
        List<Fahrzeug> leereFahrzeuge = new ArrayList<>();
        List<Kunde> leereKunden = new ArrayList<>();

        jsonService.speichereFahrzeuge(leereFahrzeuge);
        jsonService.speichereKunden(leereKunden);

        List<Fahrzeug> geladeneFahrzeuge = jsonService.ladeFahrzeuge();
        List<Kunde> geladeneKunden = jsonService.ladeKunden();

        assertTrue(geladeneFahrzeuge.isEmpty());
        assertTrue(geladeneKunden.isEmpty());
    }
}