package controllers.dynasty;

import com.google.gson.Gson;
import controllers.LayoutController;
import controllers.ListPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Dynasty;
import utils.CreateNode;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PeriodController extends LayoutController implements Initializable, ListPage<Dynasty> {

    private List<Dynasty> periods = new ArrayList<>();

    public void setPeriods(List<Dynasty> periods) {
        this.periods = periods;
    }

    @Override
    public void getData() {
        Gson gson = new Gson();
        Dynasty[] list;
        try {
            FileReader reader = new FileReader("Dynasties.json");
            list = gson.fromJson(reader, Dynasty[].class);
            List<Dynasty> periodList = Arrays.asList(list);
            setPeriods(periodList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showList(List<Dynasty> periodList) {
        int col = 0;
        int row = 1;
        try {
            for (Dynasty period : periodList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/scene/period-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PeriodItemController periodItemController = fxmlLoader.getController();
                periodItemController.setData(period);

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
    public void handleClickSearchButton(MouseEvent e) {
        String textInput = searchInput.getText().toLowerCase();
        List<Dynasty> searchList = new ArrayList<>();
        for (Dynasty period : periods) {
            if (period.getName().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(period);
            }
        }
        gridPane.getChildren().clear();
        if (searchList.size() == 0) {
            Label noFound = CreateNode.createNoFound();
            gridPane.add(noFound, 2, 1);
        } else {
            showList(searchList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        showList(this.periods);
    }
}
