package ed.citizen.info.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import ed.citizen.info.binding.SearchCriteria;
import ed.citizen.info.dao.CitizenPlanRepo;
import ed.citizen.info.entity.CitizenPlan;
import ed.citizen.info.service.CitizenPlanService;
import ed.citizen.info.utils.EmailsUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

	@Autowired
	CitizenPlanRepo citizenPlanRepo;
	@Autowired
	EmailsUtils emailsUtils;

	@Override
	public List<String> getPlanNames() {
		return citizenPlanRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return citizenPlanRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> getCitizens(SearchCriteria searchCriteria) {
		CitizenPlan citizenPlan = new CitizenPlan();

		if (!searchCriteria.getPlanName().isEmpty()) {
			citizenPlan.setPlanName(searchCriteria.getPlanName());
		}

		if (!searchCriteria.getPlanStatus().isEmpty()) {
			citizenPlan.setPlanStatus(searchCriteria.getPlanStatus());
		}

		if (!searchCriteria.getGender().isEmpty()) {
			citizenPlan.setGender(searchCriteria.getGender());
		}

		if (searchCriteria.getStartDate() != null) {
			citizenPlan.setStartDate(searchCriteria.getStartDate());
		}

		if (searchCriteria.getEndDate() != null) {
			citizenPlan.setEndDate(searchCriteria.getEndDate());
		}

		Example<CitizenPlan> example = Example.of(citizenPlan);
		return citizenPlanRepo.findAll(example);
	}

	@Override
	public void generateExcel(HttpServletResponse response) {

		List<CitizenPlan> citizenPlans = citizenPlanRepo.findAll();
		try(HSSFWorkbook workbook = new HSSFWorkbook()) {
			
			// step - 1 Create a workbook
			
			// Create a sheet in the workbook
			HSSFSheet sheet = workbook.createSheet("MyData");
			// Create Header row
			HSSFRow headerRow = sheet.createRow(0);
			// set data for header row cells
			headerRow.createCell(0).setCellValue("id");
			headerRow.createCell(1).setCellValue("Name");
			headerRow.createCell(2).setCellValue("Email");
			headerRow.createCell(3).setCellValue("Gender");
			headerRow.createCell(4).setCellValue("Plan Name");
			headerRow.createCell(5).setCellValue("Plan Status");
			headerRow.createCell(6).setCellValue("SSN");

			int rowIndex = 1;

			for (CitizenPlan citizenPlan : citizenPlans) {

				HSSFRow bodyRow = sheet.createRow(rowIndex);

				bodyRow.createCell(0).setCellValue(citizenPlan.getCitizenId());
				bodyRow.createCell(1).setCellValue(citizenPlan.getPlanName());
				bodyRow.createCell(2).setCellValue(citizenPlan.getEmail());
				bodyRow.createCell(3).setCellValue(citizenPlan.getGender());
				bodyRow.createCell(4).setCellValue(citizenPlan.getPlanName());
				bodyRow.createCell(5).setCellValue(citizenPlan.getPlanStatus());
				bodyRow.createCell(6).setCellValue(citizenPlan.getSsn());

				rowIndex++;

			}
			
			//To send file in email attachment
			File file = new File("myReport.xls");
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			emailsUtils.sendEmail(file);
			
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			
			workbook.close();
			outputStream.close();
			fos.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void generatePdf(HttpServletResponse response) {
		
		File file = new File("data.pdf");
		
		try(FileOutputStream fos = new FileOutputStream(file);
				Document document = new Document(PageSize.A4);) {
			
			List<CitizenPlan> citizenPlans = citizenPlanRepo.findAll();

			// Creating the Object of Document
			

			// Getting instance of PdfWriter
			PdfWriter.getInstance(document, response.getOutputStream());
			PdfWriter.getInstance(document, fos);
			// Opening the created document to change it
		    document.open();
		    // Creating font
		    // Setting font style and size
		    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		    fontTiltle.setSize(20);
		    
		 // Creating paragraph
		    Paragraph paragraph1 = new Paragraph("Citizen Plan Report", fontTiltle);
		    // Aligning the paragraph in the document
		    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
		    // Adding the created paragraph in the document
		    document.add(paragraph1);
		 // Creating a table of the 4 columns
		    PdfPTable table = new PdfPTable(6);
		    // Setting width of the table, its columns and spacing
		    table.setWidthPercentage(100f);
		    table.setWidths(new int[] {3,3,3,3,3,3});
		    table.setSpacingBefore(5);
		    
		    
		    // Create Table Cells for the table header
		    PdfPCell cell = new PdfPCell();
		    // Setting the background color and padding of the table cell
		    cell.setBackgroundColor(CMYKColor.BLUE);
		    cell.setPadding(5);
		    
		    // Creating font
		    // Setting font style and size
		    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		    font.setColor(CMYKColor.WHITE);
		    
		    // Adding headings in the created table cell or  header
		    // Adding Cell to table
		    cell.setPhrase(new Phrase("ID", font));
		    table.addCell(cell);
		    cell.setPhrase(new Phrase("Name", font));
		    table.addCell(cell);
		    cell.setPhrase(new Phrase("Email", font));
		    table.addCell(cell);
		    cell.setPhrase(new Phrase("Gender", font));
		    table.addCell(cell);
		    cell.setPhrase(new Phrase("plan name", font));
		    table.addCell(cell);
		    cell.setPhrase(new Phrase("plan status", font));
		    table.addCell(cell);
		    
		    for (CitizenPlan citizenPlan : citizenPlans) {
		    	table.addCell(String.valueOf(citizenPlan.getCitizenId()));
		    	table.addCell(String.valueOf(citizenPlan.getName()));
		    	table.addCell(String.valueOf(citizenPlan.getEmail()));
		    	table.addCell(String.valueOf(citizenPlan.getGender()));
		    	table.addCell(String.valueOf(citizenPlan.getPlanName()));
		    	table.addCell(String.valueOf(citizenPlan.getPlanStatus()));
			}
		    
		    // Adding the created table to the document
		    document.add(table);
		   
		    // Closing the document
		    document.close();
		    fos.close();
		    emailsUtils.sendEmail(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
  		
	}

}
