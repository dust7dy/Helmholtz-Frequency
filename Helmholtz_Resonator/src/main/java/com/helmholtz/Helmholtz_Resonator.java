package com.helmholtz;

/**
 * Author: Dust DY (dust7dy) on GitHub
 * This is the Calculation Class, all Calculations and values for the Resonator are found here.
 * Here, all methods are defined.
 */
public class Helmholtz_Resonator {
    float f0;
    float V;
    float x;
    float y;
    float z;
    float r;
    float lEff;
    float A;
    //Correctional values: i=0 (0.85f) -> flanged neck opening (most common to use), i=1 (0.6f) -> unflanged neck opening
    float[] corr = {0.85f,0.6f};
    float c = 0.0f;
    final float c0 = 344.0f;

    //V in l; d,l in cm
    public Helmholtz_Resonator(float V, float d, float l, int i) {
        this.V = V * 0.001f;
        this.r = d*0.5f * 0.01f;

        if(i < corr.length) this.c = corr[i];
        else {
            System.out.println(
                    "Faulty input of resonator-index!" +
                    "\nPlease input a valid number and try again.");

        }
        this.lEff = l*0.01f + c*r;
        this.A = (float)Math.PI * r*r;
    }

    public Helmholtz_Resonator(float x, float y, float z, float d, float l, int i) {
        this.x = x * 0.01f;
        this.y = y * 0.01f;
        this.z = z * 0.01f;
        this.V = x*y*z * 0.000_001f;
        this.r = d*0.5f * 0.01f;

        if(i < corr.length) this.c = corr[i];
        else {
            System.out.println(
                    "Faulty input of resonator-index!" +
                    "\nPlease input a valid number and try again.");

        }
        this.lEff = l*0.01f + c*r;
        this.A = (float)Math.PI * r*r;
    }

    //Calculating the resonance frequency
    //All measurements must be in cm
    public float calcFreq(){                //f0 = (c0/2*pi) * sqrt(A/ (V*lEff))
        f0 = (c0/ (2* (float)Math.PI)) * (float)Math.sqrt(A/ (V*lEff));
        f0 = Math.round(f0*100.0f)/100.0f;
        System.out.println(
                "----------------------------------------");
        if(x != 0.0f || y != 0.0f || z != 0.0f) {
            System.out.println("Height:\t"+y+"m" +
                "\nWidth:\t"+x+"m" +
                "\nDepth:\t"+z+ "m\n");
        }
        System.out.println("Diameter of the tube:\t"+2*r+"m" +
                "\nLength of the tube:\t"+Math.round((lEff-(r*c))*100.0f)/100.0f+ "m" +
                "\n-----------" +
                "\nVolume:\tapprox. "+Math.round(V*1000.0f)/1000.0f+"m^3" +
                "\nEffective Length:\t"+Math.round(lEff*100.0f)/100.0f+ "m" +
                "\n-----------" +
                "\nFrequency:\t"+f0+"Hz" +
                "\n----------------------------------------\n");
        return f0;
    }

    //Calculate the required neck length of a resonator of known measurements and frequency
    //Frequency must be entered as a correct float or double value
    //Same as instructions of "calcFreq()"
    public void calcLength(float x,float y,float z,float d, double f0) {
        x *= 0.01f;									//dx, m
        y *= 0.01f;									//dy, m
        z *= 0.01f;									//dz, m
        d *= 0.01f;									//Radius, m
        double a = Math.PI*(d/2)*(d/2);				//Area, m^2
        double v = x*y*z;							//Volume, m^3
        f0 = (double)Math.round(f0*10)/10;
        double l = (c0*c0*a)/(4*Math.PI*Math.PI*v*f0*f0)-c*d;
        l = (double)Math.round(l*10)/10;
        System.out.println(
                "----------------------------------------" +
                        "\nHeight: "+y+"m" +
                        "\nWith: "+x+"m" +
                        "\nDepth: "+z+ "m" +
                        "\nDiameter of the tube: "+d+"m" +
                        "\n-----------" +
                        "\nVolume: "+v+"m^3" +
                        "\nFrequency: "+f0+"Hz" +
                        "\n-----------" +
                        "\nLength: "+l+"m" +
                        "\n----------------------------------------\n");
    }
}