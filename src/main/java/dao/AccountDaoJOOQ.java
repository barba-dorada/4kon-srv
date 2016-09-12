package dao;

import org.jooq.DSLContext;
import org.jooq.Record1;
import ru.kon.db.tables.daos.AccountDao;
import ru.kon.db.tables.pojos.Account;
import ru.kon.db.tables.pojos.Fact;
import ru.kon.db.tables.records.AccountRecord;
import ru.kon.db.tables.records.FactRecord;

import java.util.Date;
import java.util.List;

import static ru.kon.db.Tables.ACCOUNT;
import static ru.kon.db.Tables.FACT;

/**
 * Created by admin on 30.08.2016.
 */
public class AccountDaoJOOQ  {
    DSLContext create;
    AccountDao accountDao;

    public AccountDaoJOOQ(DSLContext dslContext) {
        create = dslContext;
        accountDao=new AccountDao(dslContext.configuration());
    }

    public List<Account> list(String user) {
        return null;
    }

    public Account save(Account v) {
        AccountRecord record = create.newRecord(ACCOUNT, v);
        record.insert();
        v.setId(record.getId());
        return v;
    }

    public Account load(Integer k) {
        return accountDao.fetchOneById(k);
    }

    public Integer findIdByName(String name) {
        Integer id = create.select().from(ACCOUNT).where(ACCOUNT.NAME.equalIgnoreCase(name)).fetchAny(ACCOUNT.ID);
        if(id==null){
            Account a=new Account();
            a.setName(name);
            a=save(a);
            return a.getId();
        }
        return id;
    }
}
