package lib;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * KSE was created by amir on 03.12.15.
 */
public class Counter {

    final static double EPS = 1e-2;

    static public List<Double> getLinear(double x0, double r, int k) {
        List<Double> res = new ArrayList<Double>(k);
        double curX = x0;
        for (int i = 0; i < k; i++) {
            res.add(curX);
            curX = r * curX * (1 - curX);
        }
        return res;
    }

    static public double countLin(double r, double x) {
        return r * x * (1 - x);
    }

    static public List<Double> getBifurcationLine(double r, double x) {
        List<Double> res = new ArrayList<>();
        double curX = x;
        for (int i = 0; i < 400; i++) {
            curX = countLin(r, curX);
        }
        for (int i = 0; i < 800; i++) {
            res.add(curX);
            curX = countLin(r, curX);
        }
        return res;
    }


    static public List<Pair<Double, Double>> getParabole(double from, double to, double step) {
        List<Pair<Double, Double>> res = new ArrayList<>();
        for (double x = from; x <= to; x += step) {
            res.add(new Pair<>(x, x * (x - 1)));
        }
        return res;
    }

}
