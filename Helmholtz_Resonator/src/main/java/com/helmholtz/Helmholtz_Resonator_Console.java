package com.helmholtz;

import java.util.Scanner;
import java.io.*;

/**
 * Author: Dust DY (dust7dy) on GitHub
 * This is the Calculation Class, all Calculations and values for the Resonator are found here.
 * Here, all methods are defined.
 */
public class Helmholtz_Resonator_Console {
    float f0 = 0.0f;
    float V = 0.0f;
    float x = 0.0f;
    float y = 0.0f;
    float z = 0.0f;
    float d = 0.0f;
    float r = 0.0f;
    float l = 0.0f;
    float lEff = 0.0f;
    float A = 0.0f;
    //Correctional values: i=0 (0.85) -> flanged neck opening (most common to use), i=1 (0.6) -> unflanged neck opening
    float[] corr = {0.85f,0.6f};
    float c = 0.0f;
    final float c0 = 344.0f;
    int i = 0;
    int t = 0;
    int b = 0;
    File save;

    //V in l; d,l in cm
    public Helmholtz_Resonator_Console() {
        save = new File("./assets/save.txt");
        System.out.println(
                """
                        Welcome to the Program!\
                        
                        You need to insert the asked values to proceed to the calculation.\
                        
                        Use commas in all decimal numbers.\
                        
                        You can find a tutorial on my YouTube channel:\
                        
                        https://www.youtube.com/@dustDY""");

        Scanner sc = new Scanner(System.in);

        System.out.println(
                """
                        Would you like to view your last experiment's data?\
                        (yes, no)""");
        String d = sc.nextLine();
        try{
            if(d.equalsIgnoreCase("yes")){
                load();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if(this.i == 0){
            System.out.println(
                    """
                            Please insert the type of the resonator neck\
                            (0 = flanged neck (most common one to use), 1 = unflanged neck)""");
            this.i = sc.nextInt();
            sc.nextLine();
            if(this.i < corr.length) this.c = corr[this.i];
            else {
                errorMessage(3);
            }
        }
        if(this.t == 0){
            System.out.println(
                    "Please select the shape of the resonator:" +
                            "\n(0 = cubical, 1 = cylindrical, 2 = spherical)");
            this.t = sc.nextInt();
        }
        if(this.b == 0){
            System.out.println(
                    "Do you know the volume of the resonator cavity?" +
                            "\n(0 = no, 1 = yes)");
            this.b = sc.nextInt();
            sc.nextLine();

            if(b == 1){
                System.out.println("Please insert the volume of the cavity (litres): ");
                this.V = sc.nextFloat() * 0.001f;
            }else if(b == 0){
                if(t == 0){
                    System.out.println("Please insert the width of the cavity (cm): ");
                    this.x = sc.nextFloat() * 0.01f;
                    System.out.println("Please insert the height of the cavity (cm): ");
                    this.y = sc.nextFloat() * 0.01f;
                    System.out.println("Please insert the depth of the cavity (cm): ");
                    this.z = sc.nextFloat() * 0.01f;
                    this.V = this.x * this.y * this.z;
                } else if(t == 1){
                    System.out.println("Please insert the diameter of the cavity (cm): ");
                    this.r = sc.nextFloat() * 0.01f * 0.5f;
                    System.out.println("Please insert the height of the cavity (cm): ");
                    this.y = sc.nextFloat() * 0.01f;
                    this.V = this.y * (float) Math.PI * this.r * this.r;
                } else if(t == 2){
                    System.out.println("Please insert the diameter of the cavity (cm): ");
                    this.r = sc.nextFloat() * 0.01f * 0.5f;
                    this.V = (float) (4 / 3) * (float) Math.PI * this.r * this.r * this.r;
                } else{
                    errorMessage(0);
                }

            }else{
                errorMessage(0);
            }
        }
        if(this.d == 0.0f){
            System.out.println("Please insert the diameter of the neck (cm): ");
            this.d = sc.nextFloat();
            this.r = this.d*0.5f * 0.01f;
        }
        if(l == 0.0f){
            System.out.println("Please insert the length of the neck (cm): ");
            this.l = sc.nextFloat();
            this.lEff = this.l*0.01f + c*r;
            this.A = (float)Math.PI * r*r;
        }

    }

    public Helmholtz_Resonator_Console(float x, float y, float z, float d, float l, int i) {
        this.x = x * 0.01f;
        this.y = y * 0.01f;
        this.z = z * 0.01f;
        this.V = x*y*z * 0.000_001f;
        this.r = d*0.5f * 0.01f;

        if(i < corr.length) this.c = corr[i];
        else {
            errorMessage(3);
        }
        this.lEff = l*0.01f + c*r;
        this.A = (float)Math.PI * r*r;
    }

    public Helmholtz_Resonator_Console(float V, float d, float l, int i) {
        this.V = V * 0.001f;
        this.r = d*0.5f * 0.01f;

        if(i < corr.length) this.c = corr[i];
        else {
            errorMessage(3);
        }
        this.lEff = l*0.01f + c*r;
        this.A = (float)Math.PI * r*r;
    }



    //Calculating the resonance frequency
    //All measurements must be in cm
    public void calcFreq(){                //f0 = (c0/2*pi) * sqrt(A/ (V*lEff))
        f0 = (c0/ (2* (float)Math.PI)) * (float)Math.sqrt(A/ (V*lEff));
        f0 = Math.round(f0*100.0f)/100.0f;
    }



    public void displayData(){
        System.out.println(
                "----------------------------------------");
        if(x != 0.0f || y != 0.0f || z != 0.0f) {
            System.out.println("Height:\t"+Math.round(y*100.0f)/100.0f+"m" +
                    "\nWidth:\t"+x+"m" +
                    "\nDepth:\t"+z+ "m\n");
        }
        System.out.println("Diameter of the neck:\t"+d+"m" +
                "\nLength of the neck:\t"+l+ "m" +
                "\n-----------" +
                "\nVolume:\tapprox. "+Math.round(V*1000.0f)/1000.0f+"m^3" +
                "\nEffective Length:\t"+Math.round(lEff*100.0f)/100.0f+ "m" +
                "\n-----------" +
                "\nFrequency:\t"+f0+"Hz" +
                "\n----------------------------------------\n");
        Scanner s = new Scanner(System.in);
        System.out.println("Would you like to save your data?\n(yes, no)");
        String q = s.nextLine();
        while(!q.equalsIgnoreCase("yes") && !q.equalsIgnoreCase("no")){
            errorMessage(0);
            System.out.println("Would you like to save your data?\n(yes, no)");
            q = s.nextLine();
        }
            if(q.equalsIgnoreCase("yes")){
                save();
            }
    }

    public void save(){
        try {
            FileWriter fw = new FileWriter(save);
            fw.write(
                    "<s>"+
                            "<f0>"+this.f0+"</f0>"+
                            "<V>"+this.V+"</V>"+
                            "<x>"+this.x+"</x>"+
                            "<y>"+this.y+"</y>"+
                            "<z>"+this.z+"</z>"+
                            "<d>"+this.d+"</d>"+
                            "<r>"+this.r+"</r>"+
                            "<l>"+this.l+"</l>"+
                            "<lEff>"+this.lEff+"</lEff>"+
                            "<A>"+this.A+"</A>"+
                            "<i>"+this.i+"</i>"+
                            "<t>"+this.t+"</t>"+
                            "<b>"+this.b+"</b>"+
                    "</s>\n");
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void load(){
        try{
            BufferedReader fr = new BufferedReader(new FileReader(save));
            String data = fr.readLine();

            HR_Regex reg = new HR_Regex();
            this.f0 = Float.parseFloat(reg.regex("<f0>","</f0>",data));
            this.V = Float.parseFloat(reg.regex("<V>","</V>",data));
            this.x = Float.parseFloat(reg.regex("<x>","</x>",data));
            this.y = Float.parseFloat(reg.regex("<y>","</y>",data));
            this.z = Float.parseFloat(reg.regex("<z>","</z>",data));
            this.d = Float.parseFloat(reg.regex("<d>","</d>",data));
            this.r = Float.parseFloat(reg.regex("<r>","</r>",data));
            this.l = Float.parseFloat(reg.regex("<l>","</l>",data));
            this.lEff = Float.parseFloat(reg.regex("<lEff>","</lEff>",data));
            this.A = Float.parseFloat(reg.regex("<A>","</A>",data));
            this.i = (int)Float.parseFloat(reg.regex("<i>","</i>",data));
            this.t = (int)Float.parseFloat(reg.regex("<t>","</t>",data));
            this.b = (int)Float.parseFloat(reg.regex("<b>","</b>",data));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void errorMessage(int i){
        if(i == 0){
            System.out.println("Non-recognised input!\nPlease try again!");
        } if(i == 1){
            System.out.println(
                    "Type mismatch! Input must be a decimal value!" +
                    "\nPlease try again!");
        } if(i == 2){
            System.out.println(
                    "Type mismatch! Input must be a String!" +
                    "\nPlease try again!");
        } if(i == 3){
            System.out.println(
                    "Type mismatch! Input must be a Integer value!" +
                    "\nPlease try again!");
        }
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
        displayData();
    }
}