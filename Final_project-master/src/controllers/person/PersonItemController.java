package controllers.person;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.King;


import java.io.IOException;

public class PersonItemController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    private King person;

    public void setData(King person) {
        this.person = person;
        nameLabel.setText(person.getName());
    }

    public King getPerson() {
        return person;
    }

    @FXML
    public void handleItemClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/King-detail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);

        PersonDetailController personDetailController = fxmlLoader.getController();
        personDetailController.setPerson(this.getPerson());

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
