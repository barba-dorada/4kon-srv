package dao;


import ru.kon.db.tables.pojos.Fact;

import java.util.*;

/**
 * Created by admin on 27.07.2016.
 */
public class FactDaoHashMap implements FactsDaoInt {
    Map<Integer, Fact> map = new HashMap<>();
    Integer counter = 0;

    @Override
    public List<Fact> list(String user, Date from, Date to) {
        List<Fact> result = new ArrayList<>();
        for (Fact fact : map.values()) {
            if (!user.equalsIgnoreCase(fact.getUser())) continue;
            if (!inDiapazon(fact.getDate(), from, to)) continue;
            result.add(fact);
        }
        result.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return result;
    }

    boolean inDiapazon(Date date, Date from, Date to) {
        return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
    }

    @Override
    public Fact save(Fact v) {
        counter++;
        v.setId(counter);
        map.put(counter, v);
        return v;
    }

    @Override
    public Fact load(Integer k) {
        Fact v = map.get(k);
        return v;
    }
}
