package pl.adudkiewicz.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.adudkiewicz.services.ReportService;

@RestController
@RequestMapping(value = "report")
@CrossOrigin("*")
public class ReportController
{
    @Autowired
    ReportService reportService;

    
    @GetMapping(value = "{year}/{month}")
    public ResponseEntity<String> getReport(@PathVariable("year") int year,
            @PathVariable("month") int month, HttpServletResponse response)
    {
        Workbook excelFile = reportService.createReport(year, month);
        if (excelFile != null)
        {
            try
            {
                response.setContentType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition",
                        "attachment; filename=Rejestr" + year + "/" + month + ".xlsx");
                response.flushBuffer();
                excelFile.write(response.getOutputStream());

                return new ResponseEntity<>(HttpStatus.OK);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>("Cannot generate file.", HttpStatus.CONFLICT);

    }
}
