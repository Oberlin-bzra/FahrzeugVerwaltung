package NObwz;

import java.util.Date;

public abstract class Fahrzeug {
    private String marke;
    private String modell;
    private int hubraum;
    private String treibstoffart;
    private int kmStand;
    private int leistung;
    private Date erstZulassung;
    private String aussenfarbe;
    private int leergewicht;

    public Fahrzeug(String marke, String modell, int hubraum, String treibstoffart,
                    int kmStand, int leistung, Date erstZulassung,
                    String aussenfarbe, int leergewicht) {
        this.marke = marke;
        this.modell = modell;
        this.hubraum = hubraum;
        this.treibstoffart = treibstoffart;
        this.kmStand = kmStand;
        this.leistung = leistung;
        this.erstZulassung = erstZulassung;
        this.aussenfarbe = aussenfarbe;
        this.leergewicht = leergewicht;
    }

    public String getMarke() { return marke; }
    public String getModell() { return modell; }
    public int getHubraum() { return hubraum; }
    public String getTreibstoffart() { return treibstoffart; }
    public int getKmStand() { return kmStand; }
    public int getLeistung() { return leistung; }
    public Date getErstZulassung() { return erstZulassung; }
    public String getAussenfarbe() { return aussenfarbe; }
    public int getLeergewicht() { return leergewicht; }

    public void setMarke(String marke) { this.marke = marke; }
    public void setModell(String modell) { this.modell = modell; }
    public void setHubraum(int hubraum) { this.hubraum = hubraum; }
    public void setTreibstoffart(String treibstoffart) { this.treibstoffart = treibstoffart; }
    public void setKmStand(int kmStand) { this.kmStand = kmStand; }
    public void setLeistung(int leistung) { this.leistung = leistung; }
    public void setErstZulassung(Date erstZulassung) { this.erstZulassung = erstZulassung; }
    public void setAussenfarbe(String aussenfarbe) { this.aussenfarbe = aussenfarbe; }
    public void setLeergewicht(int leergewicht) { this.leergewicht = leergewicht; }

    public String getDetails() {
        return marke + " " + modell;
    }
}