import simbad.sim.KheperaRobot;

import javax.vecmath.Vector3d;

class Robot extends KheperaRobot {
    public Robot(Vector3d position, String name) {
        super(position, name);
    }
    public void initBehavior() {
        //System.out.println("taille : "+worldSize);
    }
    public void performBehavior() {
        setWheelsVelocity(1.1, 1);
    }
}