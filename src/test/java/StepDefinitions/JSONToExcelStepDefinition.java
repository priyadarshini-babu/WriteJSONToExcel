package StepDefinitions;

import org.junit.Test;

import com.sun.tools.javac.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;

public class JSONToExcelStepDefinition {
	
ArrayList<String> dataArray;
public static JsonPath jsonPathValidator;
@Given("Parse JSON response")
public void parse_JSON_response() {

  
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        
        Response response = null;
        try {
            response = RestAssured.given()
                .when()
                .get("/employees");
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
 
 
        //Creation of JsonPath object
         jsonPathValidator = response.jsonPath();
        String strStatus = jsonPathValidator.get("status");
        dataArray = jsonPathValidator.get("data");
        
    }


@Then("Write data in excel")
public void write_data_in_excel()throws IOException {
	JSONToExcelStepDefinition objExcelFile = new JSONToExcelStepDefinition();
	String filePath = "src/main/resources/";
	// Write the file using file name, sheet name and the data to be filled

	objExcelFile.writeExcel(filePath, "JSONRespose.xlsx", "JSONResponse", dataArray);

}
public void writeExcel(String filePath, String fileName, String sheetName, ArrayList<String> dataArray)
		throws IOException {

	// Create an object of File class to open xlsx file

	File file = new File(filePath + fileName);

	// Create an object of FileInputStream class to read excel file

	FileInputStream inputStream = new FileInputStream(file);

	XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

	// Read excel sheet by sheet name

	XSSFSheet sheet = workbook.getSheet(sheetName);

	// Get the current count of rows in excel file

	int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	for (int j = 0; j < dataArray.size(); j++) 
		
	{

		XSSFRow newRow = sheet.createRow(++rowCount);
		newRow.createCell(0).setCellValue((jsonPathValidator.get("data["+j+"].id")).toString());
        newRow.createCell(1).setCellValue((jsonPathValidator.get("data["+j+"].employee_name")).toString());
        newRow.createCell(2).setCellValue((jsonPathValidator.get("data["+j+"].employee_salary")).toString());
        newRow.createCell(3).setCellValue((jsonPathValidator.get("data["+j+"].employee_age")).toString());
        
	}
		
	// Close input stream

	inputStream.close();

	// Create an object of FileOutputStream class to create write data in excel file

	FileOutputStream outputStream = new FileOutputStream(file);

	// write data in the excel file

	workbook.write(outputStream);

	// close output stream

	outputStream.close();

}
}
