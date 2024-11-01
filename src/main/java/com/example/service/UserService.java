package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    /**
     * 新增&修改
     * @param user
     */
    public void save(User user) {
        userDao.save(user);
    }

    /**
     * 删除
     * @param id
     */
    public void del(Long id) {
        userDao.deleteById(id);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User findById(Long id){
        return userDao.findById(id).orElse(null);
    }

    /**
     * 分页查询用户
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    public Page<User> findPage(Integer pageNum, Integer pageSize, String name){
        Sort sort = Sort.by(Sort.Direction.DESC, "create_time");
        PageRequest request = PageRequest.of(pageNum - 1, pageSize, sort);
        return  userDao.findByNameLike(name, request);
    }
}
