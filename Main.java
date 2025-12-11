package Calculation;

/**
 * This code calculates the resonance frequency of a Helmholtz-Resonator with given measurements.
 *
 * Author: Dust DY (dust7dy) on GitHub
 * This is the Main Class,
 * all Resonator-specific values are passed here.
 * Here, all methods are called.
 */
public class Main {
	public static void main(String[] args) {
		//calcLength(19.2f,47.3f,39.0f,20,49.2);
		Calculation.Helmholtz_Resonator hr =
				new Calculation.Helmholtz_Resonator(
						0.745f,
						1.9f,
						5.4f,
						0);
		hr.calcFreq();
	}
}