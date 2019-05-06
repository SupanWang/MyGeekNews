package com.example.nice.geeknews.util;

import com.example.nice.geeknews.base.BaseApp;
import com.example.nice.geeknews.bean.GirlDbBean;
import com.example.nice.geeknews.dao.DaoMaster;
import com.example.nice.geeknews.dao.DaoSession;
import com.example.nice.geeknews.dao.GirlDbBeanDao;

import java.util.List;

public class DbUtils {

    private static DbUtils dbUtils;
    private final GirlDbBeanDao girlDbBeanDao;

    public DbUtils() {

        //1.
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getInstance(), "list.db");
        //2.
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        //3.
        DaoSession daoSession = daoMaster.newSession();
        //4.
        girlDbBeanDao = daoSession.getGirlDbBeanDao();
    }


    public static DbUtils getDbUtils() {
        if (dbUtils == null) {
            synchronized (DbUtils.class) {
                if (dbUtils == null) {
                    dbUtils = new DbUtils();
                }
            }
        }
        return dbUtils;
    }


    //插入所有
    public void insertAll(GirlDbBean dbBean) {
        if (has(dbBean)) {
            return;
        }
        girlDbBeanDao.insertOrReplace(dbBean);
    }

    //删除
    public void delete(GirlDbBean dbBean) {
        if (has(dbBean)) {
            girlDbBeanDao.delete(dbBean);
        }
    }

    //查询
    public List<GirlDbBean> query() {
        return girlDbBeanDao.queryBuilder().list();
    }

    //判断是否存在
    public boolean has(GirlDbBean dbBean) {
        List<GirlDbBean> list = girlDbBeanDao.queryBuilder().where(GirlDbBeanDao.Properties.Url.eq(dbBean.getUrl())).list();

        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }



}
