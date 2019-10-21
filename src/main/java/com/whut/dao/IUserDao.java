package com.whut.dao;

import com.whut.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/14 15:16
 */
@Mapper
@Repository
public interface IUserDao {

   public  User getAllUser(int user_id);

   public Map<String,Object> login(@Param("id") Integer id,@Param("password") String password);

   //显示用户的具体信息
   //public int getUserInfo(int id);

   public List<Map<String, Object>> getListUser();

   // 添加用户
   public int insertUser(User user);

   // 修该用户
   public  int updateUserById(User user);

   // 删除用户
   public  int deleteUser(Integer id);


}
