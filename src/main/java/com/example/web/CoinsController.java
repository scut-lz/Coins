package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.service.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class CoinsController {
    @Autowired
    CoinsService coinsService;

    @Autowired
    public void setCoinsService(CoinsService coinsService) {
        this.coinsService = coinsService;
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestParam("user_id") Integer userId,
            @RequestParam("coins") Integer coins) {
        if (coinsService.addUser(userId, coins) > 0)
            return new ResponseEntity<String>("Success!", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Fail!", HttpStatus.OK);
    }

    @RequestMapping(value = "/coins/user/{userId}", method = RequestMethod.GET)
    public @ResponseBody int getCoins(@PathVariable("userId") int userId) {
        int coins = coinsService.getCoinsByUserId(userId);
        return coins;
    }

    @RequestMapping(value = "/transaction/transfer", method = RequestMethod.POST)
    public ResponseEntity<String> transfer(@RequestParam("from_user_id") int fromUserId,
            @RequestParam("to_user_id") int toUserId, @RequestParam("coins") int coins) {
        if (coinsService.transfer(toUserId, fromUserId, coins))
            return new ResponseEntity<String>("Success!", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Fail!", HttpStatus.OK);
    }

    @RequestMapping(value = "/ops/jstack", method = RequestMethod.GET)
    public ResponseEntity<String> jstack() throws IOException {
        String[] name = ManagementFactory.getRuntimeMXBean().getName().split("@");
        String pid = name[0];
        Process process = Runtime.getRuntime().exec("jstack -l " + pid);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }
        int exitVal = 0;
        try {
            exitVal = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(stringBuffer.toString(), HttpStatus.OK);
    }

}
