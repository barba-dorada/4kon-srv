package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by admin on 27.07.2016.
 */
public class Fact {
    Integer id;
    String user;
    Date date;
    String account;
    String subconto;
    BigDecimal amount;
    String comment;

    public Fact() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSubconto() {
        return subconto;
    }

    public void setSubconto(String subconto) {
        this.subconto = subconto;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Fact(Integer id, String user, Date date, String account, String subconto, BigDecimal amount, String comment) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.account = account;
        this.subconto = subconto;
        this.amount = amount;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", date=" + date +
                ", account='" + account + '\'' +
                ", subconto='" + subconto + '\'' +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                '}';
    }
}
