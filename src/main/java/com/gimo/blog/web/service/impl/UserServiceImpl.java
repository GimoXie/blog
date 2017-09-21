package com.gimo.blog.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.core.generic.GenericServiceImpl;
import com.gimo.blog.web.dao.UserDao;
import com.gimo.blog.web.model.User;
import com.gimo.blog.web.service.UserService;

/**
 * 用户Service实现类
 *
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int insert(User model) {
        return userDao.insertSelective(model);
    }

    @Override
    public int update(User model) {
        return userDao.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(Long id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public User authentication(User user) {
        return userDao.authentication(user);
    }

    @Override
    public User selectById(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public GenericDao<User, Long> getDao() {
        return userDao;
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

	@Override
	public User selectByBloggerId(Long id) {
		return userDao.selectByBloggerId(id);
	}

}
