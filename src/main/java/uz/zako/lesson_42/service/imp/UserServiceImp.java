package uz.zako.lesson_42.service.imp;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.model.Result;
import uz.zako.lesson_42.service.UserService;

import java.sql.*;

@Service
@ComponentScan
public class UserServiceImp implements UserService {
    private static final String URL = "jdbc:postgresql://localhost:5432/login_register";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";
    private static final String DRIVER = "org.postgresql.Driver";

    @Override
    public User getUserEmail(String email) {

        User user=null;

        String sql = "select * from public.register where email=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Base64 base64 = new Base64();

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);

            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                user=new User(
                  resultSet.getLong("id"),
                  resultSet.getString("email"),
                  resultSet.getString("fullname"),
                  resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getLong("district_id"),
                        resultSet.getShort("age"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getTimestamp("update_at")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Result deleteCommentId(Long id) {

        String sql = "delete from public.comment where id=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Base64 base64 = new Base64();

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);


            return preparedStatement.executeUpdate()!=0?new Result(true,"delete"):new Result(false,"not delete");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result deleteUserId(Long id) {

        String sql = "delete from public.register where id=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Base64 base64 = new Base64();

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);


            return preparedStatement.executeUpdate()!=0?new Result(true,"delete"):new Result(false,"not delete");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserId(Long id) {

        User user=null;

        String sql = "select * from public.register where id=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Base64 base64 = new Base64();

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                user=new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("fullname"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getLong("district_id"),
                        resultSet.getShort("age"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getTimestamp("update_at")
                );
            }

            System.out.println(user.getFullName()+"userFullName");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User getUser(String email, String password){
        try{
            String sql="select * from public.register where email=?";
            User user=null;

            user=jdbcTemplate.queryForObject(sql,new Object[]{email},(rs,rowNum)->new User(
                    rs.getLong("id"),
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getLong("district_id"),
                    rs.getShort("age"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("update_at")
            ));
            if (user == null){
                return null;
            }
            if (password.equals(new String(Base64.decodeBase64(user.getPassword())))) {
                return user;
            }
            System.out.println(new String(Base64.decodeBase64(user.getPassword())));
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean saveUser(User user){
        boolean a=false;
        try{
            String sql="insert into public.register(email,fullname,password,age,phone,district_id) values(?,?,?,?,?,?)";
            user.setPassword((Base64.encodeBase64String(user.getPassword().getBytes())));
            a=jdbcTemplate.update(sql,new Object[]{user.getEmail(),user.getFullName(),user.getPassword(),user.getAge(),user.getPhone(),user.getDistrictId()}) != 0;

        }catch (Exception e){
            e.getMessage();
        }
        return a;
    }

    @Override
    public User getByEmail(String email) {

        System.out.println(email+"=keldi=");
        User user=null;
        try {
            String sql = "select * from public.register u where u.email=?";
            user = jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> new User(
              rs.getLong("id"),
              rs.getString("email"),
              rs.getString("fullname"),
              rs.getShort("age")
            ));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public Result delete(Long id) {
        try {
            String sql = "delete from register u where u.id=?";
            int r = jdbcTemplate.update(sql, new Object[]{id});

            return r > 0 ? new Result(true, "deleted") : new Result(false, "not deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }



}
