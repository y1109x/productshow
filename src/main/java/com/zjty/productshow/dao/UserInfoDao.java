package com.zjty.productshow.dao;

import com.zjty.productshow.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {

}