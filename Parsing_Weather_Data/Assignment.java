package Parsing_Weather_Data;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Assignment {
    private static String temperatureCol = "TemperatureF";
    private static String dateCol = "DateUTC";
    private static String humidityCol = "Humidity";
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord cold = null;
        for (CSVRecord record : parser) {
            double temperature = Double.parseDouble(record.get(temperatureCol));
            if (cold == null && temperature != -9999) {
                cold = record;
            } else {
                if (temperature < Double.parseDouble(cold.get(temperatureCol)) && temperature != -9999) {
                    cold = record;
                }
            }
        }
        return cold;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord record = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + record.get(temperatureCol) + " at " + record.get(dateCol));
    }

    public File fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        File file = null;
        CSVRecord coldest = null;
        for (File selectFile : dr.selectedFiles()) {
            FileResource fr = new FileResource(selectFile);
            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            if (coldest == null) {
                coldest = record;
                file = selectFile;
            } else {
                if (Double.parseDouble(record.get(temperatureCol)) < Double.parseDouble(coldest.get(temperatureCol))) {
                    coldest = record;
                    file = selectFile;
                }
            }
        }
        return file;
    }

    public void printAllRecordsInFile(CSVParser parser) {
        for (CSVRecord record : parser) {
            System.out.println(record.get(dateCol) + " " + record.get(temperatureCol));
        }
    }

    public void testFileWithColdestTemperature() {
        File file = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + file.getName());

        FileResource fr = new FileResource(file);
        String coldestTemperature = coldestHourInFile(fr.getCSVParser()).get(temperatureCol);
        System.out.println("Coldest temperature on that day was " + coldestTemperature);

        System.out.println("All the Temperatures on the coldest day were:");
        printAllRecordsInFile(fr.getCSVParser());
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord minHumidity = null;
        for (CSVRecord record : parser) {
            String humidityString = record.get(humidityCol);
            if (!humidityString.equals("N/A")) {
                int humidity = Integer.parseInt(humidityString);
                if (minHumidity == null) {
                    minHumidity = record;
                } else {
                    if (humidity < Integer.parseInt(minHumidity.get(Assignment.humidityCol))) {
                        minHumidity = record;
                    }
                }
            }
        }
        return minHumidity;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord minHumidity = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + minHumidity.get(humidityCol) + " at " + minHumidity.get(dateCol));
    }


    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord minHumidity = null;
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
            if (minHumidity == null) {
                minHumidity = record;
            } else {
                if (Integer.parseInt(record.get(humidityCol)) < Integer.parseInt(minHumidity.get(humidityCol))) {
                    minHumidity = record;
                }
            }
        }
        return minHumidity;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidityRecord.get(humidityCol) + " at " + lowestHumidityRecord.get(dateCol));
    }


    public double averageTemperatureInFile(CSVParser parser) {
        double total = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            double temperature = Double.parseDouble(record.get(temperatureCol));
            if (temperature != -9999) {
                total += temperature;
                count++;
            }
        }
        return total / count;
    }


    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + averageTemperature);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double total = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            int humidity = Integer.parseInt(record.get(humidityCol));
            if (humidity >= value) {
                double temperature = Double.parseDouble(record.get(temperatureCol));
                if (temperature != -9999) {
                    total += temperature;
                    count++;
                }
            }
        }
        if (count == 0) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return total / count;
        }
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (averageTemperature == Double.NEGATIVE_INFINITY) {
            System.out.println("No temperature with that humidity");
        } else {
            System.out.println("Average temperature when high humidity is " + averageTemperature);
        }
    }
}
