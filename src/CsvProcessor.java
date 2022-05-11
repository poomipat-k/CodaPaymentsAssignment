import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvProcessor {
    private String[] fieldNames;
    private List<Map<String, String>> data;

    public CsvProcessor(String filePath) {
        this(filePath, ",");
    }
    public CsvProcessor(String filePath, String delimiter) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            this.fieldNames = br.readLine().split(delimiter);
            Gson gsonObj = new Gson();
            // Read file line by line
            List<Map<String, String>> records = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                // To replace second string with custom data type
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < fieldNames.length; i++) {
                    row.put(fieldNames[i], values[i]);
                }
                records.add(row);
            }
            this.data = records;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeDataToJson() throws Exception {
        Gson gsonObj = new Gson();
        if (this.data.size() > 0) {
            for (Map<String, String> row : this.data) {
                writeJson(gsonObj, row);
            }
        } else {
            System.out.println("Data is empty");
        }
    }

    private void writeJson(Gson gsonObj, Map <String, String> row) throws  Exception {
        String generatedString = RandomStringUtils.randomAlphanumeric(8);
        String fileName = "src/output/out_" + generatedString + ".json";
        writeJson(gsonObj, row, fileName);
    }

    private void writeJson(Gson gsonObj, Map <String, String> row, String outputFilePath) throws  Exception{
        String jsonStr = gsonObj.toJson(row);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        writer.write(jsonStr);
        writer.close();
    }
}