package controllers.person;
import controllers.DetailController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import models.King;
import utils.CreateNode;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonDetailController extends DetailController implements Initializable {

    private King person;

    public void setPerson(King person) {
        this.person = person;
    }

    @Override
    public void showDetail() {
        if (person.getName() != null){
            boxDetail.getChildren().add(CreateNode.createHBox("Tên", person.getName()));
        }
        if (person.getNienHieu() != null){
            boxDetail.getChildren().add(CreateNode.createHBox("Niên hiệu", person.getNienHieu()));
        }

        if (person.getYear() != null) {
            boxDetail.getChildren().add(CreateNode.createHBox("Năm lên ngôi", person.getYear()));
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setBackLink("/scene/King.fxml");
            showDetail();
        });
    }


}
