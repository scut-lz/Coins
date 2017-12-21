package com.example.service;

import com.example.domain.Coins;
import org.springframework.stereotype.Service;

@Service
public interface CoinsService {
    int getCoinsByUserId(Integer userId);

    int addUser(int userId,int coins);

    boolean transfer(int inUserId,int outUserId,int coins);
}
