package org.example.Assignment4.pen.drawable;

public class FeltDrawable implements Drawable {
    @Override
    public void draw(String color, String text) {
        System.out.println("FeltDrawable [" + color + "]: " + text);
    }
}

