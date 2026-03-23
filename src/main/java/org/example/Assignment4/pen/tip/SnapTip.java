package org.example.Assignment4.pen.tip;

public class SnapTip implements Tip {
    @Override
    public void open() {
        System.out.println("SnapTip: open");
    }

    @Override
    public void seal() {
        System.out.println("SnapTip: seal");
    }
}

