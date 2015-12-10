package lib;

import javafx.util.Pair;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

/**
 * KSE was created by amir on 08.12.15.
 */
public class NewtonsPools {

    final static double EPS = 1e-2;
    final static int MAX_ITERATIONS = 300;

    static public Complex countPhi(Complex x) {
        return x.subtract(x.pow(3).subtract(1).divide(x.pow(2).multiply(3)));
    }

    static public Pair<Integer, Integer> countConvergence(Complex x0) {
        Complex curX = x0;
        int iter = 0;
        while (iter < MAX_ITERATIONS && curX.subtract(countPhi(curX)).abs() > EPS) {
            curX = countPhi(curX);
            iter++;
        }
        int point = 0;
        if (curX.getReal() < 0) {
            if (curX.getImaginary() > 0) {
                point = 1;
            } else {
                point = 2;
            }
        }
        //System.err.println(curX.getReal() + " + i" + curX.getImaginary());
        return new Pair<>(point, iter);
    }

    static public List<Complex> getWay(Complex x0) {
        Complex curX = x0;
        List<Complex> res = new ArrayList<>();
        int i = 0;
        while (i < MAX_ITERATIONS && curX.subtract(countPhi(curX)).abs() > EPS) {
            res.add(curX);
            curX = countPhi(curX);
            i++;
        }
        res.add(curX);
        curX = countPhi(curX);
        res.add(curX);
        if (i == MAX_ITERATIONS) {
            return new ArrayList<>();
        }
        return res;
    }

}
