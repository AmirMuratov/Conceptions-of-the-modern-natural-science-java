package Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import lib.Counter;

import java.util.List;

/**
 * KSE was created by amir on 08.12.15.
 */
public class FirstDiagramController {
    @FXML
    private TextField x01;
    @FXML
    private TextField r1;
    @FXML
    private TextField iter1;
    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private void plotFirstDiagram() {
        lineChart.getData().clear();
        try {
            double x0 = Double.parseDouble(x01.getText());
            double r = Double.parseDouble(r1.getText());
            int iter = Integer.parseInt(iter1.getText());

            List<Double> data = Counter.getLinear(x0, r, iter);
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            for (int i = 0; i < data.size(); i++) {
                if (!Double.isFinite(data.get(i))) {
                    throw new NumberFormatException();
                }
                series.getData().add(new XYChart.Data(i, data.get(i)));
            }
            lineChart.getData().add(series);
        } catch (NumberFormatException ignored) {
        }
    }
}
