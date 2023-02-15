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

import models.King;

public class Crawl_King {
    String url = "http://www.hannom.org.vn/detail.asp?param=1020&Catid=493";
    ArrayList<King> kingsList = new ArrayList<King>();
    public Crawl_King () throws Exception {
        
        Document doc = Jsoup.connect(url).get();
        Element table = doc.select("table.MsoNormalTable").get(0); //
        Elements rows = table.select("tr");
        
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            King historical_king = new King();
            historical_king.setNienHieu(cols.get(0).text());
            historical_king.setYear(cols.get(2).text());
            historical_king.setName(cols.get(3).text());
            kingsList.add(historical_king);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String dy = gson.toJson(kingsList);
        Path path = Paths.get("Kings.json");

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.append(dy);
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public ArrayList<King> getKingsList() {
        return kingsList;
    }

}