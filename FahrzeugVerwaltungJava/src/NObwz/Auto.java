package NObwz;

import java.util.Date;

public class Auto extends Fahrzeug {
    private String aufbau;
    private boolean naviSystem;

    public Auto(String marke, String modell, int hubraum, String treibstoffart,
                int kmStand, int leistung, Date erstZulassung,
                String aussenfarbe, int leergewicht,
                String aufbau, boolean naviSystem) {
        super(marke, modell, hubraum, treibstoffart, kmStand, leistung,
                erstZulassung, aussenfarbe, leergewicht);
        this.aufbau = aufbau;
        this.naviSystem = naviSystem;
    }

    public String getAufbau() {
        return aufbau;
    }

    public void setAufbau(String aufbau) {
        this.aufbau = aufbau;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " (Auto - " + aufbau + ")";
    }
}