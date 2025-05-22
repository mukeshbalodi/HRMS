package Utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtil {

    // Centralized CSV path
    private static final String CSV_PATH = "src/test/resources/EmployeeData.csv"; 

   
    public static List<String[]> readCSV() throws IOException, CsvValidationException {
        List<String[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader("src/test/resources/EmployeeData.csv"))) {
            String[] line;
            boolean skipHeader = true;

            while ((line = reader.readNext()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                data.add(line);
            }
        }

        return data;
    }

    // DataProvider method for TestNG
    @DataProvider(name = "loginData")
    public static Iterator<Object[]> loginDataProvider() throws CsvValidationException, IOException {
        List<String[]> csvData = readCSV();
        List<Object[]> testData = new ArrayList<>();

        for (String[] row : csvData) {
            testData.add(new Object[]{row[0], row[1]}); //  2 columns: username, password
        }

        return testData.iterator();
    }
}
