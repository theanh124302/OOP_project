package controllers.festival;

import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import models.Festival;
import utils.CreateNode;

import java.net.URL;
import java.util.ResourceBundle;

public class FestivalDetailController extends DetailController implements Initializable {

    private Festival festival;

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên lễ hội", festival.getName()));
        boxDetail.getChildren().add(CreateNode.createHBox("Ngày bắt đầu", festival.getDate()));
        boxDetail.getChildren().add(CreateNode.createHBox("Địa điểm", festival.getPlace()));
        boxDetail.getChildren().add(CreateNode.createHBox("Lần đầu tổ chức", festival.getFirstHeldYear()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("/scene/fes.fxml");
            showDetail();
        });
    }


}
