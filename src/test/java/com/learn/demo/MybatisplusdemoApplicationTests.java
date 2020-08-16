package com.learn.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.demo.entity.User;
import com.learn.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.learn.demo.mapper")
@Slf4j
class MybatisplusdemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testList(){
        List<User> users = userService.selectList();
        users.forEach(user -> {log.info(user.toString());});
    }

    @Test
    void testInsert(){
        User user = new User();
        user.setName("李四");
        user.setSex(1);
        user.setAge(22);
        user.setStatus(1);
        int insert = userService.insert(user);
        if(1 == insert){
            log.info("插入成功：user="+user.toString());
        }else {
            log.error("插入失败：user="+user.toString());
        }
    }

    @Test
    void testDelete(){
        User user = new User();
        user.setName("张三");
        user.setAge(22);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        int delete = userService.delete(wrapper);
        if(1 == delete){
            log.info("删除成功：user="+user.toString());
        }else {
            log.error("删除失败：user="+user.toString());
        }
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setAge(22);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        User updateUser = new User();
        updateUser.setName("张三");
        updateUser.setSex(1);
        wrapper.setEntity(updateUser);
        int update = userService.update(user, wrapper);
        if(1 == update){
            log.info("更新成功：user={}", user.toString());
        }else {
            log.error("更新失败：user={}", user.toString());
        }
    }

    @Test
    void testPage(){
        Page<User> page = userService.selectPage(new Page<>(1, 2), null);
        List<User> records = page.getRecords();
        records.forEach(user -> log.info(user.toString()));
        long total = page.getTotal();
        log.info("总数：{}",total);
    }
}
