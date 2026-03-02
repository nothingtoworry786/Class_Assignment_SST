package org.example.Assignment1.Answers.Ex7;

public class LightsPanel implements PowerContoller,BrightnessContorller {
    @Override public void powerOn() { /* always on */ }
    @Override public void powerOff() { System.out.println("Lights OFF"); }

    @Override public void setBrightness(int pct) { System.out.println("Lights set to " + pct + "%"); }

}
