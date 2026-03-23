package org.example.Assignment4.pen.drawable;

public class BrushDrawable implements Drawable {
    @Override
    public void draw(String color, String text) {
        System.out.println("BrushDrawable [" + color + "]: " + text);
    }
}

