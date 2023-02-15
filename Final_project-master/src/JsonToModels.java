
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonToModels {


    public static void main(String[] args) throws Exception {
        //read Dynasties.json
        String DynastiesJson = new String(Files.readAllBytes(Paths.get("Dynasties.json")), StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //convert to Dynasties model
        ArrayList <Dynasty> Dynasties = new ArrayList<Dynasty>();
        Dynasty[] DynastiesArray = gson.fromJson(DynastiesJson, Dynasty[].class);
        for (Dynasty dynasty : DynastiesArray) {
            Dynasties.add(dynasty);
        }
        //read Events.json
        String EventsJson = new String(Files.readAllBytes(Paths.get("Events.json")), StandardCharsets.UTF_8);
        //convert to Events model
        ArrayList <Event> Events = new ArrayList<Event>();
        Event[] EventsArray = gson.fromJson(EventsJson, Event[].class);
        for (Event event : EventsArray) {
            Events.add(event);
        }
        //read Kings.json
        String KingsJson = new String(Files.readAllBytes(Paths.get("Kings.json")), StandardCharsets.UTF_8);
        //convert to Kings model
        ArrayList <King> Kings = new ArrayList<King>();
        King[] KingsArray = gson.fromJson(KingsJson, King[].class);
        for (King king : KingsArray) {
            Kings.add(king);
        }
        //read Festivals.json
        String FestivalsJson = new String(Files.readAllBytes(Paths.get("Festivals.json")), StandardCharsets.UTF_8);
        //convert to Festivals model
        ArrayList <Festival> Festivals = new ArrayList<Festival>();
        Festival[] FestivalsArray = gson.fromJson(FestivalsJson, Festival[].class);
        for (Festival festival : FestivalsArray) {
            Festivals.add(festival);
        }
        //read Ruins.json
        String RuinsJson = new String(Files.readAllBytes(Paths.get("Ruins.json")), StandardCharsets.UTF_8);
        //convert to Ruins model
        ArrayList <Ruin> Ruins = new ArrayList<Ruin>();
        Ruin[] RuinsArray = gson.fromJson(RuinsJson, Ruin[].class);
        for (Ruin ruin : RuinsArray) {
            Ruins.add(ruin);
        }
        // //print first element of each list
        // System.out.println(Kings.get(0).getName());
        // System.out.println(Events.get(0).getDetails());
        // System.out.println(Dynasties.get(0).getName());
        // System.out.println(Festivals.get(0).getName());
        // System.out.println(Ruins.get(0).getName());
        



    }
}
