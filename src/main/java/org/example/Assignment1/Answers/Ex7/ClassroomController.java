package org.example.Assignment1.Answers.Ex7;

public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        PowerContoller projectorPower  = reg.getFirstOfType(PowerContoller.class);
        projectorPower.powerOn();
        
        InputConnecter projectorInput = reg.getFirstOfType(InputConnecter.class);
        projectorInput.connectInput("HDMI-1");

        BrightnessContorller lights = reg.getFirstOfType(BrightnessContorller.class);
        lights.setBrightness(60);

        TemperatureController ac = reg.getFirstOfType(TemperatureController.class);
        ac.setTemperatureC(24);

        ScanAttendanceController scan = reg.getFirstOfType(ScanAttendanceController.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }


    public void endClass() {
        System.out.println("Shutdown sequence:");

        for (PowerContoller device :
                reg.getAllByCapability(PowerContoller.class)) {
            device.powerOff();
        }
    }
}
