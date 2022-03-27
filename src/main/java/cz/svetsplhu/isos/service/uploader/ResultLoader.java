package cz.svetsplhu.isos.service.uploader;

import cz.svetsplhu.isos.repository.CategoryRepository;
import cz.svetsplhu.isos.repository.model.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by M on 14.05.2017.
 */
@Service
public class ResultLoader {
    public static final Logger LOGGER = LoggerFactory.getLogger(ResultLoader.class);

    private static final Double MAX_VALUE = 999.0;
    private static final String KAT_I = "KAT_I";
    private static final String KAT_II = "KAT_II";
    private static final String KAT_III = "KAT_III";
    private static final String KAT_IV = "KAT_IV";
    private static final String KAT_V = "KAT_V";
    private static final String KAT_VI = "KAT_VI";

    private CompetitionLoader competitionLoader;
    private CategoryRepository categoryRepository;

    @Inject
    public ResultLoader(CompetitionLoader competitionLoader, CategoryRepository categoryRepository) {
        this.competitionLoader = competitionLoader;
        this.categoryRepository = categoryRepository;
    }

    //    @PostConstruct
    public void initTableKeys() {
        CategoryEntity cat1 = new CategoryEntity();
        cat1.setCategoryKey(KAT_I);
        cat1.setLabel("Žáci");
        cat1.setRopeLength(4.5);

        CategoryEntity cat2 = new CategoryEntity();
        cat2.setCategoryKey(KAT_II);
        cat2.setLabel("Dorostenci");
        cat2.setRopeLength(4.5);

        CategoryEntity cat3 = new CategoryEntity();
        cat3.setCategoryKey(KAT_III);
        cat3.setLabel("Muži");
        cat3.setRopeLength(8.0);

        CategoryEntity cat4 = new CategoryEntity();
        cat4.setCategoryKey(KAT_IV);
        cat4.setLabel("Senioři");
        cat4.setRopeLength(8.0);

        CategoryEntity cat5 = new CategoryEntity();
        cat5.setCategoryKey(KAT_V);
        cat5.setLabel("Ženy a dorostenky");
        cat5.setRopeLength(4.5);


        CategoryEntity cat6 = new CategoryEntity();
        cat6.setCategoryKey(KAT_VI);
        cat6.setLabel("Žákyně");
        cat6.setRopeLength(4.5);

        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(cat3);
        categoryRepository.save(cat4);
        categoryRepository.save(cat5);
        categoryRepository.save(cat6);
    }


    public CompetitionEntity load(InputStream file) {
//        InputStream file = null;
        try {
//            file = this.getClass().getClassLoader().getResourceAsStream("supcik_2017.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            CompetitionEntity competition = competitionLoader.load(workbook);
            load(workbook, competition);
            return competition;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public void load(XSSFWorkbook workbook, CompetitionEntity competition) {
        try {
            XSSFSheet sheet = workbook.getSheet(KAT_I);
            if (sheet != null) {
                load(sheet, competition, KAT_I);
            }
            sheet = workbook.getSheet(KAT_II);
            if (sheet != null) {
                load(sheet, competition, KAT_II);
            }
            sheet = workbook.getSheet(KAT_III);
            if (sheet != null) {
                load(sheet, competition, KAT_III);
            }
            sheet = workbook.getSheet(KAT_IV);
            if (sheet != null) {
                load(sheet, competition, KAT_IV);
            }
            sheet = workbook.getSheet(KAT_V);
            if (sheet != null) {
                load(sheet, competition, KAT_V);
            }
            sheet = workbook.getSheet(KAT_VI);
            if (sheet != null) {
                load(sheet, competition, KAT_VI);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private CompetitionEntity load(XSSFSheet sheet, CompetitionEntity competition, String categoryKey) {
        CategoryEntity category = categoryRepository.findByCategoryKey(categoryKey);
        int i = 1;
        for (; ; ) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            String firstName = row.getCell(1) == null ? null : row.getCell(1).getStringCellValue();
            String lastName = row.getCell(2) == null ? null : row.getCell(2).getStringCellValue();
            Double year = row.getCell(3) == null ? null : row.getCell(3).getNumericCellValue();
            Integer yearOfBirth = null;
            if (year != null) {
                yearOfBirth = (int) year.doubleValue();
            }
            String organization = row.getCell(4) == null ? null : row.getCell(4).getStringCellValue();
            Double time1 = getTime(row.getCell(5));
            Double time2 = getTime(row.getCell(6));
            Double time3 = getTime(row.getCell(7));
            Double time4 = getTime(row.getCell(8));
            if (firstName == null || lastName == null || yearOfBirth == null || organization == null
                    || (time1 == null && time2 == null && time3 == null && time4 == null)) {
                break;
            }
            RopeClimberEntity ropeClimber = new RopeClimberEntity(firstName.trim(), lastName.trim(), yearOfBirth);
            ParticipationEntity participation = new ParticipationEntity();
            participation.setCategory(category);
            participation.setOrganization(organization);
            createTime(time1, 1, participation);
            createTime(time2, 2, participation);
            createTime(time3, 3, participation);
            createTime(time4, 4, participation);
            participation.setRopeClimber(ropeClimber);
            participation.setCompetition(competition);
            competition.getParticipationList().add(participation);
            i++;
        }
        return competition;
    }

    private void createTime(Double time, Integer round, ParticipationEntity participation) {
        if (time != null) {
            participation.getTimeList().add(new TimeEntity(round, time));
        }
    }

    private Double getTime(XSSFCell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
            return cell.getNumericCellValue();
        } else if (cell.getStringCellValue().isEmpty()) {
            return null;
        } else if (cell.getStringCellValue().equalsIgnoreCase("x") || cell.getStringCellValue().equalsIgnoreCase("n") || cell.getStringCellValue().equalsIgnoreCase("q")) {
            return MAX_VALUE;
        }
        return null;
    }
}
