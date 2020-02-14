package autoemails;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StepsFromFileHandler {
    private String filePath = "/Users/zacha/OneDrive/Desktop/steps.csv";

    public void assignFileValuesToDay(Day day) {
        List<List<String>> records = new ArrayList<List<String>>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }

            for(List<String> record : records){
                //if dates match
                if(record.get(0).equalsIgnoreCase(day.getDaySlashMonth())){
                    //update step count and emailSent boolean
                    day.setStepCount((record.get(1)));
                    day.setEmailSent(Boolean.parseBoolean(record.get(2)));
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
