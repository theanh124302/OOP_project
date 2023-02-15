package crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Dynasty;

public class Crawl_Dynasties {    

    ArrayList<Dynasty> dynastiesList = new ArrayList<Dynasty>();
    String url = "https://nguoikesu.com/tu-lieu/bang-doi-chieu-cac-trieu-dai-viet-nam-va-cac-trieu-dai-trung-quoc";
    public Crawl_Dynasties() throws Exception {

        Document doc = Jsoup.connect(url).get();
        Elements table = doc.select("table.table.table-bordered");
        Elements rows = table.select("tr");
        
        for (int i = 1; i < rows.size(); i++) {

            Element row = rows.get(i);
            Elements cols = row.select("td");
            int check = cols.size();
            if (check > 3) {
                Dynasty historical_dynasty = new Dynasty();
                historical_dynasty.setName(cols.get(0).text());
                historical_dynasty.setLabel(cols.get(1).text());
                historical_dynasty.setLunaryear(cols.get(2).text());
                historical_dynasty.setYear(cols.get(3).text());
                dynastiesList.add(historical_dynasty);
            }

        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String dy = gson.toJson(dynastiesList);    

        Path path = Paths.get("Dynasties.json");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                writer.append(dy);
                writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }   
    public ArrayList<Dynasty> getDynastiesList() {
        return dynastiesList;
    }
}
