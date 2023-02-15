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

import models.Festival;

public class Crawl_Festival {
        ArrayList<Festival> festivalList =new ArrayList<>();
        String url= "https://vi.wikipedia.org/wiki/Lễ_hội_Việt_Nam";
    public Crawl_Festival()  throws IOException {
       
        Document doc = Jsoup.connect(url).get();
        Elements table = doc.select("table.prettytable.wikitable");
        Elements rows = table.select("tr");
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            Festival festival = new Festival();
            festival.setDate(cols.get(0).text());
            festival.setPlace(cols.get(1).text());
            festival.setName(cols.get(2).text());
            festival.setFirstHeldYear(cols.get(3).text());
            festival.setRelFigure(cols.get(4).text());
            festivalList.add(festival);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String dy = gson.toJson(festivalList);
        Path path = Paths.get("Festivals.json");

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                writer.append(dy);
                writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public ArrayList<Festival> getFestivalsList() {
        return festivalList;
    }
}
