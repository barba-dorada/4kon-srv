// For convenience, always static import your generated tables and jOOQ functions to decrease verbosity:

import dao.AccountDaoJOOQ;
import dao.FactDaoJOOQ;
import dao.FactsDaoInt;
import dao.ReadTsvFacts;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ru.kon.db.tables.pojos.Fact;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import static test.generated.Tables.FACT;
//import static test.generated.Tables.USER;

public class Main {
    public static void main(String[] args) {
        String userName = "vad";
        String password = "123";
        String url = "jdbc:postgresql://192.168.56.102:5432/kon4";
// Connection is the only JDBC resource that we need
// PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
// ...
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES_9_5);

            FactsDaoInt factDao = new FactDaoJOOQ(create);
            AccountDaoJOOQ accountDao = new AccountDaoJOOQ(create);

            //SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            ReadTsvFacts.loadFactsFromTSVFile2(s->{
                Fact fact = new Fact();
                fact.setId(null);
                fact.setUser(s[0]);
                fact.setDate(LocalDate.parse(s[1], df).atStartOfDay());
                String account = s[2];
                fact.setAccountId(accountDao.findIdByName(account));
                fact.setAccount(account);
                fact.setSubconto(s[3]);
                fact.setAmount(new BigDecimal(ReadTsvFacts.prepareNum(s[4])));
                fact.setComment(s[5]);
                if(s[0].equalsIgnoreCase("в")){
                    fact.setUserId(1);
                }else {
                    fact.setUserId(2);
                }
                factDao.save(fact);
            });


/*            Result<Record> result = create.select().from(FACT).fetch();

            for (Record r : result) {
                Integer id = r.getValue(FACT.ID);
                String user = r.getValue(FACT.USER);
                String account = r.getValue(FACT.ACCOUNT);
                BigDecimal amount = r.getValue(FACT.AMOUNT);
                Date d = r.getValue(FACT.DATE);
                System.out.println("ID: " + id + " usr: " + user + " acc: " + account+" d:"+d+" $:"+amount);
            }*/

         /*   Result<Record> result2 = create.select().from(MYTABLE).fetch();
            for (Record r : result2) {
                Integer id = r.getValue(MYTABLE.ID);
                BigDecimal c2 = r.getValue(MYTABLE.COLUMN_2);
                //Date d = r.getValue(AUTHOR.DATE_OF_BIRTH);
                System.out.println("ID: " + id + " c2: " + c2);
            }*/

           // int res = create.insertInto(USER).set(USER.NAME, "в").set(USER.LOGIN, "в").set(USER.PASSWORD, "в").execute();
        }
// For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
