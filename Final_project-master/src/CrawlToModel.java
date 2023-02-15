import crawl.*;
import models.*;
import java.io.IOException;

import java.util.ArrayList;

public class CrawlToModel {
    public static void main(String[] args) throws Exception {
        Crawl_Event Events = new Crawl_Event();
        Crawl_King Kings = new Crawl_King();
        Crawl_Dynasties Dynasties = new Crawl_Dynasties();
        Crawl_Festival Festival = new Crawl_Festival();
        Crawl_Ruins Ruins = new Crawl_Ruins();

        //create lists
        ArrayList<King> kingsList = Kings.getKingsList();
        ArrayList<Event> eventsList = Events.getEventsList();
        ArrayList<Dynasty> dynastiesList = Dynasties.getDynastiesList();
        ArrayList<Festival> festivalsList = Festival.getFestivalsList();
        ArrayList<Ruin> ruinsList = Ruins.getRuinsList();
    }
    
}
