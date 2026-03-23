package org.example.Assignment4.pen.reload;

public class TubeReload implements Reload {
    @Override
    public void reload(String color) {
        System.out.println("TubeReload: reloading ink to [" + color + "]");
    }
}

