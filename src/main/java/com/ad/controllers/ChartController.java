package com.ad.controllers;

import com.ad.interfaces.IControllerWithLifeCycle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import sw.IStock;

public class ChartController implements Initializable, IControllerWithLifeCycle {

    @FXML
    private BarChart<String, Double> barChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        barChart.setLegendVisible(false);

        /*
         * Example usage:
         * 
         * ArrayList<IStock> stocks = new ArrayList<>();
         * 
         * stocks.add(new Stock("AAPL", 150.0));
         * stocks.add(new Stock("GOOGL", 2800.0));
         * 
         * populateChartFromArray(stocks);
         * 
         */
    }

    // can be called to clear all data in table data source
    private void clearChart() {
        barChart.getData().remove(0, barChart.getData().size());
    }

    // can be called to populate table with data from a Stock array
    private void populateChartFromArray(List<IStock> stocks) {
        XYChart.Series<String, Double> stockDataSeries = new XYChart.Series<>();
        stockDataSeries.setName("current price");

        stocks.forEach(stock -> {
            stockDataSeries.getData().add(new XYChart.Data<>(stock.getName(), stock.getPrice()));
        });
        barChart.getData().add(stockDataSeries);
    }

    @Override
    public void willUnmount() {
        System.out.println("Chart Controller Will unmount!!");
    }
}
