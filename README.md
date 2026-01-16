# Fahrzeugverwaltung - IdealCar4You

Eine Desktop-Applikation zur effizienten Verwaltung von Fahrzeugen und Kundendaten für die Firma IdealCar4You.

## 📋 Projektübersicht

Diese Applikation wurde im Rahmen des Moduls "Vertiefung Objektorientiertes Programmieren" (VOOP) entwickelt. Sie ermöglicht die Verwaltung von verschiedenen Fahrzeugtypen (Autos und Transporter) sowie Kundendaten mit einer benutzerfreundlichen Java Swing Oberfläche.

## ✨ Funktionen

### Fahrzeugverwaltung
- **Fahrzeuge erfassen**: Neue Fahrzeuge mit allen Basisdaten und typspezifischen Attributen hinzufügen
- **Fahrzeuge bearbeiten**: Bestehende Fahrzeugdaten ändern (z.B. Km-Stand, Farbe)
- **Fahrzeuge anzeigen**: Übersichtliche Darstellung aller verfügbaren Fahrzeuge
- **Fahrzeugsuche**: Filterung nach Marke und Modell

**Unterstützte Fahrzeugtypen:**
- **Autos**: Mit Aufbau (Kleinwagen, Limousine, Kombi, SUV, Cabriolet) und Navigationssystem
- **Transporter**: Mit maximaler Zuladung in kg

### Kundenverwaltung
- Kundendaten erfassen, anzeigen und bearbeiten
- Verwaltung von Name, Adresse, Kontaktdaten und Geburtsdatum

### Datenpersistenz
- Automatisches Speichern in JSON-Dateien (`fahrzeuge.json`, `kunden.json`)
- Laden der Daten beim Applikationsstart

## 🛠️ Technologie-Stack

- **Programmiersprache**: Java
- **UI-Framework**: Java Swing
- **Build-Tool**: IntelliJ IDEA
- **JSON-Library**: Gson 2.10.1
- **Testing**: JUnit 5.8.1
- **Java Version**: OpenJDK 24

## 📦 Installation

### Voraussetzungen
- OpenJDK 24 oder höher
- IntelliJ IDEA (empfohlen)

### Setup
1. Repository klonen:
   ```bash
   git clone https://github.com/Oberlin-bzra/FahrzeugVerwaltung.git
   ```

2. Projekt in IntelliJ IDEA öffnen

3. Dependencies werden automatisch geladen (Gson, JUnit)

## 🚀 Ausführung

1. Projekt in IntelliJ IDEA öffnen
2. Main-Klasse im Verzeichnis `FahrzeugVerwaltungJava` finden
3. Applikation starten (Run)

Die Applikation lädt automatisch vorhandene Daten aus den JSON-Dateien und speichert Änderungen beim Beenden.

## 🏗️ Projektstruktur

```
FahrzeugVerwaltung/
├── FahrzeugVerwaltungJava/
│   ├── src/
│   │   ├── model/          # Datenmodelle (Fahrzeug, Auto, Transporter, Kunde)
│   │   ├── controller/     # Geschäftslogik (Fahrzeugverwaltung, Kundenverwaltung)
│   │   ├── view/           # Java Swing UI-Komponenten
│   │   └── service/        # JSON-Persistenz Service
│   └── test/               # Unit Tests
├── fahrzeuge.json          # Fahrzeugdaten
├── kunden.json             # Kundendaten
└── README.md
```

## ✅ Qualitätssicherung

Das Projekt wurde umfassend getestet:
- **40 Testfälle** insgesamt
- **23 automatisierte Unit Tests** (Fahrzeugverwaltung, Kundenverwaltung, Persistenz)
- **17 manuelle GUI-Tests**
- **Erfolgsrate: 100%**

### Testabdeckung
- CRUD-Operationen für Fahrzeuge und Kunden
- Suchfunktionalität (case-insensitive)
- JSON-Persistenz mit Polymorphie-Erhaltung (Auto/Transporter)
- UI-Validierung und Datenfluss

## 👥 Akteure

- **Fahrzeugpark Manager**: Verwaltung der Fahrzeuge (Erfassen, Ändern)
- **Kundenberater**: Verwaltung der Kundendaten, Anzeigen und Suchen von Fahrzeugen

## 📚 Dokumentation

Die vollständige Projektdokumentation umfasst:
- Anforderungskatalog (funktionale und nicht-funktionale Anforderungen)
- Use Case Diagramme und Spezifikationen
- UML-Klassendiagramm
- Testprotokolle und Testfälle
- Projektplan mit Meilensteinen

## 📝 Projekt-Details

**Modul**: Vertiefung Objektorientiertes Programmieren (VOOP)  
**Schule**: Berufs- und Weiterbildungszentrum Rapperswil-Jona  
**Projektdauer**: 35 Lektionen  
**Abgabe**: Januar 2025

## 📄 Lizenz

Dieses Projekt wurde für Bildungszwecke erstellt.

---

**Entwickelt mit ❤️ im Rahmen des VOOP-Moduls**
