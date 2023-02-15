package controllers.event;

import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.Event;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailController extends DetailController implements Initializable {

    private Event event;

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên sự kiện", event.getDetails()));
        boxDetail.getChildren().add(CreateNode.createHBox("Thời gian", event.getTime()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("/scene/event.fxml");
            showDetail();
        });
    }
}
