package autoemails;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StepsFromFileHandler {
    private String filePath = "/Users/zacha/OneDrive/Desktop/steps.csv";

    public void assignStepsToDay(Day day) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }

            for(List<String> record : records){
                if(record.get(0).equalsIgnoreCase(day.getDaySlashMonth())){
                    //update stepcount
                    day.setStepCount(Integer.parseInt(record.get(1)));
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }
    }


    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
