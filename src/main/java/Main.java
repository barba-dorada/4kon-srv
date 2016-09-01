// For convenience, always static import your generated tables and jOOQ functions to decrease verbosity:

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import static test.generated.Tables.FACT;

public class Main {
    public static void main(String[] args) {
        String userName = "vad";
        String password = "123";
        String url = "jdbc:postgresql://192.168.56.101:5432/kon4";
// Connection is the only JDBC resource that we need
// PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
// ...
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES_9_5);
            Result<Record> result = create.select().from(FACT).fetch();

            for (Record r : result) {
                Integer id = r.getValue(FACT.ID);
                String user = r.getValue(FACT.USER);
                String account = r.getValue(FACT.ACCOUNT);
                BigDecimal amount = r.getValue(FACT.AMOUNT);
                Date d = r.getValue(FACT.DATE);
                System.out.println("ID: " + id + " usr: " + user + " acc: " + account+" d:"+d+" $:"+amount);
            }

         /*   Result<Record> result2 = create.select().from(MYTABLE).fetch();
            for (Record r : result2) {
                Integer id = r.getValue(MYTABLE.ID);
                BigDecimal c2 = r.getValue(MYTABLE.COLUMN_2);
                //Date d = r.getValue(AUTHOR.DATE_OF_BIRTH);
                System.out.println("ID: " + id + " c2: " + c2);
            }*/


        }
// For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
