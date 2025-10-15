package com.ad.controllers;

import com.ad.communication.NetworkRetriever;
import com.ad.interfaces.IControllerWithLifeCycle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable, IControllerWithLifeCycle {
    private static final String FXML_LOCATION = "/com/ad/stocks/";
    private static final String CHART_VIEW = "chart-view.fxml";
    private static final String LIST_VIEW = "table-view.fxml";

    @FXML
    private AnchorPane centerStage;
    private FXMLLoader centerStageLoader;
    @FXML
    private void showChart(ActionEvent event) {
        populateCenterStage(CHART_VIEW);
    }
    @FXML
    private void showList(ActionEvent event) {
        populateCenterStage(LIST_VIEW);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //calling the NetworkRetriever will make it so we start li
        NetworkRetriever.getNetworkRetriever();

        populateCenterStage(LIST_VIEW);
    }


    private void populateCenterStage(String view) {

        //clear center stage
        clearCenterStage();

        try {
            //populate new FXML, controller is loaded from the FXML file in this application.
            centerStageLoader = new FXMLLoader(getClass().getResource(FXML_LOCATION+view));
            centerStage.getChildren().add(centerStageLoader.load());

            //This gives you access to the controller after it has been loaded (after initialize method have been called)
            //Can be used to do additional setup or "late injections" ...
            //Use it if you need or feel like it!
            IControllerWithLifeCycle activeViewController = centerStageLoader.getController();

        } catch (Exception ex) {
            System.out.println("ERROROROROR changing center stage in the application ---- :");
            System.out.println(ex.getMessage());
        }
    }

    private void clearCenterStage() {
        if (!centerStage.getChildren().isEmpty()) {
            //get active controller class
            IControllerWithLifeCycle activeChild = centerStageLoader.getController();
            //run willUnmount on it.
            activeChild.willUnmount();
            //remove it.
            centerStage.getChildren().removeFirst();
        }
    }

    @Override
    public void willUnmount() {
        System.out.println("\"Main View Will unmount");
    }
}
