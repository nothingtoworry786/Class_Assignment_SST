package org.example.Assignment4.pen.model;

import org.example.Assignment4.pen.drawable.Drawable;
import org.example.Assignment4.pen.reload.Reload;
import org.example.Assignment4.pen.tip.Tip;

public class Marker {
    private String inkColor;
    private boolean isUncapped;
    private Drawable drawable;
    private Tip tip;
    private Reload reloader;

    public Marker(String inkColor, Drawable drawable, Tip tip, Reload reloader) {
        this.inkColor = inkColor;
        this.drawable = drawable;
        this.tip = tip;
        this.reloader = reloader;
        this.isUncapped = false;
    }

    public void draw(String text) {
        if (!isUncapped) {
            System.out.println("Marker is capped. Uncap before drawing.");
            return;
        }
        drawable.draw(inkColor, text);
    }

    public void uncap() {
        if (isUncapped) return;
        tip.open();
        isUncapped = true;
    }

    public void cap() {
        if (!isUncapped) return;
        tip.seal();
        isUncapped = false;
    }

    public void reload(String color) {
        // Reload requires the marker to be uncapped.
        if (!isUncapped) {
            System.out.println("Marker is capped. Uncap before reloading.");
            return;
        }
        this.inkColor = color;
        reloader.reload(color);
    }
}

