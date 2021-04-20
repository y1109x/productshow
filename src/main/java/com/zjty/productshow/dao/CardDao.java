package com.zjty.productshow.dao;

import com.zjty.productshow.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDao extends JpaRepository<Card, Integer>, JpaSpecificationExecutor<Card> {
    Card findByCardNo(String cardNo);

}
