import java.io.*;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader;


public class TemperatureLinearRegression {

    private File temperatureFile;
    private File outputFile;

    public TemperatureLinearRegression(File temperatureFile, File outputFile) {
        this.temperatureFile = temperatureFile;
        this.outputFile = outputFile;
    }
    public void trainLinearRegressionModel() throws Exception {
        Instances trainingDataSet = getDataSet(this.temperatureFile);
        Classifier classifier = new weka.classifiers.functions.LinearRegression();
        classifier.buildClassifier(trainingDataSet);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(outputFile));
        objectOutputStream.writeObject(classifier);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println("Linear Regression Model for Temperature has been created successfully.");
    }

    private Instances getDataSet(File temperatureFile) throws IOException {
        int classIdx = 1;
        ArffLoader loader = new ArffLoader();
        loader.setSource(temperatureFile);
        Instances dataSet = loader.getDataSet();
        dataSet.setClassIndex(classIdx);
        return dataSet;
    }
}
