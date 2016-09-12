/**
 * Created by admin on 20.07.2016.
 */

import dao.FactDaoHashMap;
import dao.FactsDaoInt;
import dao.ReadTsvFacts;
import org.jetbrains.annotations.NotNull;
import ru.kon.db.tables.pojos.Fact;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class KonServer {
    static FactsDaoInt factsDao = new FactDaoHashMap();

    public static void main(String[] args) throws ParseException, IOException {
        if (true) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }

        ReadTsvFacts.loadFactsFromTSVFile(factsDao);

        get("/hello", (req, res) -> "Hello World");
        get("/fact", getRoute());


        //get("/facts", getTemplateViewRoute(), new MustacheTemplateEngine());

        get("/facts/:user/:from/:to", KonServer::handleFacts, new MustacheTemplateEngine());

        System.out.println("http://localhost:4567/hello");
    }

    static public ModelAndView handleFacts(Request req, Response res) throws Exception {
        Map map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String user = req.params("user");
        String from = req.params("from");
        String to = req.params("to");

        map.put("user",user);
        map.put("from",from);
        map.put("to",to);


        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = sdf.parse(from);
            toDate = sdf.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Fact> list = factsDao.list(user, fromDate, toDate);
        map.put("facts", list);

        // factslist.mustache file is in resources/templates directory
        return new ModelAndView(map, "factslist.mustache");
    }

    @NotNull
    static TemplateViewRoute getTemplateViewRoute() {
        Map map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = sdf.parse("01.07.2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Fact> list = factsDao.list("Л", date, date);
        map.put("facts", list);

        // factslist.mustache file is in resources/templates directory
        return (rq, rs) -> new ModelAndView(map, "factslist.mustache");
    }

    @NotNull
    static Route getRoute() {
      /*  Map map = new HashMap();
        map.put("facts", "Sam");
        return  (rq, rs) -> new ModelAndView(map, "factslist.mustache");*/

        return (req, res) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date date = sdf.parse("01.07.2016");
            List<Fact> list = factsDao.list("Л", date, date);
            Map map = new HashMap();
            map.put("facts", list);
            return new ModelAndView(map, "factslist.mustache");


        };
    }
}