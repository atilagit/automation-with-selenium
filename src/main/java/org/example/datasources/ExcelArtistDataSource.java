package org.example.datasources;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.entities.Artist;
import org.example.exceptions.ExcelArtistDataSourceException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelArtistDataSource implements ArtistDataSource{

    @Override
    public List<Artist> loadArtists() {
        String caminhoArquivo = "C:\\temp\\contatos.xlsx";
        List<Artist> artists = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(caminhoArquivo)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            Sheet sheet = workbook.getSheetAt(0); // Selecione a primeira planilha

            // Iniciar a leitura a partir da segunda linha (índice 1)
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Pular a primeira linha (cabeçalho)

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Artist artist = new Artist();

                // Mapear cada coluna para os campos do objeto Artist
                artist.setId((long) row.getCell(0).getNumericCellValue());
                artist.setArtistName(row.getCell(1).getStringCellValue());
                artist.setWhatsappName(row.getCell(2).getStringCellValue());
                artist.setInformalName(row.getCell(3).getStringCellValue());
                artist.setArtistPhoneNumber(String.valueOf((long) row.getCell(4).getNumericCellValue()));
                artist.setSoloSinger(Boolean.parseBoolean(row.getCell(5).getStringCellValue()));
                artist.setResponsiblePhoneNumber(row.getCell(6).getStringCellValue());
                artist.setResponsibleName(row.getCell(7).getStringCellValue());

                artists.add(artist);
            }

            workbook.close();
        } catch (IOException e) {
            throw new ExcelArtistDataSourceException(e.getMessage());
        }

        return artists;
    }
}
