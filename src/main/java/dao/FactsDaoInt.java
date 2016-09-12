package dao;

//import model.Fact;

import ru.kon.db.tables.pojos.Fact;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 27.07.2016.
 */
public interface FactsDaoInt {
    List<Fact> list(String user, Date from, Date to);
    Fact save(Fact v);
    Fact load(Integer k);
}
