package com.sample.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MySqlDb {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySqlDb mySqlDb = new MySqlDb();
        mySqlDb.outputMetaData();
    }

    public void outputMetaData() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gms_center", "root", "qwe@123zxc");
        PreparedStatement ps = conn.prepareStatement("SELECT id,school_name,school_code FROM school");
        ResultSet rs = ps.executeQuery();

        // 获取表的元数据（通过ResultSetMetaData我们可以拿到该表的表结构,每个字段的类型，是否可为空值等）
        ResultSetMetaData rsm = ps.getMetaData();
        for (int i = 1; i < rsm.getColumnCount(); i++) {
            System.out.println(rsm.getColumnLabel(i));
            System.out.println(rsm.getColumnName(i));
            System.out.println(rsm.getColumnTypeName(i));
            System.out.println(rsm.getColumnType(i));
            System.out.println("-------------------------------------------------------------------");
        }
        // 获取执行结果元数据（可以在不知道列名的情况下，盲取该表的所有数据）
        while (rs.next()) {
            for (int i = 1; i < rsm.getColumnCount(); i++) {
                System.out.println("列名：" + rsm.getColumnLabel(i) + "\t列值：" + rs.getObject(i));
            }
        }
    }
}
