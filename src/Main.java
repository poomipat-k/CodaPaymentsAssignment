import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Please enter a csv file path: ");
        String inputCsvPath = sc.nextLine();
        System.out.println("Processing a csv file: " + inputCsvPath);
        try {
            CsvProcessor csvProcessor = new CsvProcessor(inputCsvPath);
            csvProcessor.writeDataToJson();
        } catch (Exception e) {
            System.out.println("Error when writing output json file");
        }
    }
}

