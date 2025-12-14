package enums;

import java.util.Arrays;

public enum LockerSize {
    XS(new double[] { 10, 10, 10 }, "XS"),
    S(new double[] { 20, 20, 20 }, "S"),
    M(new double[] { 50, 50, 50 }, "M"),
    L(new double[] { 100, 100, 100 }, "L");

    private final double[] dimensions;
    String sizeString;

    private LockerSize(double[] dimensions, String sizeString) {
        this.dimensions = dimensions;
        this.sizeString = sizeString;
    }

    public double[] getDimensions() {
        Arrays.sort(dimensions);
        return dimensions;
    }

    public String getSizeString() {
        return this.sizeString;
    }

}
