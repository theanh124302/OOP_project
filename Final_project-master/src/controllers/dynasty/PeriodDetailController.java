package controllers.dynasty;

import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.Dynasty;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class PeriodDetailController extends DetailController implements Initializable {

    private Dynasty period;

    public void setPeriod(Dynasty period) {
        this.period = period;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên triều đại", period.getName()));
        boxDetail.getChildren().add(CreateNode.createHBox("Ngày bắt đầu", period.getYear()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("/scene/period.fxml");
            showDetail();
        });
    }


}
