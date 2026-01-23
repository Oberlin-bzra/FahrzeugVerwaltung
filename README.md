# Fahrzeugverwaltung - IdealCar4You

Eine Desktop-Applikation zur effizienten Verwaltung von Fahrzeugen und Kundendaten für die fiktive Firma IdealCar4You mit integriertem Login-System.

## 📋 Projektübersicht

Diese Applikation wurde im Rahmen des Moduls "Vertiefung Objektorientiertes Programmieren" (VOOP) entwickelt. Sie ermöglicht die Verwaltung von verschiedenen Fahrzeugtypen (Autos und Transporter) sowie Kundendaten mit einer benutzerfreundlichen Java Swing Oberfläche und rollenbasiertem Zugriffssystem.

## ✨ Funktionen

### Authentifizierung & Benutzerverwaltung
- **Login-System**: Sichere Anmeldung mit Benutzername und Passwort
- **Passwortverschlüsselung**: SHA-256 Hash-Algorithmus für sichere Passwortspeicherung
- **Benutzerregistrierung**: Neue Benutzer können direkt über die Login-Oberfläche registriert werden
- **Rollenverwaltung**: Zwei Benutzerrollen (Fahrzeugpark Manager, Kundenberater)
- **Session-Management**: Benutzerinformationen werden während der Sitzung angezeigt
- **Abmeldefunktion**: Sicheres Abmelden über das Account-Menü

**Vordefinierte Benutzer:**
- **Admin-Account**: Username: `admin`, Passwort: `admin`, Rolle: Fahrzeugpark Manager
- **Berater-Account**: Username: `berater`, Passwort: `berater`, Rolle: Kundenberater

### Fahrzeugverwaltung
- **Fahrzeuge erfassen**: Neue Fahrzeuge mit allen Basisdaten und typspezifischen Attributen hinzufügen
- **Fahrzeuge bearbeiten**: Bestehende Fahrzeugdaten ändern (z.B. Km-Stand, Farbe)
- **Fahrzeuge anzeigen**: Übersichtliche Darstellung aller verfügbaren Fahrzeuge
- **Fahrzeugsuche**: Filterung nach Marke und Modell
- **Fahrzeuge löschen**: Entfernen von Fahrzeugen aus der Verwaltung

**Unterstützte Fahrzeugtypen:**
- **Autos**: Mit Aufbau (Kleinwagen, Limousine, Kombi, SUV, Cabriolet, Sportwagen) und Navigationssystem
- **Transporter**: Mit maximaler Zuladung in kg

### Kundenverwaltung
- Kundendaten erfassen, anzeigen und bearbeiten
- Verwaltung von Name, Adresse, Kontaktdaten und Geburtsdatum
- Kundendaten löschen

### Datenpersistenz
- Automatisches Speichern in JSON-Dateien (`fahrzeuge.json`, `kunden.json`, `users.json`)
- Laden der Daten beim Applikationsstart
- Polymorphie-Erhaltung bei Fahrzeugtypen (Auto/Transporter)

## 🛠️ Technologie-Stack

- **Programmiersprache**: Java
- **UI-Framework**: Java Swing
- **Build-Tool**: IntelliJ IDEA
- **JSON-Library**: Gson 2.10.1
- **Testing**: JUnit 5.8.1
- **Java Version**: OpenJDK 24
- **Sicherheit**: SHA-256 Passwort-Hashing

## 📦 Installation

### Voraussetzungen
- OpenJDK 24 oder höher
- IntelliJ IDEA (empfohlen)
- Gson 2.10.1 Library

### Setup
1. Repository klonen:
   ```bash
   git clone https://github.com/Oberlin-bzra/FahrzeugVerwaltung.git
   ```

2. Projekt in IntelliJ IDEA öffnen

3. Dependencies werden automatisch geladen (Gson, JUnit)

4. Sicherstellen, dass Gson 2.10.1 im Classpath ist

## 🚀 Ausführung

1. Projekt in IntelliJ IDEA öffnen
2. Main-Klasse `MainWindow.java` im Verzeichnis `FahrzeugVerwaltungJava/src/NObwz` finden
3. Applikation starten (Run)
4. Login-Fenster erscheint:
   - Verwenden Sie einen der vordefinierten Accounts oder
   - Registrieren Sie einen neuen Benutzer über den "Registrieren"-Button
5. Nach erfolgreicher Anmeldung öffnet sich die Hauptanwendung

### Erste Schritte nach dem Start

**Mit vordefiniertem Account anmelden:**
```
Username: admin
Passwort: admin
```
oder
```
Username: berater
Passwort: berater
```

**Neuen Benutzer registrieren:**
1. Klicken Sie auf "Registrieren"
2. Geben Sie Benutzername und Passwort ein
3. Bestätigen Sie das Passwort
4. Wählen Sie eine Rolle aus
5. Klicken Sie auf "OK"

Die Applikation lädt automatisch vorhandene Daten aus den JSON-Dateien und speichert Änderungen beim Beenden oder über den "Speichern"-Button.

## 🏗️ Projektstruktur

```
FahrzeugVerwaltung/
├── FahrzeugVerwaltungJava/
│   ├── src/
│   │   └── NObwz/
│   │       ├── User.java                  # Benutzermodell
│   │       ├── LoginService.java          # Login-Logik & Authentifizierung
│   │       ├── LoginView.java             # Login-GUI
│   │       ├── MainWindow.java            # Hauptfenster mit Session-Management
│   │       ├── Fahrzeug.java              # Abstrakte Fahrzeugklasse
│   │       ├── Auto.java                  # Auto-Modell
│   │       ├── Transporter.java           # Transporter-Modell
│   │       ├── Fahrzeugverwaltung.java    # Fahrzeug-Controller
│   │       ├── FahrzeugPanel.java         # Fahrzeug-UI
│   │       ├── FahrzeugDialog.java        # Fahrzeug-Eingabedialog
│   │       ├── Kunde.java                 # Kunden-Modell
│   │       ├── Kundenverwaltung.java      # Kunden-Controller
│   │       ├── KundenPanel.java           # Kunden-UI
│   │       ├── KundenDialog.java          # Kunden-Eingabedialog
│   │       └── JsonService.java           # JSON-Persistenz
│   └── test/
│       └── NObwz/
│           ├── FahrzeugverwaltungTest.java
│           ├── KundenverwaltungTest.java
│           └── JsonServiceTest.java
├── fahrzeuge.json          # Fahrzeugdaten
├── kunden.json             # Kundendaten
├── users.json              # Benutzerdaten (verschlüsselt)
└── README.md
```

## 🔐 Sicherheitsfeatures

- **Passwort-Hashing**: Alle Passwörter werden mit SHA-256 gehasht
- **Keine Klartext-Speicherung**: Passwörter werden niemals im Klartext gespeichert
- **Session-Management**: Benutzerinformationen bleiben während der Sitzung erhalten
- **Rollenverwaltung**: Unterschiedliche Benutzerrollen für verschiedene Aufgaben

## ✅ Qualitätssicherung

Das Projekt wurde umfassend getestet:
- **40+ Testfälle** insgesamt
- **23 automatisierte Unit Tests** (Fahrzeugverwaltung, Kundenverwaltung, Persistenz)
- **17 manuelle GUI-Tests**
- **Zusätzliche Login-Tests** (Authentifizierung, Registrierung, Session-Management)
- **Erfolgsrate: 100%**

### Testabdeckung
- CRUD-Operationen für Fahrzeuge und Kunden
- Suchfunktionalität (case-insensitive)
- JSON-Persistenz mit Polymorphie-Erhaltung (Auto/Transporter)
- UI-Validierung und Datenfluss
- Login und Authentifizierung
- Benutzerregistrierung
- Passwort-Hashing

## 👥 Benutzerrollen

Die Applikation unterstützt zwei Benutzerrollen:

### Fahrzeugpark Manager
- Vollzugriff auf alle Funktionen
- Fahrzeuge erfassen, bearbeiten und löschen
- Kundendaten verwalten
- Für administrative Aufgaben gedacht

### Kundenberater
- Vollzugriff auf alle Funktionen
- Fahrzeuge anzeigen und suchen
- Kundendaten verwalten
- Für kundenorientierte Aufgaben gedacht

*Hinweis: In der aktuellen Version haben beide Rollen die gleichen Berechtigungen. Die Rollentrennung ist für zukünftige Erweiterungen vorbereitet.*

## 🎯 Use Cases

1. **Login durchführen**: Benutzer meldet sich mit Username und Passwort an
2. **Neuen Benutzer registrieren**: Admin kann neue Benutzer anlegen
3. **Fahrzeug erfassen**: Manager erfasst ein neues Fahrzeug im System
4. **Fahrzeug suchen**: Berater sucht nach spezifischen Fahrzeugen
5. **Kunde erfassen**: Berater legt einen neuen Kunden an
6. **Daten speichern**: System speichert alle Änderungen persistent
7. **Abmelden**: Benutzer meldet sich sicher ab

## 📚 Dokumentation

Die vollständige Projektdokumentation umfasst:
- Anforderungskatalog (funktionale und nicht-funktionale Anforderungen)
- Use Case Diagramme und Spezifikationen
- UML-Klassendiagramm (erweitert mit Login-Komponenten)
- Testprotokolle und Testfälle
- Projektplan mit Meilensteinen
- Sicherheitskonzept

## 🔧 Konfiguration

### JSON-Dateien

**users.json** - Benutzerdaten (automatisch erstellt beim ersten Start):
```json
[
  {
    "username": "admin",
    "password": "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
    "role": "Fahrzeugpark Manager"
  }
]
```

**fahrzeuge.json** - Fahrzeugdaten mit Typinformation

**kunden.json** - Kundendaten

## 🐛 Bekannte Einschränkungen

- Beide Benutzerrollen haben derzeit identische Berechtigungen
- Keine Passwort-Wiederherstellung implementiert
- Keine Passwort-Komplexitätsanforderungen
- Single-User-Session (keine Mehrbenutzer-Unterstützung gleichzeitig)

## 🚀 Zukünftige Erweiterungen

- Rollenbasierte Zugriffskontrolle (RBAC)
- Passwort-Wiederherstellungsfunktion
- Passwort-Komplexitätsregeln
- Audit-Log für Benutzeraktionen
- Multi-User-Support mit Datenbank-Backend

## 📝 Projekt-Details

**Modul**: Vertiefung Objektorientiertes Programmieren (VOOP)  
**Schule**: Berufs- und Weiterbildungszentrum Rapperswil-Jona  
**Projektdauer**: 35 Lektionen + Login-Erweiterung  
**Abgabe**: Januar 2025  
**Erweiterung**: Login-System (Januar 2025)

## 📄 Lizenz

Dieses Projekt wurde für Bildungszwecke erstellt.

## 🤝 Kontakt & Support

Bei Fragen oder Problemen:
- Überprüfen Sie die vordefinierten Login-Daten
- Stellen Sie sicher, dass alle Dependencies korrekt geladen sind
- Prüfen Sie, ob die JSON-Dateien schreibbar sind

---

**Entwickelt mit ❤️ im Rahmen des VOOP-Moduls**
**Login-System hinzugefügt für erweiterte Sicherheit und Benutzerverwaltung**
