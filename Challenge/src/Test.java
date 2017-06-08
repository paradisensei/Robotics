import java.util.Arrays;

import field.Matchbox;
import robot.Robot;
import kinematics.FK;
import kinematics.IK;
import lejos.utility.Delay;

public class Test {
	public static void main(String[] args) {
		double[][] t = FK.getTransform(new int[]{22, -72, 149});
		double[] jointAngles = IK.solve(new Matchbox(t));
		
		Robot r = new Robot();
		System.out.println(r.getColor());
		System.out.println(r.getColor());
		System.out.println(r.getColor());
		System.out.println(r.getColor());
		System.out.println(r.getColor());
		
		r.move(jointAngles[0], jointAngles[1], jointAngles[2]);
		r.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
		
		r.stop();
	}
}