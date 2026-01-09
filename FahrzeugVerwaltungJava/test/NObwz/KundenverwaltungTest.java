package NObwz;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class KundenverwaltungTest {
    private Kundenverwaltung verwaltung;
    private Kunde testKunde;

    @BeforeEach
    void setUp() {
        verwaltung = new Kundenverwaltung();

        Date geburtsdatum = new Date(90, 4, 15); // 15.05.1990
        testKunde = new Kunde(
                "Müller", "Hans", "Hauptstrasse 10", "8640",
                "Rapperswil", "055 123 45 67", "079 123 45 67",
                "hans.mueller@email.ch", geburtsdatum
        );
    }

    @Test
    @DisplayName("TC-KV-01: Kunde erfassen")
    void testKundeErfassen() {
        // Act
        verwaltung.kundeErfassen(testKunde);

        // Assert
        List<Kunde> kunden = verwaltung.getAlleKunden();
        assertEquals(1, kunden.size(), "Es sollte genau ein Kunde in der Liste sein");

        Kunde gespeichert = kunden.get(0);
        assertEquals("Müller", gespeichert.getName());
        assertEquals("Hans", gespeichert.getVorname());
        assertEquals("8640", gespeichert.getPlz());
        assertEquals("Rapperswil", gespeichert.getWohnort());
        assertEquals("hans.mueller@email.ch", gespeichert.getEmail());
    }

    @Test
    @DisplayName("TC-KV-02: Kunde bearbeiten")
    void testKundeBearbeiten() {
        // Arrange
        verwaltung.kundeErfassen(testKunde);

        // Act - Daten ändern
        testKunde.setTelefonMobile("079 999 88 77");
        testKunde.setEmail("neue.email@test.ch");
        verwaltung.kundeBearbeiten(testKunde);

        // Assert
        List<Kunde> kunden = verwaltung.getAlleKunden();
        Kunde geaendert = kunden.get(0);
        assertEquals("079 999 88 77", geaendert.getTelefonMobile());
        assertEquals("neue.email@test.ch", geaendert.getEmail());
    }

    @Test
    @DisplayName("TC-KV-03: Kunde löschen")
    void testKundeLoeschen() {
        // Arrange
        verwaltung.kundeErfassen(testKunde);
        assertEquals(1, verwaltung.getAlleKunden().size());

        // Act
        verwaltung.kundeLoeschen(testKunde);

        // Assert
        assertTrue(verwaltung.getAlleKunden().isEmpty(), "Liste sollte leer sein");
    }

    @Test
    @DisplayName("Mehrere Kunden erfassen")
    void testMehrereKundenErfassen() {
        // Arrange & Act
        for (int i = 0; i < 3; i++) {
            Kunde kunde = new Kunde(
                    "Name" + i, "Vorname" + i, "Strasse " + i, "864" + i,
                    "Ort" + i, "055 000 00 0" + i, "079 000 00 0" + i,
                    "kunde" + i + "@test.ch", new Date()
            );
            verwaltung.kundeErfassen(kunde);
        }

        // Assert
        assertEquals(3, verwaltung.getAlleKunden().size());
    }

    @Test
    @DisplayName("Kunde mit vollständigen Kontaktdaten")
    void testKundeMitVollstaendigenDaten() {
        // Act
        verwaltung.kundeErfassen(testKunde);

        // Assert
        Kunde kunde = verwaltung.getAlleKunden().get(0);
        assertNotNull(kunde.getName());
        assertNotNull(kunde.getVorname());
        assertNotNull(kunde.getStrasseNummer());
        assertNotNull(kunde.getPlz());
        assertNotNull(kunde.getWohnort());
        assertNotNull(kunde.getTelefonPrivat());
        assertNotNull(kunde.getTelefonMobile());
        assertNotNull(kunde.getEmail());
        assertNotNull(kunde.getGeburtsdatum());
    }

    @Test
    @DisplayName("Kunde toString() Methode")
    void testKundeToString() {
        String expected = "Hans Müller (Rapperswil)";
        assertEquals(expected, testKunde.toString());
    }
}