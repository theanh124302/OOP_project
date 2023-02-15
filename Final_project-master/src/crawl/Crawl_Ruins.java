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

import models.Ruin;

public class Crawl_Ruins{ 
    ArrayList<Ruin> Ruinslist =new ArrayList<>();
    String url= "https://vi.wikipedia.org/wiki/Danh_sách_Di_tích_quốc_gia_Việt_Nam";
    public Crawl_Ruins()  throws IOException {
       
        Document doc = Jsoup.connect(url).get();
        Elements temp = doc.select("#mw-content-text > div.mw-parser-output > table");

        for(int i = 1; i < temp.select("tr").size(); ++i) {
            Element element = (Element)temp.select("tr").get(i);
            if (element.children().select("td").size() == 5) {
                Ruin tempRuin = new Ruin();
                tempRuin.setName(((Element)element.select("td").get(0)).text());
                tempRuin.setAddress(((Element)element.select("td").get(1)).text());
                tempRuin.setType(((Element)element.select("td").get(2)).text());
                tempRuin.setRecogyear(((Element)element.select("td").get(3)).text());
                Ruinslist.add(tempRuin);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String dy = gson.toJson(Ruinslist);
        Path path = Paths.get("Ruins.json");

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                writer.append(dy);
                writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
    }
    public ArrayList<Ruin> getRuinsList() {
        return Ruinslist;
    }
}