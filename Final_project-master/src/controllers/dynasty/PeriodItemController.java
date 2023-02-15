package controllers.dynasty;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Dynasty;


import java.io.IOException;

public class PeriodItemController {
    @FXML
    private Label nameLabel;

    private Dynasty period;

    public void setData(Dynasty period) {
        this.period = period;
        nameLabel.setText(period.getName());
    }

    public Dynasty getPeriod() {
        return period;
    }

    @FXML
    public void handleItemClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/period-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        PeriodDetailController periodDetailController = fxmlLoader.getController();
        periodDetailController.setPeriod(this.getPeriod());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
