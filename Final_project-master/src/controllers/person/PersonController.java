package controllers.person;

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
import models.King;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PersonController extends LayoutController implements Initializable, ListPage<King> {

    private List<King> persons = new ArrayList<>();

    public void setPersons(List<King> persons) {
        this.persons = persons;
    }

    @Override
    // This method is used to get data from json file and store it in a list.
    public void getData() {
        Gson gson = new Gson();
        King[] list;
        try {
            FileReader reader = new FileReader("Kings.json");
            list = gson.fromJson(reader, King[].class);
            List<King> personList = Arrays.asList(list);
            setPersons(personList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    // A method to show list of person
    public void showList(List<King> personList) {
        int col = 0;
        int row = 1;
        try {
            for (King person : personList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/scene/King-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PersonItemController personItemController = fxmlLoader.getController();
                personItemController.setData(person);

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
        List<King> searchList = new ArrayList<>();
        for (King person : persons) {
            if (person.getName().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(person);
            }
        }
        gridPane.getChildren().clear();
        if (searchList.size() == 0) {
            Label noFound = new Label();
            noFound.setMinWidth(1000);
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
        showList(this.persons);
    }
}
