package com.kusofan.seeweather.module.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.kusofan.seeweather.R;
import com.kusofan.seeweather.base.BaseApplication;
import com.kusofan.seeweather.common.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by heming on 2018/1/29.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "china_city.db";
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void close() {
        super.close();
        DaoManager.clearCache();
    }

    public <D extends Dao<T, ?>, T> D getCityDao(Class<T> clazz) {
        try {
            return getDao(clazz);
        } catch (SQLException e) {
            LogUtil.e(e.getMessage());
        }
        return null;
    }

    /**
     * 导入城市数据库
     */
    public static void importCityDB() {

        // 判断保持城市的数据库文件是否存在
        File file = new File(BaseApplication.getAppContext().getDatabasePath(DB_NAME).getAbsolutePath());
        // 如果不存在，则导入数据库文件
        if (!file.exists()) {
            //数据库文件
            File dbFile = BaseApplication.getAppContext().getDatabasePath(DB_NAME);
            try {
                if (!dbFile.getParentFile().exists()) {
                    dbFile.getParentFile().mkdir();
                }
                if (!dbFile.exists()) {
                    dbFile.createNewFile();
                }
                //加载欲导入的数据库
                InputStream is = BaseApplication.getAppContext().getResources().openRawResource(R.raw.china_city);
                FileOutputStream fos = new FileOutputStream(dbFile);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                fos.write(buffer);
                is.close();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
