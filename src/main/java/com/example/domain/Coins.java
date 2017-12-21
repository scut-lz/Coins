package com.example.domain;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Coins.findByUserId", query = "select coins from Coins c where c.user_id=?1")
public class Coins extends JdbcDaoSupport {
    
    private static final long serialVersionUID = 1L;
    @Id
    long id;
    @Column(name = "user_id")
    int userId;

    @Column(name = "coins")
    int coins;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }


    public void setCoins(int coins) {
        this.coins = coins;
    }

    
    public int addUser(int userId,int coins){
        String sql = "insert into coins (user_id,coins) values(?,?)";
        return this.getJdbcTemplate().update(sql,userId,coins);
    }
    
    public int getCoins(int userId){
        String sql = "select coins from coins where user_id="+userId;
        return (Integer)this.getJdbcTemplate().queryForObject(sql,java.lang.Integer.class);
    }
    
    public int outCoins(int userId,int coins){
        String sql = "update coins set coins=coins-? where user_id=?";
        return this.getJdbcTemplate().update(sql,coins,userId);
    }

    
     public int inCoins(int userId,int coins){
         String sql = "update coins set coins=coins+? where user_id=?";
         return this.getJdbcTemplate().update(sql,coins,userId);
     }
}

