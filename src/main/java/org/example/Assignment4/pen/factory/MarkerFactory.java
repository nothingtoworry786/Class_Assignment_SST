package org.example.Assignment4.pen.factory;

import org.example.Assignment4.pen.drawable.BrushDrawable;
import org.example.Assignment4.pen.drawable.Drawable;
import org.example.Assignment4.pen.drawable.FeltDrawable;
import org.example.Assignment4.pen.drawable.PermanentDrawable;
import org.example.Assignment4.pen.model.Marker;
import org.example.Assignment4.pen.reload.Reload;
import org.example.Assignment4.pen.reload.ReservoirReload;
import org.example.Assignment4.pen.reload.TubeReload;
import org.example.Assignment4.pen.tip.SnapTip;
import org.example.Assignment4.pen.tip.Tip;
import org.example.Assignment4.pen.tip.TwistTip;

public class MarkerFactory {
    public Marker createMarker(String drawableType, String tipType, String reloadType, String inkColor) {
        Drawable drawable = createDrawable(drawableType);
        Tip tip = createTip(tipType);
        Reload reload = createReload(reloadType);
        return new Marker(inkColor, drawable, tip, reload);
    }

    private Drawable createDrawable(String type) {
        String t = normalize(type);
        switch (t) {
            case "brush":
                return new BrushDrawable();
            case "felt":
                return new FeltDrawable();
            case "permanent":
                return new PermanentDrawable();
            default:
                throw new IllegalArgumentException("Unknown drawableType: " + type);
        }
    }

    private Tip createTip(String type) {
        String t = normalize(type);
        switch (t) {
            case "snap":
                return new SnapTip();
            case "twist":
                return new TwistTip();
            default:
                throw new IllegalArgumentException("Unknown tipType: " + type);
        }
    }

    private Reload createReload(String type) {
        String t = normalize(type);
        switch (t) {
            case "tube":
                return new TubeReload();
            case "reservoir":
                return new ReservoirReload();
            default:
                throw new IllegalArgumentException("Unknown reloadType: " + type);
        }
    }

    private String normalize(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }
}

