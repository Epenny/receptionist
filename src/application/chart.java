package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class chart extends Application {

    @Override
    public void start(Stage stage) {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Age", 54),
                new PieChart.Data("Salary", 10),
                new PieChart.Data("Year Of Birth", 22));

        //Creating a Pie chart
        PieChart pieChart = new PieChart(pieChartData);

        //Setting the title of the Pie chart
//        pieChart.setTitle("Staff Data");

        //setting the direction to arrange the data
        pieChart.setClockwise(true);

        //Setting the length of the label line
        pieChart.setLabelLineLength(50);

        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);

        //Creating a Group object
        Group root = new Group(pieChart);

        //Creating a scene object
        Scene scene1 = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Staff Data Pie Chart");

        //Adding scene to the stage
        stage.setScene(scene1);

        //Displaying the contents of the stage
        stage.show();
        //EndofPiechart

    }

}
