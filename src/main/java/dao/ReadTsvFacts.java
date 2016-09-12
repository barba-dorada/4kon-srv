package dao;


import ru.kon.db.tables.pojos.Fact;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by admin on 23.07.2016.
 */
public class ReadTsvFacts {


    public static void loadFactsFromTSVFile(FactsDaoInt factsDao) throws IOException, ParseException {
        Path path = Paths.get("src\\main\\resources\\db\\facts.tsv");
        List<String> lines = Files.readAllLines(path);
        String names = lines.remove(0);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        for (String line : lines) {
            String[] s = line.split("\\t");
            //System.out.printf("%s-%s-%s %s %s %s:%s\n", s);

            Fact fact = new Fact();
            fact.setId(null);
            fact.setUser(s[0]);
            fact.setDate(new Date(df.parse(s[1]).getTime()));
            fact.setAccount(s[2]);
            fact.setSubconto(s[3]);
            fact.setAmount(new BigDecimal(prepareNum(s[4])));
            fact.setComment(s[5]);
            if (s[0].equalsIgnoreCase("в")) {
                fact.setUserId(1);
            } else {
                fact.setUserId(2);
            }
            factsDao.save(fact);

            //System.out.println(fact);
        }
    }

    public static void loadFactsFromTSVFile2(Consumer<String[]> consumer) throws IOException, ParseException {
        Path path = Paths.get("src\\main\\resources\\db\\facts.tsv");
        List<String> lines = Files.readAllLines(path);
        String names = lines.remove(0);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        for (String line : lines) {
            String[] s = line.split("\\t");
            //System.out.printf("%s-%s-%s %s %s %s:%s\n", s);
            consumer.accept(s);

            //System.out.println(fact);
        }
    }

    public static String prepareNum(String s) {
        s = s.replace(',', '.');
        s = s.replaceAll("[ ]", "");
        return s;
    }
}

