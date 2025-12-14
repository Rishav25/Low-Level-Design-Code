package models;

import java.util.Arrays;

public class Parcel {
    double length;
    double breadth;
    double height;
    String packageName;

    public Parcel(double length, double breadth, double height, String packageName) {
        this.length = length;
        this.breadth = breadth;
        this.height = height;
        this.packageName = packageName;
    }

    public double[] getSortedDimensions() {
        double[] dimensions = new double[3];
        dimensions[0] = length;
        dimensions[1] = breadth;
        dimensions[2] = height;
        Arrays.sort(dimensions);
        return dimensions;
    }

    public String getParcelName() {
        return this.packageName;
    }

}
