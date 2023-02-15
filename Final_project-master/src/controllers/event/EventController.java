package controllers.event;

import com.google.gson.Gson;
import controllers.LayoutController;
import controllers.ListPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Event;
import utils.CreateNode;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EventController extends LayoutController implements Initializable, ListPage<Event> {

    private List<Event> events = new ArrayList<>();

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public void getData() {
        Gson gson = new Gson();
        Event[] list;
        try {
            FileReader reader;
            reader = new FileReader("Events.json");
            list = gson.fromJson(reader, Event[].class);
            List<Event> eventList = Arrays.asList(list);
            setEvents(eventList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showList(List<Event> eventList) {
        int col = 0;
        int row = 1;
        try {
            for (Event event: eventList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/scene/event-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventItemController eventItemController = fxmlLoader.getController();
                eventItemController.setData(event);

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
        List<Event> searchList = new ArrayList<>();
        for (Event event : events) {
            if (event.getDetails().toLowerCase().contains(textInput.toLowerCase())) {
                searchList.add(event);
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
        showList(this.events);
    }
}
