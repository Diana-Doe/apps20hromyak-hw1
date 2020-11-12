package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {2.0, 4.0, 5.0, 6.0, 8.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.0;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {-2.0, 19.6, -20.8, 16.0, 8.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -20.8;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {-2.0, 19.6, -20.8, 16.0, 8.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 19.6;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testClosestToZero () {
        double[] temperatureSeries = {-2.0, 19.6, -20.8, 16.0, 8.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -2.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testClosestToValue () {
        double[] temperatureSeries = {-2.0, 19.6, -20.8, 16.2, 8.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 16.2;

        double actualResult = seriesAnalysis.findTempClosestToValue(16.0);

        assertEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void testLessThan () {
        double[] temperatureSeries = {-2.0, 19.6, -20.8, 16.2, 8.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-2.0, -20.8};

        double[] actualResult = seriesAnalysis.findTempsLessThen(5.0);

        assertArrayEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void testGreaterThan() {
        double[] temperatureSeries = {-2.0, 19.6, -20.8, 16.2, 8.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {19.6, 16.2, 8.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(5.0);

        assertArrayEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {-2.0, 19.6, -20.8, 16.2, 8.0, 28.9};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double average = seriesAnalysis.average();
        double deviation = seriesAnalysis.deviation();
        double min = seriesAnalysis.min();
        double max = seriesAnalysis.max();
        double[] expResult = {average, deviation, min, max};

        TempSummaryStatistics summary = seriesAnalysis.summaryStatistics();
        double[] actualResult = {summary.getAvgTemp(), summary.getDevTemp(),
                summary.getMinTemp(), summary.getMaxTemp()};

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAdd() {
        double[] temperatureSeries = {-2.0, 19.6, -20.8};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(temperatureSeries);
        double[] expResult = {-2.0, 19.6, -20.8, 0.0};

        double[] actualResult = seriesAnalysis.temperatures;

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithLessThanMin() {
        double[] temperatureSeries = {-2.0, 19.6, -20.8, -276};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(temperatureSeries);
    }

}
