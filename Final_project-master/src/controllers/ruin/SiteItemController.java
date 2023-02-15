package controllers.ruin;

import controllers.ruin.SiteDetailController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Ruin;
import models.Ruin;

import java.io.IOException;

public class SiteItemController {
    @FXML
    private Label nameLabel;

    private Ruin site;

    public void setData(Ruin site) {
        this.site = site;
        nameLabel.setText(site.getName());
    }

    public Ruin getSite() {
        return site;
    }

    @FXML
    void handleItemClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/site-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        SiteDetailController siteDetailController = fxmlLoader.getController();
        siteDetailController.setSite(this.getSite());

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}


