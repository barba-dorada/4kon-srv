package dao;

import model.Fact;
import org.jooq.DSLContext;

import java.util.Date;
import java.util.List;

import static org.jooq.impl.DSL.defaultValue;
import static test.generated.Tables.FACT;

/**
 * Created by admin on 30.08.2016.
 */
public class FactDaoJOOQ implements FactsDaoInt {
    DSLContext dsl;

    public FactDaoJOOQ(DSLContext create) {
        dsl = create;
    }

    @Override
    public List<Fact> list(String user, Date from, Date to) {
        return null;
    }

    @Override
    public Fact save(Fact v) {

        dsl.insertInto(FACT)
                .set(FACT.ID, defaultValue(FACT.ID))
                .set(FACT.USER, v.getUser())
                .set(FACT.DATE, new java.sql.Date(v.getDate().getTime()))
                .set(FACT.ACCOUNT, v.getAccount())
                .set(FACT.AMOUNT, v.getAmount())
                .set(FACT.COMMENT, v.getComment())
                .execute();

        return v;
    }

    @Override
    public Fact load(Integer k) {
        return null;
    }
}
