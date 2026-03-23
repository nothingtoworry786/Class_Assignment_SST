package org.example.Assignment4.pen;

import org.example.Assignment4.pen.factory.MarkerFactory;
import org.example.Assignment4.pen.model.Marker;

public class App {
    public static void main(String[] args) {
        // args: drawableType tipType reloadType inkColor text
        String drawableType = args.length > 0 ? args[0] : "brush";
        String tipType = args.length > 1 ? args[1] : "snap";
        String reloadType = args.length > 2 ? args[2] : "tube";
        String inkColor = args.length > 3 ? args[3] : "black";
        String text = args.length > 4 ? args[4] : "Hello!";

        MarkerFactory factory = new MarkerFactory();
        Marker marker = factory.createMarker(drawableType, tipType, reloadType, inkColor);

        marker.uncap();
        marker.draw(text);
        marker.reload(inkColor + "_new");
        marker.draw(text);
        marker.cap();
    }
}
