package controllers.ruin;

import com.google.gson.Gson;
import controllers.LayoutController;
import controllers.ListPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Ruin;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public  class SiteController extends LayoutController implements Initializable, ListPage<Ruin> {

    private List<Ruin> sites = new ArrayList<>();

    public void setRuin(List<Ruin> sites) {
        this.sites = sites;
    }

    @Override
    // This method is used to get data from json file.
    public void getData() {
        Gson gson = new Gson();
        Ruin[] list;
        try {
            FileReader reader = new FileReader("Ruins.json");
            list = gson.fromJson(reader, Ruin[].class);
            List<Ruin> siteList = Arrays.asList(list);
            setRuin(siteList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // A method to show list of sites.
    public void showList(List<Ruin> siteList) {
        int col = 0;
        int row = 1;
        try {
            for (Ruin site : siteList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/scene/site-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                SiteItemController siteItemController = fxmlLoader.getController();
                siteItemController.setData(site);

                if (col == 1) {
                    col = 0;
                    row++;
                }
                gridPane.add(anchorPane, col++, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClickSearchButton(MouseEvent event) {
        String textInput = searchInput.getText().toLowerCase();
        List<Ruin> searchList = new ArrayList<>();
        for (Ruin site : sites) {
            if (site.getName().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(site);
            }
        }
        gridPane.getChildren().clear();
        if (searchList.size() == 0) {
            Label noFound = new Label();
            noFound.setMinWidth(500);
            noFound.setAlignment(Pos.CENTER);
            noFound.setText("Không có kết quả tìm thấy");
            noFound.setStyle("-fx-text-fill: #823a17; -fx-font-size: 16;");
            gridPane.add(noFound,2,1);
        } else {
            showList(searchList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        showList(this.sites);
    }
}
