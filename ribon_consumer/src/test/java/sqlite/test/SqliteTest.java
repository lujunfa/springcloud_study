package sqlite.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lujunfa
 * @Date: 2018/11/20 20:15
 * @Description:
 */
class User{
    private String name;

    private int age;

    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "name:"+name+" age："+age+"gender："+gender;
    }
}

public class SqliteTest {

    public static void main(String[] args) {
        try {
            SqliteHelper helper = new SqliteHelper();

            helper.executeUpdate("create table tbl1(name varchar(20), salary int);",
                           "insert into tbl1values('ZhangSan',8000);",
                    "insert into tbl1values('LiSi',7800);",
                    "insert into tbl1values('WangWu',5800);");

            List<User> userList =  helper.executeQuery("select * from user", (ResultSet rs)->{
                List<User> userList1 = new ArrayList<>();
                try {
                    while(!rs.isLast()){
                        User user = new User();
                        user.setName(rs.getString("name"));
                        user.setAge(rs.getInt("age"));
                        user.setGender(rs.getString("gender"));
                        userList1.add(user);
                    }
                }catch (Exception e){
                    System.out.println("解析数据出错");
                }
                return userList1;
            } );
            userList.forEach(user ->{
                System.out.println(user);
            });

        }catch (Exception e){

        }
    }

    private static class SqliteHelper{
        private ResultSet resultSet;
        private Statement statement;
        private Connection connection;

        public SqliteHelper() throws SQLException, ClassNotFoundException {
            getConnection();
        }

        public Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lujunfa.db");
            return connection;
        }


        public <T> T executeQuery(String sql, ResultSetExtractor<T> rse) {
            try {
                resultSet = getStatement().executeQuery(sql);
                T rs = rse.extractData(resultSet);
                return rs;
            }catch (Exception e){
                System.out.println(e);
            }finally {
                destroyed();
                return null;
            }
        }

        /**
         * 执行多个sql更新语句
         * @param sqls
         * @throws SQLException
         * @throws ClassNotFoundException
         */
        public void executeUpdate(String...sqls) throws SQLException, ClassNotFoundException {
            try {
                for (String sql : sqls) {
                    getStatement().executeUpdate(sql);
                }
            } finally {
                destroyed();
            }
        }

        private Statement getStatement() throws SQLException, ClassNotFoundException {
            if (null == statement) statement = getConnection().createStatement();
            return statement;
        }
        /**
         * 数据库资源关闭和释放
         */
        public void destroyed() {
            try {
                if (null != connection) {
                    connection.close();
                    connection = null;
                }

                if (null != statement) {
                    statement.close();
                    statement = null;
                }

                if (null != resultSet) {
                    resultSet.close();
                    resultSet = null;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
