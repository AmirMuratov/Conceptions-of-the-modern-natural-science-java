package Controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import lib.NewtonsPools;
import org.apache.commons.math3.complex.Complex;

import java.util.List;

/**
 * KSE was created by amir on 08.12.15.
 */
public class ConvergencePictureController {

    public static double IMAGE_SCALE = 0.05;

    @FXML
    private Canvas convImg;

    @FXML
    private Canvas wayImg;

    @FXML
    private void drawConvImg() {
        wayImg.getGraphicsContext2D().setFill(Color.TRANSPARENT);
        wayImg.getGraphicsContext2D().fillRect(0, 0, wayImg.getHeight(), wayImg.getWidth());
        for (int i = 0; i < convImg.getHeight(); i++) {
            for (int j = 0; j < convImg.getWidth(); j++) {
                double x = (i - convImg.getHeight() / 2) * IMAGE_SCALE;
                double y = (j - convImg.getWidth() / 2) * IMAGE_SCALE;
                Pair<Integer, Integer> res = NewtonsPools.countConvergence(new Complex(x, y));
                double darkness = Math.min(100, res.getValue() * 5);
                Color col = Color.color((200D - darkness) / 200, (100D - darkness) / 200, (100D - darkness) / 200);
                if (res.getKey() == 0) {
                    col = Color.color((100D - darkness) / 200, (200D - darkness) / 200, (100D - darkness) / 200);
                }
                if (res.getKey() == 1) {
                    col = Color.color((100D - darkness) / 200, (100D - darkness) / 200, (200D - darkness) / 200);
                }
                convImg.getGraphicsContext2D().getPixelWriter().setColor(i, j, col);
            }
        }
    }

    private double mapX(double x) {
        return x / IMAGE_SCALE + convImg.getHeight() / 2;
    }

    private double mapY(double y) {
        return y / IMAGE_SCALE + convImg.getWidth() / 2;
    }


    @FXML
    private void drawWay(MouseEvent event) {
        wayImg.getGraphicsContext2D().clearRect(0, 0, wayImg.getHeight(), wayImg.getWidth());
        double x = (event.getX() - wayImg.getHeight() / 2) * IMAGE_SCALE;
        double y = (event.getY() - wayImg.getWidth() / 2) * IMAGE_SCALE;
        List<Complex> way = NewtonsPools.getWay(new Complex(x, y));
        wayImg.getGraphicsContext2D().beginPath();
        wayImg.getGraphicsContext2D().moveTo(mapX(way.get(0).getReal()), mapY(way.get(0).getImaginary()));
        for (int i = 1; i < way.size() - 1; i++) {
            wayImg.getGraphicsContext2D().lineTo(mapX(way.get(i).getReal()), mapY(way.get(i).getImaginary()));
        }
        wayImg.getGraphicsContext2D().stroke();
    }
}
