package pl.adudkiewicz.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.repositories.RegisterEntryRepository;
import pl.adudkiewicz.repositories.RegisterRepository;

@Service
public class ReportService
{
    @Autowired
    RegisterEntryRepository registerEntryRepository;
    @Autowired
    RegisterRepository registerRepository;

    public Workbook createReport(int year, int month)
    {
        Register register = registerRepository.findByYearAndMonth(year, month);
        List<RegisterEntry> registerEntries = register.getRegistrations();

        InputStream fileRegisterTemplate = getClass().getResourceAsStream("/registerTemplate.xlsx");
        XSSFWorkbook workbookRegister;
        try
        {
            workbookRegister = new XSSFWorkbook(fileRegisterTemplate);
            XSSFSheet sheetRegister = workbookRegister.getSheetAt(0);

            int registerCounter = 1;
            int rowCounter = 4;
            for (RegisterEntry registerEntry : registerEntries)
            {
                XSSFRow row = sheetRegister.getRow(rowCounter);
                row.getCell(0).setCellValue(registerCounter);
                row.getCell(1).setCellValue(registerEntry.getType().toString());
                row.getCell(2).setCellValue(registerEntry.getReceivedDate().toString());
                row.getCell(3).setCellValue(registerEntry.getInvoiceDate().toString());
                row.getCell(4).setCellValue(registerEntry.getInvoiceNumber());
                row.getCell(5).setCellValue(registerEntry.getWholesaler().getNip());
                row.getCell(6).setCellValue(registerEntry.getWholesaler().getName());
                row.getCell(7).setCellValue(registerEntry.getNet23().doubleValue());
                row.getCell(8).setCellValue(registerEntry.getVat23().doubleValue());
                row.getCell(9).setCellValue(registerEntry.getNet8().doubleValue());
                row.getCell(10).setCellValue(registerEntry.getVat8().doubleValue());
                row.getCell(11).setCellValue(registerEntry.getNet5().doubleValue());
                row.getCell(12).setCellValue(registerEntry.getVat5().doubleValue());
                row.getCell(13).setCellValue(registerEntry.getNet0().doubleValue());
                row.getCell(14).setCellValue(registerEntry.getVatSum().doubleValue());
                row.getCell(15).setCellValue(registerEntry.getNetSum().doubleValue());
                row.getCell(16).setCellValue(registerEntry.getGrossValue().doubleValue());

                rowCounter++;
                registerCounter++;

            }
            return workbookRegister;

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;

    }
}
