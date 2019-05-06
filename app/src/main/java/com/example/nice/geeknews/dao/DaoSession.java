package com.example.nice.geeknews.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.nice.geeknews.bean.GirlDbBean;

import com.example.nice.geeknews.dao.GirlDbBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig girlDbBeanDaoConfig;

    private final GirlDbBeanDao girlDbBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        girlDbBeanDaoConfig = daoConfigMap.get(GirlDbBeanDao.class).clone();
        girlDbBeanDaoConfig.initIdentityScope(type);

        girlDbBeanDao = new GirlDbBeanDao(girlDbBeanDaoConfig, this);

        registerDao(GirlDbBean.class, girlDbBeanDao);
    }
    
    public void clear() {
        girlDbBeanDaoConfig.clearIdentityScope();
    }

    public GirlDbBeanDao getGirlDbBeanDao() {
        return girlDbBeanDao;
    }

}
