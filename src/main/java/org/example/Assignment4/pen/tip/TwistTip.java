package org.example.Assignment4.pen.tip;

public class TwistTip implements Tip {
    @Override
    public void open() {
        System.out.println("TwistTip: open");
    }

    @Override
    public void seal() {
        System.out.println("TwistTip: seal");
    }
}

