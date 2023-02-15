package controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;



public abstract class DetailController extends LayoutController{

    @FXML
    protected HBox backButton;

    @FXML
    protected VBox boxDetail;
    @FXML
    protected String backLink;
    @FXML
    public void setBackLink(String backLink) {
        this.backLink = backLink;
    }


    // A method that is called when the back button is clicked. It loads the previous scene.
    public void handleBack(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(backLink));
        Scene scene = new Scene(fxmlLoader.load(), 1028, 768);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public abstract void showDetail();




}
