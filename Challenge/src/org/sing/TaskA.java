package org.sing;

import java.util.Arrays;

import org.sing.MatchboxColor;

import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Assuming robot stands on the narrow side of the field.
 */
public class TaskA {
	public static void main(String[] args) {
		int size = 3;
		
		int[][][] field = Field.getBySize(size);
		
		MatchboxColor[][] ans = new MatchboxColor[size][size];
		
		Robot robot = new Robot();
		
		for (int i = 0; i < size; i++) {
			// relocation condition
			if ((size <= 4 && i == 2) || (size == 5 && i == 3)) {
				Delay.msDelay(15000);
			}
			
			for (int j = 0; j < size; j++) {
				double[][] t = FK.getTransform(field[i][j]);
				
				double[] jointAngles = IK.solve(new Matchbox(t));
				System.out.println(Arrays.toString(jointAngles));
				
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				ans[size - 1 - i][j] = robot.getColor();
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
		
		for (MatchboxColor[] c : ans) {
			System.out.println(Arrays.toString(c));
		}
		
		// finish execution
		double[][] t = FK.getTransform(field[size - 1][size - 1]);
		double[] jointAngles = IK.solve(new Matchbox(t));
		robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
		Sound.beep();
		Delay.msDelay(10000);
		robot.stop();
	}
}
