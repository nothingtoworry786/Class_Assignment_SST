package org.example.Assignment1.Answers.Ex7;

public class AirConditioner implements PowerContoller,TemperatureController{
    @Override public void powerOn() { /* ok */ }
    @Override public void powerOff() { System.out.println("AC OFF"); }
    @Override public void setTemperatureC(int c) { System.out.println("AC set to " + c + "C"); }
}
