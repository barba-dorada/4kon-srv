/**
 * Created by admin on 20.07.2016.
 */

import dao.FactDaoHashMap;
import dao.FactsDaoInt;
import dao.ReadTsvFacts;
import model.Fact;
import org.jetbrains.annotations.NotNull;
import spark.Route;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static spark.Spark.*;

public class KonServer {
    static FactsDaoInt factsDao= new FactDaoHashMap();
    public static void main(String[] args) throws ParseException, IOException {
        if (true) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }

        ReadTsvFacts.loadFacts(factsDao);

        get("/hello", (req, res) -> "Hello World");
        get("/fact", getRoute());

        System.out.println("http://localhost:4567/hello");
    }

    @NotNull
    static Route getRoute() {
        return (req, res) -> {
            SimpleDateFormat sdf =new SimpleDateFormat("dd.MM.yyyy");
            Date date = sdf.parse("01.07.2016");
            List<Fact> list = factsDao.list("Л", date, date);
            String result="";
            for (Fact fact : list) {
                result=result+fact.toString()+"\n";
            }
            return result;
        };
    }
}