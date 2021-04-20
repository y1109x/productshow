package com.zjty.productshow.dao;

import com.zjty.productshow.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordDao extends JpaRepository<Record, Integer>, JpaSpecificationExecutor<Record> {

}
