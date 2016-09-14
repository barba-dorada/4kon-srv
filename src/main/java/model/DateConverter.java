package model;

import org.jooq.Converter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by admin on 14.09.2016.
 */
public class DateConverter implements Converter<Date, LocalDateTime> {
    @Override
    public LocalDateTime from(Date databaseObject) {
        if (databaseObject == null) {
            return null;
        }
        LocalDateTime ldt = LocalDateTime.ofInstant(databaseObject.toInstant(), ZoneId.systemDefault());
        return ldt;
    }

    @Override
    public Date to(LocalDateTime userObject) {
        if(userObject==null){
            return null;
        }
        Date result = new Date(userObject.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        return result;
    }

    @Override
    public Class<Date> fromType() {
        return Date.class;
    }

    @Override
    public Class<LocalDateTime> toType() {
        return LocalDateTime.class;
    }
}
