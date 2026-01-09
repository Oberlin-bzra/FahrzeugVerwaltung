package NObwz;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class FahrzeugverwaltungTest {
    private Fahrzeugverwaltung verwaltung;
    private Auto testAuto;
    private Transporter testTransporter;

    @BeforeEach
    void setUp() {
        verwaltung = new Fahrzeugverwaltung();

        // Test-Auto erstellen
        Date erstZulassung = new Date();
        testAuto = new Auto(
                "BMW", "X5", 4395, "Benzin", 50000, 625,
                erstZulassung, "Schwarz", 2475, "SUV", true
        );

        // Test-Transporter erstellen
        testTransporter = new Transporter(
                "Mercedes", "Sprinter", 2143, "Diesel", 80000, 163,
                erstZulassung, "Weiss", 2100, 1500
        );
    }

    @Test
    @DisplayName("TC-FV-01: Fahrzeug erfassen (Auto)")
    void testFahrzeugErfassenAuto() {
        // Act
        verwaltung.fahrzeugErfassen(testAuto);

        // Assert
        List<Fahrzeug> fahrzeuge = verwaltung.getAlleFahrzeuge();
        assertEquals(1, fahrzeuge.size(), "Es sollte genau ein Fahrzeug in der Liste sein");

        Fahrzeug gespeichert = fahrzeuge.get(0);
        assertTrue(gespeichert instanceof Auto, "Das Fahrzeug sollte vom Typ Auto sein");
        assertEquals("BMW", gespeichert.getMarke());
        assertEquals("X5", gespeichert.getModell());
        assertEquals(4395, gespeichert.getHubraum());

        Auto auto = (Auto) gespeichert;
        assertEquals("SUV", auto.getAufbau());
    }

    @Test
    @DisplayName("TC-FV-02: Fahrzeug erfassen (Transporter)")
    void testFahrzeugErfassenTransporter() {
        // Act
        verwaltung.fahrzeugErfassen(testTransporter);

        // Assert
        List<Fahrzeug> fahrzeuge = verwaltung.getAlleFahrzeuge();
        assertEquals(1, fahrzeuge.size());

        Fahrzeug gespeichert = fahrzeuge.get(0);
        assertTrue(gespeichert instanceof Transporter, "Das Fahrzeug sollte vom Typ Transporter sein");
        assertEquals("Mercedes", gespeichert.getMarke());
        assertEquals("Sprinter", gespeichert.getModell());

        Transporter trans = (Transporter) gespeichert;
        assertEquals(1500, trans.getMaxZuladung());
    }

    @Test
    @DisplayName("TC-FV-03: Fahrzeugsuche nach Marke")
    void testFahrzeugSucheNachMarke() {
        // Arrange
        verwaltung.fahrzeugErfassen(testAuto);
        verwaltung.fahrzeugErfassen(testTransporter);

        Auto audi = new Auto(
                "Audi", "RS6", 3996, "Benzin", 5000, 630,
                new Date(), "Schwarz", 2100, "Kombi", true
        );
        verwaltung.fahrzeugErfassen(audi);

        // Act - Suche nach BMW
        List<Fahrzeug> ergebnis = verwaltung.fahrzeugSuchen("BMW", "");

        // Assert
        assertEquals(1, ergebnis.size(), "Es sollte genau ein BMW gefunden werden");
        assertEquals("BMW", ergebnis.get(0).getMarke());
    }

    @Test
    @DisplayName("TC-FV-03: Fahrzeugsuche nach Modell")
    void testFahrzeugSucheNachModell() {
        // Arrange
        verwaltung.fahrzeugErfassen(testAuto);
        verwaltung.fahrzeugErfassen(testTransporter);

        Auto porsche = new Auto(
                "Porsche", "GT3 RS", 3996, "Benzin", 10000, 525,
                new Date(), "Weiss", 1450, "Sportwagen", false
        );
        verwaltung.fahrzeugErfassen(porsche);

        // Act
        List<Fahrzeug> ergebnis = verwaltung.fahrzeugSuchen("", "Sprinter");

        // Assert
        assertEquals(1, ergebnis.size(), "Es sollte genau ein Sprinter gefunden werden");
        assertEquals("Sprinter", ergebnis.get(0).getModell());
    }

    @Test
    @DisplayName("TC-FV-03: Fahrzeugsuche kombiniert (Marke und Modell)")
    void testFahrzeugSucheKombiniert() {
        // Arrange
        verwaltung.fahrzeugErfassen(testAuto);
        verwaltung.fahrzeugErfassen(testTransporter);

        // Act
        List<Fahrzeug> ergebnis = verwaltung.fahrzeugSuchen("BMW", "X5");

        // Assert
        assertEquals(1, ergebnis.size());
        assertEquals("BMW", ergebnis.get(0).getMarke());
        assertEquals("X5", ergebnis.get(0).getModell());
    }

    @Test
    @DisplayName("TC-FV-03: Fahrzeugsuche - keine Treffer")
    void testFahrzeugSucheKeineTreffer() {
        // Arrange
        verwaltung.fahrzeugErfassen(testAuto);

        // Act
        List<Fahrzeug> ergebnis = verwaltung.fahrzeugSuchen("Tesla", "Model S");

        // Assert
        assertTrue(ergebnis.isEmpty(), "Es sollten keine Fahrzeuge gefunden werden");
    }

    @Test
    @DisplayName("TC-FV-03: Fahrzeugsuche - Case-Insensitive")
    void testFahrzeugSucheCaseInsensitive() {
        // Arrange
        verwaltung.fahrzeugErfassen(testAuto);

        // Act
        List<Fahrzeug> ergebnis = verwaltung.fahrzeugSuchen("bmw", "x5");

        // Assert
        assertEquals(1, ergebnis.size(), "Suche sollte case-insensitive sein");
    }

    @Test
    @DisplayName("TC-FV-04: Fahrzeug löschen")
    void testFahrzeugLoeschen() {
        // Arrange
        verwaltung.fahrzeugErfassen(testAuto);
        verwaltung.fahrzeugErfassen(testTransporter);
        assertEquals(2, verwaltung.getAlleFahrzeuge().size());

        // Act
        verwaltung.fahrzeugLoeschen(testAuto);

        // Assert
        List<Fahrzeug> fahrzeuge = verwaltung.getAlleFahrzeuge();
        assertEquals(1, fahrzeuge.size(), "Es sollte noch ein Fahrzeug übrig sein");
        assertEquals("Mercedes", fahrzeuge.get(0).getMarke());
    }

    @Test
    @DisplayName("Alle Fahrzeuge löschen")
    void testAlleFahrzeugeLoeschen() {
        // Arrange
        verwaltung.fahrzeugErfassen(testAuto);
        verwaltung.fahrzeugErfassen(testTransporter);

        // Act
        verwaltung.fahrzeugLoeschen(testAuto);
        verwaltung.fahrzeugLoeschen(testTransporter);

        // Assert
        assertTrue(verwaltung.getAlleFahrzeuge().isEmpty(), "Liste sollte leer sein");
    }

    @Test
    @DisplayName("Mehrere Fahrzeuge erfassen")
    void testMehrereFahrzeugeErfassen() {
        // Act
        for (int i = 0; i < 5; i++) {
            Auto auto = new Auto(
                    "Marke" + i, "Modell" + i, 2000, "Benzin", 10000 * i, 150 + i,
                    new Date(), "Farbe" + i, 1500, "Limousine", false
            );
            verwaltung.fahrzeugErfassen(auto);
        }

        // Assert
        assertEquals(5, verwaltung.getAlleFahrzeuge().size());
    }
}