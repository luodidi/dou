package com.whut.dao;

import com.whut.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/14 15:16
 */
@Mapper
@Repository
public interface IUserDao {

   User getAllUser(int user_id);

}
