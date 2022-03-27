package cz.svetsplhu.isos.service.uploader;

import cz.svetsplhu.isos.repository.model.CompetitionEntity;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;

/**
 * Created by M on 30.05.2017.
 */
@Service
public class CompetitionLoader {
    public static final Logger LOGGER = LoggerFactory.getLogger(CompetitionLoader.class);
    public static final String INFO_SHEET = "INFO";
    public static final Map.Entry<Integer, Integer> COMPETITION_NAME_COORDINATIONS = new AbstractMap.SimpleEntry<>(0, 1);
    public static final Map.Entry<Integer, Integer> YEAR_NAME_COORDINATIONS = new AbstractMap.SimpleEntry<>(1, 1);
    public static final Map.Entry<Integer, Integer> DATE_COORDINATIONS = new AbstractMap.SimpleEntry<>(2, 1);
    public static final Map.Entry<Integer, Integer> PLACE_COORDINATIONS = new AbstractMap.SimpleEntry<>(3, 1);
    public static final Map.Entry<Integer, Integer> JUDGE_COORDINATIONS = new AbstractMap.SimpleEntry<>(4, 1);
    public static final Map.Entry<Integer, Integer> SENSOR_INSTALATION_COORDINATIONS = new AbstractMap.SimpleEntry<>(5, 1);
    public static final Map.Entry<Integer, Integer> STARTER_COORDINATIONS = new AbstractMap.SimpleEntry<>(6, 1);
    public static final Map.Entry<Integer, Integer> TYPE_COORDINATIONS = new AbstractMap.SimpleEntry<>(7, 1);

    public CompetitionEntity load(XSSFWorkbook workbook) {
        CompetitionEntity competition = null;
        try {
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(INFO_SHEET);
            competition = new CompetitionEntity();
            String competitionName = sheet.getRow(COMPETITION_NAME_COORDINATIONS.getKey()).getCell(COMPETITION_NAME_COORDINATIONS.getValue()).getStringCellValue();
            if (competitionName != null && !competitionName.isEmpty()) {
                competition.setCompetitionName(competitionName);
            }
            String yearName = sheet.getRow(YEAR_NAME_COORDINATIONS.getKey()).getCell(YEAR_NAME_COORDINATIONS.getValue()).getStringCellValue();
            if (yearName != null && !yearName.isEmpty()) {
                competition.setName(yearName);
            }
            Date date = sheet.getRow(DATE_COORDINATIONS.getKey()).getCell(DATE_COORDINATIONS.getValue()).getDateCellValue();
            if (date != null) {
                competition.setDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
            String place = sheet.getRow(PLACE_COORDINATIONS.getKey()).getCell(PLACE_COORDINATIONS.getValue()).getStringCellValue();
            if (place != null && !place.isEmpty()) {
                competition.setPlace(place);
            }
            String judge = sheet.getRow(JUDGE_COORDINATIONS.getKey()).getCell(JUDGE_COORDINATIONS.getValue()).getStringCellValue();
            if (judge != null && !judge.isEmpty()) {
                competition.setJudge(judge);
            }
            String sensor = sheet.getRow(SENSOR_INSTALATION_COORDINATIONS.getKey()).getCell(SENSOR_INSTALATION_COORDINATIONS.getValue()).getStringCellValue();
            if (sensor != null && !sensor.isEmpty()) {
                competition.setSensorInstallation(sensor);
            }
            String starter = sheet.getRow(STARTER_COORDINATIONS.getKey()).getCell(STARTER_COORDINATIONS.getValue()).getStringCellValue();
            if (starter != null && !starter.isEmpty()) {
                competition.setStarter(starter);
            }
            String type = sheet.getRow(TYPE_COORDINATIONS.getKey()).getCell(TYPE_COORDINATIONS.getValue()).getStringCellValue();
            if (type != null && !type.isEmpty()) {
                competition.setType(type);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return competition;
    }
}
