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
import java.util.Objects;

public class ExcelArtistDataSource implements ArtistDataSource{

    @Override
    public List<Artist> loadArtists() {
        String caminhoArquivo = "C:\\composicao\\automacao\\contatos_teste.xlsx";
        List<Artist> artists = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(caminhoArquivo)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            Sheet sheet = workbook.getSheetAt(0); // Selecione a primeira planilha

            // Iniciar a leitura a partir da segunda linha (índice 1)
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Pular a primeira linha (cabeçalho)

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (thereIsNoDataInTheRow(row)) break;

                Artist artist = new Artist();

                // Mapear cada coluna para os campos do objeto Artist
                artist.setId((long) row.getCell(0).getNumericCellValue());
                artist.setPhoneNumber(String.valueOf((long) row.getCell(1).getNumericCellValue()));
                artist.setWhatsappName(row.getCell(2).getStringCellValue());
                artist.setArtistName(row.getCell(3).getStringCellValue());
                artist.setInformalArtistName(row.getCell(4).getStringCellValue());
                artist.setSoloSinger(Boolean.parseBoolean(row.getCell(5).getStringCellValue()));
                artist.setResponsibleContact(Boolean.parseBoolean(row.getCell(6).getStringCellValue()));
                artist.setInformalResponsibleName(row.getCell(7).getStringCellValue());
                artist.setProducerForSeveralArtists(Boolean.parseBoolean(row.getCell(8).getStringCellValue()));
                artist.setInformalNameOfProducer(row.getCell(9).getStringCellValue());

                artists.add(artist);
            }

            workbook.close();
        } catch (IOException e) {
            throw new ExcelArtistDataSourceException(e.getMessage());
        }

        return artists;
    }

    private static boolean thereIsNoDataInTheRow(Row row) {
        return (Objects.isNull(row.getCell(2).getStringCellValue()) || "".equals(row.getCell(2).getStringCellValue()));
    }
}
