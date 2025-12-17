package NObwz;

import java.util.Date;

public class Kunde {
    private String name;
    private String vorname;
    private String strasseNummer;
    private String plz;
    private String wohnort;
    private String telefonPrivat;
    private String telefonMobile;
    private String email;
    private Date geburtsdatum;

    public Kunde(String name, String vorname, String strasseNummer, String plz,
                 String wohnort, String telefonPrivat, String telefonMobile,
                 String email, Date geburtsdatum) {
        this.name = name;
        this.vorname = vorname;
        this.strasseNummer = strasseNummer;
        this.plz = plz;
        this.wohnort = wohnort;
        this.telefonPrivat = telefonPrivat;
        this.telefonMobile = telefonMobile;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
    }

    // Getter
    public String getName() { return name; }
    public String getVorname() { return vorname; }
    public String getStrasseNummer() { return strasseNummer; }
    public String getPlz() { return plz; }
    public String getWohnort() { return wohnort; }
    public String getTelefonPrivat() { return telefonPrivat; }
    public String getTelefonMobile() { return telefonMobile; }
    public String getEmail() { return email; }
    public Date getGeburtsdatum() { return geburtsdatum; }

    // Setter
    public void setName(String name) { this.name = name; }
    public void setVorname(String vorname) { this.vorname = vorname; }
    public void setStrasseNummer(String strasseNummer) { this.strasseNummer = strasseNummer; }
    public void setPlz(String plz) { this.plz = plz; }
    public void setWohnort(String wohnort) { this.wohnort = wohnort; }
    public void setTelefonPrivat(String telefonPrivat) { this.telefonPrivat = telefonPrivat; }
    public void setTelefonMobile(String telefonMobile) { this.telefonMobile = telefonMobile; }
    public void setEmail(String email) { this.email = email; }
    public void setGeburtsdatum(Date geburtsdatum) { this.geburtsdatum = geburtsdatum; }

    @Override
    public String toString() {
        return vorname + " " + name + " (" + wohnort + ")";
    }
}