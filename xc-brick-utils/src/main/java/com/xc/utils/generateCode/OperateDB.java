package com.xc.utils.generateCode;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OperateDB {
    public OperateDB() {
    }

    public static String getCreateSql(String url, String user, String password, String tableName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("show create table " + tableName);
            ResultSetMetaData metadata = result.getMetaData();
            List<String> metadataList = new ArrayList();
            System.out.println("====================表结构=============================");

            for(int i = 1; i <= metadata.getColumnCount(); ++i) {
                metadataList.add(metadata.getColumnName(i));
            }

            StringBuilder sqlStr = new StringBuilder();
            int size = 0;

            while(result.next()) {
                for(Iterator i = metadataList.iterator(); i.hasNext(); ++size) {
                    String oneKey = (String)i.next();
                    if (size != 0) {
                        sqlStr.append(result.getString(oneKey)).append(" ");
                    }
                }
            }

            System.out.println(sqlStr);
            result.close();
            statement.close();
            con.close();
            return sqlStr.toString();
        } catch (SQLException | ClassNotFoundException var13) {
            var13.printStackTrace();
            return "";
        }
    }
}
