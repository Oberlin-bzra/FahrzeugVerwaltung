package NObwz;

import java.util.Date;

public class Transporter extends Fahrzeug {
    private int maxZuladung;

    public Transporter(String marke, String modell, int hubraum,
                       String treibstoffart, int kmStand, int leistung,
                       Date erstZulassung, String aussenfarbe, int leergewicht,
                       int maxZuladung) {
        super(marke, modell, hubraum, treibstoffart, kmStand, leistung,
                erstZulassung, aussenfarbe, leergewicht);
        this.maxZuladung = maxZuladung;
    }

    public int getMaxZuladung() {
        return maxZuladung;
    }

    public void setMaxZuladung(int maxZuladung) {
        this.maxZuladung = maxZuladung;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " (Transporter)";
    }
}