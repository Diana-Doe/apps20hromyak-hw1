package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.lang.IllegalArgumentException;
import java.lang.Math.*;

public class TemperatureSeriesAnalysis {
    public double[] temperatures;
    public int len;
    static final int minTemp = -273;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[1];
        len = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if (DoubleStream.of(temperatureSeries).anyMatch(x -> x < minTemp))
            throw new IllegalArgumentException("Temperature less than -273!");
        if (temperatureSeries.length == 0)
            temperatures = new double[1];
        else
            temperatures = temperatureSeries;
        len = temperatureSeries.length;
    }

    public double average() {
        if (len == 0)
            throw new IllegalArgumentException("Empty array!");
        double sum = 0;
        for (int i=0; i < len; i++) {
            sum += temperatures[i];
        }
        return sum / len;
    }

    public double deviation() {
        if (len == 0)
            throw new IllegalArgumentException("Empty array!");
        double average = average();
        double count = 0;
        for (int i=0; i < len; i++) {
            count += Math.pow(temperatures[i] - average, 2);
        }
        return Math.sqrt(count / len);
    }

    public double min() {
        if (len == 0)
            throw new IllegalArgumentException("Empty array!");
        double min = temperatures[0];
        for (int i=1; i < len; i++) {
            if (temperatures[i] < min)
                min = temperatures[i];
        }
        return min;
    }

    public double max() {
        if (len == 0)
            throw new IllegalArgumentException("Empty array!");
        double max = temperatures[0];
        for (int i=1; i < len; i++) {
            if (temperatures[i] > max)
                max = temperatures[i];
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (len == 0)
            throw new IllegalArgumentException("Empty array!");
        double best = Math.abs(temperatures[0] - tempValue);
        double bestNum = temperatures[0];
        for (int i=1; i < len; i++) {
            if (Math.abs(temperatures[i] - tempValue) < best) {
                best = Math.abs(temperatures[i] - tempValue);
                bestNum = temperatures[i];
            }
        }
        return bestNum;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (len == 0)
            throw new IllegalArgumentException("Empty array!");
        double[] lessTemps = new double[len];
        int j = 0;
        for (int i=0; i < len; i++) {
            if (temperatures[i] < tempValue)
                lessTemps[j++] = temperatures[i];
        }
        return Arrays.copyOf(lessTemps, j);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (len == 0)
            throw new IllegalArgumentException("Empty array!");
        double[] greatTemps = new double[len];
        int j = 0;
        for (int i=0; i < len; i++) {
            if (temperatures[i] > tempValue)
                greatTemps[j++] = temperatures[i];
        }
        return Arrays.copyOf(greatTemps, j);
    }

    public TempSummaryStatistics summaryStatistics() {
        if (len == 0)
            throw new IllegalArgumentException("Empty array!");
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        if (DoubleStream.of(temps).anyMatch(x -> x < minTemp))
            throw new IllegalArgumentException("Temperature less than -273");
        while (temperatures.length - len < temps.length) {
            temperatures = Arrays.copyOf(temperatures, temperatures.length*2);
        }
        for (double temp : temps) {
            temperatures[len++] = temp;
        }
        System.out.println(Arrays.toString(temperatures));
        return 0;
    }
}
