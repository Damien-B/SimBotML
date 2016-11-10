import simbad.gui.Simbad;
import simbad.sim.*;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {

    private static float initialValue;
    private static float initialPas = 0.1f;
    private static float pasMinimum = 0.0001f;
    private static int lowInterval = -5;
    private static int highInterval = 5;

    public static void main(String[] args) {
        System.setProperty("j3d.implicitAntialiasing", "true");
        Simbad frame = new Simbad(new MyEnv(), false);

        initialValue = (float) (Math.random()*10-5);

        System.out.println(initialValue);

        DecimalFormat df = new DecimalFormat("#.#######");
        df.setRoundingMode(RoundingMode.CEILING);
        System.out.println("best value found for first function = "+df.format(maximise(initialValue, 1)));
        System.out.println("best value found for second function = "+df.format(maximise(initialValue, 2)));
        System.out.println("best value found for third function = "+df.format(maximise(initialValue, 3)));

    }

    public static float maximise(float x, int funcNumber) {
        float tmpX = x;
        float tmpPas = initialPas;
            while (tmpPas >= pasMinimum) {
                //System.out.println(".pas = "+tmpPas);
                if (getBestVoisinage(tmpX, tmpPas, funcNumber) > tmpX) {
                    while (getBestVoisinage(tmpX, tmpPas, funcNumber) > tmpX) {
                        //System.out.println(".x = " + tmpX);
                        tmpX = getBestVoisinage(tmpX, tmpPas, funcNumber);
                    }
                } else if (getBestVoisinage(tmpX, tmpPas, funcNumber) < tmpX) {
                    while (getBestVoisinage(tmpX, tmpPas, funcNumber) < tmpX) {
                        //System.out.println("..x = " + tmpX);
                        tmpX = getBestVoisinage(tmpX, tmpPas, funcNumber);
                    }
                } else {
                    return tmpX;
                }
                tmpPas = tmpPas/10.0f;
            }
        return tmpX;
    }

    public static float getBestVoisinage(float x, float pas, int funcNumber) {
        if (x - pas < lowInterval) {
            return x + pas;
        } else if (x + pas > highInterval) {
            return x - pas;
        }
        if (funcNumber == 1) {
            if (firstFunction(x - pas) >= firstFunction(x + pas)) {
                return x - pas;
            }
            return x + pas;
        } else if (funcNumber == 2) {
            if (secondFunction(x - pas) >= secondFunction(x + pas)) {
                return x - pas;
            }
            return x + pas;
        } else if (funcNumber == 3) {
            if (thirdFunction(x - pas) >= thirdFunction(x + pas)) {
                return x - pas;
            }
            return x + pas;
        }
        return x;
    }

    public static float firstFunction(float x) {
        return (float) -Math.sqrt(x);
    }

    public static float secondFunction(float x) {
        return (float) (x+Math.sin(x)-Math.sqrt(x));
    }

    public static float thirdFunction(float x) {
        return (float) ((float) -Math.pow(x, 4)+Math.pow(x, 3)+x*x-x);
    }
}