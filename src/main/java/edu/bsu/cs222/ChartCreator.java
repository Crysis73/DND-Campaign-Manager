package edu.bsu.cs222;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import java.util.Map;

@SuppressWarnings("unchecked")
class ChartCreator {
    private XYChart.Series<String,Integer> series;

    ChartCreator(Map<String, Integer> data){
        generateSeries(data);
    }

    private void generateSeries(Map<String, Integer> data){
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        for (final Map.Entry<String, Integer> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        this.series = series;
    }

    private ObservableList generateChartData(XYChart.Series<String, Integer> series){
        ObservableList<XYChart.Series<String,Integer>> chartData = FXCollections.observableArrayList();
        chartData.add(series);
        return chartData;
    }

    BarChart<String, Integer> createChart(ObservableList<String> categories, String characterName){
        CategoryAxis xAxis = new CategoryAxis();
        Axis<? extends Number> yAxis = new NumberAxis();
        ((NumberAxis) yAxis).setUpperBound(20);
        yAxis.setAutoRanging(false);
        xAxis.setCategories(categories);
        BarChart<String,Integer> chart = new BarChart<>(xAxis,(Axis<Integer>)yAxis);
        chart.getData().addAll(generateChartData(series));
        chart.setTitle(characterName+"'s Traits");
        chart.setLegendVisible(false);
        chart.animatedProperty().setValue(true);
        return chart;
    }

}
