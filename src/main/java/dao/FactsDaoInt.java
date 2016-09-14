package dao;

import ru.kon.db.tables.pojos.Fact;

import java.time.LocalDateTime;
import java.util.List;


public interface FactsDaoInt {
    List<Fact> list(String user, LocalDateTime from, LocalDateTime to);
    Fact save(Fact v);
    Fact load(Integer k);
}
