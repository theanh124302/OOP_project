package controllers.ruin;
import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.Ruin;
import utils.CreateNode;
import java.net.URL;
import java.util.ResourceBundle;

public class SiteDetailController extends DetailController implements Initializable {

    private Ruin ruin;

    public void setSite(Ruin site) {
        this.ruin = site;
    }

    @Override
    public void showDetail() {
        boxDetail.getChildren().add(CreateNode.createHBox("Tên di tích", ruin.getName()));
        boxDetail.getChildren().add(CreateNode.createHBox("Địa điểm", ruin.getAddress()));
        boxDetail.getChildren().add(CreateNode.createHBox("Ngày công nhận", ruin.getRecogyear()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("/scene/site.fxml");
            showDetail();
        });
    }
}
