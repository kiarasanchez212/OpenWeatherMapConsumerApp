import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LinechartCreator {
    public void createLineChart(String rootDirectory, ArrayList<ArrayList<String>> databaseContentList) throws IOException {
        var dataset = createWeatherMeasuresXYSeriesCollection(databaseContentList);
        JFreeChart chart = createJFreeChart(dataset);
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = customizeXYLineAndShapeRenderer();
        customizeGridLines(plot, renderer);
        setChartLengendAndTitle(chart);
        ChartUtils.saveChartAsPNG(new File(rootDirectory + "/line_chart.png"), chart, 450, 400);
        System.out.println("Line Chart of Temperature, Humidity and Pressure has been created successfully.");
    }

    private XYSeriesCollection createWeatherMeasuresXYSeriesCollection(ArrayList<ArrayList<String>> databaseContentList) {
        XYSeries temperatureXYSerie = new XYSeries("Temperature");
        XYSeries humidityXYSerie = new XYSeries("Humidity");
        XYSeries pressureXYSerie = new XYSeries("Pressure");
        for (ArrayList<String> strings : databaseContentList) {
            temperatureXYSerie.add(Long.parseLong(strings.get(0)), Double.parseDouble(strings.get(3)));
            humidityXYSerie.add(Long.parseLong(strings.get(0)), Double.parseDouble(strings.get(5)));
            pressureXYSerie.add(Long.parseLong(strings.get(0)), Double.parseDouble(strings.get(4)));
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(temperatureXYSerie);
        dataset.addSeries(humidityXYSerie);
        dataset.addSeries(pressureXYSerie);
        return dataset;
    }

    private JFreeChart createJFreeChart(XYSeriesCollection dataset) {
        return ChartFactory.createXYLineChart(
                "Temperature, Humidity and Pressure by time in millis",
                "time in millis",
                "Temperature, Humidity and Pressure",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private XYLineAndShapeRenderer customizeXYLineAndShapeRenderer() {
        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.PINK);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.ORANGE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesPaint(2, Color.CYAN);
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        return renderer;
    }

    private void customizeGridLines(XYPlot plot, XYLineAndShapeRenderer renderer) {
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
    }

    private void setChartLengendAndTitle(JFreeChart chart) {
        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.setTitle(new TextTitle("Temperature, Humidity and Pressure by time in millis",
                        new Font("Serif", Font.BOLD, 18)
                )
        );
    }
}
