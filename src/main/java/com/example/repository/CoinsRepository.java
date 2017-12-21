package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.domain.Coins;

public interface CoinsRepository extends Repository<Coins, Long>
{

    
    @Query(value = "from Coins c where c.user_id=:ids")
    List<Coins> findByUserId(@Param("ids") int ids);

}
