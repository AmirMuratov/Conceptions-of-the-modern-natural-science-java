package Controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import lib.Counter;

import java.util.List;

/**
 * KSE was created by amir on 08.12.15.
 */
public class BifurcationDiagramController {
    @FXML
    private Canvas bifImg;

    @FXML
    private TextField bifx0;

    @FXML
    private void drawBifurcationDiagram() {
        double from = 0;
        double to = 4;
        double x0 = 0.9;
        try {
            x0 = Double.parseDouble(bifx0.getText());
        } catch (NumberFormatException ignored) {
        }
        for (int i = 0; i < bifImg.getHeight(); i++) {
            for (int j = 0; j < bifImg.getWidth(); j++) {
                bifImg.getGraphicsContext2D().getPixelWriter().setColor(i, j, Color.TRANSPARENT);
            }
        }

        for (int i = 0; i < bifImg.getWidth(); i++) {
            //double r = i / bifImg.getWidth() * 8 - 4;
            double r = i / bifImg.getWidth() * (to - from) + from;
            List<Double> points = Counter.getBifurcationLine(r, x0);
            double[] line = new double[(int) bifImg.getHeight()];
            for (double p : points) {
                int pix = (int) (bifImg.getHeight() - 1 - Math.round(p * (bifImg.getHeight() - 1)));
                line[pix] += 1;
            }
            int smooth = 1;
            for (int j = 0; j < bifImg.getHeight(); j++) {
                double c = 0;
                for (int l = Math.max(0, j - smooth); l <= Math.min(bifImg.getHeight() - 1, j + smooth); l++) {
                    c += line[l] / ((Math.abs(l - j) + 1) * (Math.abs(l - j) + 1) * (Math.abs(l - j) + 1));
                }

                c = 1 / Math.max(c, 1);
                bifImg.getGraphicsContext2D().getPixelWriter().
                        setColor(i, j, Color.color(c, c, c, 1 - c));
            }

        }
    }
}
