package com.helmholtz;

import java.io.IOException;

/**
 * This program calculates the resonance frequency of a Helmholtz-Resonator with given measurements.

 * Author: Dust DY (dust7dy) on GitHub
 * This is the Main Class,
 * all Resonator-specific values are passed here.
 * Here, all major methods calls are made.
 */
public class Main {
	public static void main(String[] args){
		com.helmholtz.Helmholtz_Resonator_Console hr = new com.helmholtz.Helmholtz_Resonator_Console();
		hr.calcFreq();
		hr.displayData();
	}
}