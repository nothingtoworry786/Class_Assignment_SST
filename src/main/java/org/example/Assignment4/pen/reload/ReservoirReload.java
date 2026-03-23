package org.example.Assignment4.pen.reload;

public class ReservoirReload implements Reload {
    @Override
    public void reload(String color) {
        System.out.println("ReservoirReload: reloading ink to [" + color + "]");
    }
}

