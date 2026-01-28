package Utils;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    @SuppressWarnings("deprecation")
	public static Object[][] getExcelData(String sheetName) throws IOException {

        String path = "src/test/resources/TestData/SearchData.xlsx";
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                row.getCell(j).setCellType(CellType.STRING);
                data[i - 1][j] = row.getCell(j).getStringCellValue();
            }
        }

        workbook.close();
        file.close();
        return data;
    }
    
    }
