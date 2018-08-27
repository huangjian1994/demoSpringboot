package com.example.demo.repository;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author by hj on 2018-2-8.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 根据名字查或者用户名
     * @param name
     * @return
     */
    List<User> findByNameLike(String name);

    /**
     * 删除
     */
    @Transactional
    void removeUserById(Integer id);

    @Override
    User findOne(Integer id);

    @Query(value = "select u.* from user u where u.name =?1 or u.username = ?1",nativeQuery=true)
    @Modifying
    List<User> findUserByNameOrUsername(String search);


    @Transactional
    @Modifying
    @Query("update User as c set c.name = ?1,c.username=?2,c.age=?3,c.password=?4 where c.id=?5")
    int updateNameById(String name,String username,String age,String password, int id);
}
