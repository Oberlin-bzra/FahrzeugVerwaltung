package NObwz;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class JsonService {
    private static final String FAHRZEUGE_FILE = "fahrzeuge.json";
    private static final String KUNDEN_FILE = "kunden.json";
    private Gson gson;

    public JsonService() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("dd.MM.yyyy")
                .create();
    }

    public List<Fahrzeug> ladeFahrzeuge() {
        File file = new File(FAHRZEUGE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            List<Fahrzeug> fahrzeuge = new ArrayList<>();

            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
                String type = obj.get("type").getAsString();

                if ("Auto".equals(type)) {
                    fahrzeuge.add(gson.fromJson(obj, Auto.class));
                } else if ("Transporter".equals(type)) {
                    fahrzeuge.add(gson.fromJson(obj, Transporter.class));
                }
            }

            return fahrzeuge;
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der Fahrzeuge: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void speichereFahrzeuge(List<Fahrzeug> fahrzeuge) {
        try (Writer writer = new FileWriter(FAHRZEUGE_FILE)) {
            JsonArray jsonArray = new JsonArray();

            for (Fahrzeug f : fahrzeuge) {
                JsonObject obj = gson.toJsonTree(f).getAsJsonObject();
                if (f instanceof Auto) {
                    obj.addProperty("type", "Auto");
                } else if (f instanceof Transporter) {
                    obj.addProperty("type", "Transporter");
                }
                jsonArray.add(obj);
            }

            gson.toJson(jsonArray, writer);
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Fahrzeuge: " + e.getMessage());
        }
    }

    public List<Kunde> ladeKunden() {
        File file = new File(KUNDEN_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Kunde[] kundenArray = gson.fromJson(reader, Kunde[].class);
            return kundenArray != null ? Arrays.asList(kundenArray) : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der Kunden: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void speichereKunden(List<Kunde> kunden) {
        try (Writer writer = new FileWriter(KUNDEN_FILE)) {
            gson.toJson(kunden, writer);
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Kunden: " + e.getMessage());
        }
    }
}