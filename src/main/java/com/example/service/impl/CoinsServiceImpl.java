package com.example.service.impl;

import com.example.domain.Coins;
import com.example.service.CoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CoinsServiceImpl implements CoinsService {

    private Coins coins;

    @Autowired
    public void setCoins(Coins coins) {
        this.coins = coins;
    }

    public int addUser(int userId,int coins){
        return this.coins.addUser(userId,coins);
    }

    public int getCoinsByUserId(Integer userId) {
        return coins.getCoins(userId);
    }

    public boolean transfer(int inUserId,int outUserId,int coins){
        return  this.coins.outCoins(outUserId,coins)>0 && this.coins.inCoins(inUserId, coins)>0;
    }

}
