package tech.minesoft.mine.spider.impl.saver;

import tech.minesoft.mine.spider.core.component.SpiderSaver;
import tech.minesoft.mine.spider.core.utils.Content;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.Date;
import java.util.*;

@Slf4j
public class MySqlSaver implements SpiderSaver {

    private String url;
    private String username;
    private String password;
    private String tableName;
    private Map<String, String> content2db;

    public MySqlSaver(String url, String username, String password, String tableName, Map<String, String> content2db) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.tableName = tableName;
        this.content2db = content2db;
    }

    @Override
    public void saveContent(Content content) {
        List<String> names = new ArrayList<>();
        List<String> places = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (String key : content2db.keySet()) {
            String columnName = content2db.get(key);

            names.add(columnName);
            places.add("?");
            values.add(content.get(key));
        }

        String cols = StringUtils.join(names, ",");
        String plas = StringUtils.join(places, ",");

        String sql = " INSERT INTO " + tableName +
                " ( " + cols + " ) " +
                " VALUES " +
                " ( " + plas + " ) ";

        runUpdate(sql, values);

    }

    public void runUpdate(String sql, List<Object> values) {
        try {
            //加载MySql的驱动类
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DriverManager.getConnection(url, username, password);
            stmt = con.prepareStatement(sql);

            int index = 1;
            for (Object value : values) {
                stmt.setObject(index++, value);
            }

            stmt.executeUpdate();

        } catch (SQLException se) {
            System.out.println("update 数据库连接失败");
            System.out.println(sql);
            se.printStackTrace();
        } finally {
            close(con, stmt);
        }
    }

    private void close(Connection con, Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        Map<String, String> db = new HashMap<>();
        db.put("id", "agent_id");
        db.put("account", "agent_account");
        db.put("amount", "amount");
        db.put("time", "create_time");

        MySqlSaver sqlSaver = new MySqlSaver(
                "jdbc:mysql://154.208.76.217:13066/gamestone?useSSL=false",
                "root",
                "z7UBmXk2fii4V0Bj",
                "test_log",
                db
        );

        Content content = new Content();

        content.put("id", "1");
        content.put("account", "name");
        content.put("amount", "10.1");
        content.put("time", new Date());
    }
}
