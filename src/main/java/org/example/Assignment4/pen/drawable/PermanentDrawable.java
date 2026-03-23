package org.example.Assignment4.pen.drawable;

public class PermanentDrawable implements Drawable {
    @Override
    public void draw(String color, String text) {
        System.out.println("PermanentDrawable [" + color + "]: " + text);
    }
}

