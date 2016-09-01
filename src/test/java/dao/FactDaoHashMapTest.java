package dao;

import model.Fact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by admin on 27.07.2016.
 */
public class FactDaoHashMapTest {
    FactsDaoInt factsDao;
    @Test
    public void list() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void load() throws Exception {

    }

    @Test
    public void test(){
        Fact f = factsDao.load(1);
        assertEquals(f.getAccount(),"кошелек.В");
    }

    @Test
    public void test01_07_2016() throws ParseException {
//01.07.2016
        SimpleDateFormat sdf =new SimpleDateFormat("dd.MM.yyyy");
        Date date = sdf.parse("01.07.2016");
        List<Fact> list = factsDao.list("Л", date, date);
        assertEquals(6,list.size());
    }

    @Test
    public void test01_07_2016_no_user() throws ParseException {
//01.07.2016
        SimpleDateFormat sdf =new SimpleDateFormat("dd.MM.yyyy");
        Date date = sdf.parse("01.07.2016");
        List<Fact> list = factsDao.list("ЛAAA", date, date);
        assertEquals(0,list.size());
    }



    @Before
    public void setUp() throws Exception {
        factsDao=new FactDaoHashMap();
        ReadTsvFacts.loadFactsFromTSVFile(factsDao);
    }

    @After
    public void tearDown() throws Exception {

    }

}