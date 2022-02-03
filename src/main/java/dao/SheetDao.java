package dao;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SheetDao {
    private static final String spreadsheetId = "1FKzYQ76Z40y7pyr6SZ2bON3Gm4G4OCLnW22yCEgGAgQ";
    private static final String range = "Sheet1!A4:F";

    public List<List<Object>> getSpreadsheet() {
        try {
            Sheets service = Connect.getSheet();
            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            return response.getValues();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    public void spreadsheetWrite(List<ValueRange> data) {
        try {
            Sheets service = Connect.getSheet();
            BatchUpdateValuesRequest body = new BatchUpdateValuesRequest()
                    .setValueInputOption("RAW")
                    .setData(data);
            BatchUpdateValuesResponse result = service.spreadsheets().values().batchUpdate(spreadsheetId, body)
                    .execute();
            System.out.printf("\t-%d celulas alteradas.\n\n", result.getTotalUpdatedCells());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
