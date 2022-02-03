package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.model.ValueRange;

import dao.SheetDao;
import models.Student;

public class spreadsheetControl {
    private static final SheetDao sheetDao = new SheetDao();

    public static void spreadsheetSave() {
        System.out.println("[2] - Leitura dos dados da planilha\n"); 
        List<List<Object>> values = sheetDao.getSpreadsheet();
        if (values == null || values.isEmpty()) {
            System.out.println("Nenhum dado encontrado.");
        } else {
            System.out.println("[3] - Execucao dos Calculos\n"); 
            List<String> listSituation = new ArrayList<>();
            List<String> listNaf = new ArrayList<>();
            List<ValueRange> data = new ArrayList<>();
            List<List<Object>> valuesSave;
            Student student;
            int maxClass = 60;
            for (List row : values) {
                student = new Student(
                        (row.get(0) != null) ? Integer.parseInt((String) row.get(0)) : 0,
                        (row.get(1) != null) ? (String) row.get(1) : null,
                        (row.get(2) != null) ? Integer.parseInt((String) row.get(2)) : 0,
                        (row.get(3) != null) ? Double.parseDouble((String) row.get(3)) : 0,
                        (row.get(4) != null) ? Double.parseDouble((String) row.get(4)) : 0,
                        (row.get(5) != null) ? Double.parseDouble((String) row.get(5)) : 0);
                listSituation.add(student.situation(maxClass));
                listNaf.add(String.format("%.0f", student.minNaf(maxClass)));
                log.logStudent.printStudent(student);
            }
            valuesSave = Arrays.asList(
                Arrays.asList(listSituation.toArray())                
            );
            data.add(new ValueRange().setRange("Sheet1!G4").setValues(valuesSave).setMajorDimension("COLUMNS"));
            valuesSave = Arrays.asList(
                Arrays.asList(listNaf.toArray())                
            );
            data.add(new ValueRange().setRange("Sheet1!H4").setValues(valuesSave).setMajorDimension("COLUMNS"));
            System.out.println("\n[4] - Gravacao dos dados na planilha\n"); 
            sheetDao.spreadsheetWrite(data);            
        }
    }
}
