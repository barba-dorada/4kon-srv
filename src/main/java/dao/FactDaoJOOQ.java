package dao;

import org.jooq.DSLContext;
import ru.kon.db.tables.pojos.Fact;
import ru.kon.db.tables.records.FactRecord;

import java.util.Date;
import java.util.List;

import static ru.kon.db.Tables.FACT;

/**
 * Created by admin on 30.08.2016.
 */
public class FactDaoJOOQ implements FactsDaoInt {
    DSLContext create;

    public FactDaoJOOQ(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public List<Fact> list(String user, Date from, Date to) {
        return null;
    }

    @Override
    public Fact save(Fact v) {
        FactRecord record= create.newRecord(FACT,v);
        record.insert();
        v.setId(record.getId());
        return v;
    }

    @Override
    public Fact load(Integer k) {
        return null;
    }
}
