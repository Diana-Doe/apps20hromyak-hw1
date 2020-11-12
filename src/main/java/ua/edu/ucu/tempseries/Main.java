package ua.edu.ucu.tempseries;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[] tem = {5, 8, 10, 12, 17, 20};
        TemperatureSeriesAnalysis temp = new TemperatureSeriesAnalysis(tem);
        double[] t = {};
        System.out.println(temp.deviation());
    }
}
