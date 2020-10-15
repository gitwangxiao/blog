package com.wx.service;

import com.wx.dao.TypeDao;
import com.wx.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/14 15:18
 */

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional //事务注解
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public List<Type> getIndexType() {
        return typeDao.getIndexType();
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }
    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
