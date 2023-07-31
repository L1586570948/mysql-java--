package com.Supermar.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidUtils {
    //Druid德鲁伊,据说是魔兽世界中的一个角色,森林女神
    public static DruidDataSource dataSource;

    //1.初始化Druid连接池
    static {
        //第二种方式:使用软编码通过配置文件初始化DBCP
        try {
            Properties properties = new Properties();
            //通过类加载器加载配置文件
            InputStream inputStream = DruidUtils.class.getClassLoader().
                    getResourceAsStream("com/Supermar/utils/druid.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource(){
        return  dataSource;
    }
    //关闭连接
    public static void closeAll(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stat!= null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
