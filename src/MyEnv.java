import simbad.sim.Arch;
import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

class MyEnv extends EnvironmentDescription {
    public MyEnv() {
        light1IsOn = true;
        light2IsOn = true;
        Wall w1 = new Wall(new Vector3d(9, 0, 0), 19, 1, this);
        w1.rotate90(1);
        add(w1);
        Wall w2 = new Wall(new Vector3d(-9, 0, 0), 19, 2, this);
        w2.rotate90(1);
        add(w2);
        Wall w3 = new Wall(new Vector3d(0, 0, 9), 19, 1, this);
        add(w3);
        Wall w4 = new Wall(new Vector3d(0, 0, -9), 19, 2, this);
        add(w4);
        Box b1 = new Box(new Vector3d(-3, 0, -3), new Vector3f(1, 1, 1),
                this);
        add(b1);
        add(new Arch(new Vector3d(3, 0, -3), this));
        setWorldSize(15);
        add(new Robot(new Vector3d(0, 0, 0), "robot 1"));
    }
}