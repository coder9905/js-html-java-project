package uz.zako.lesson_42.service.imp;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.model.Result;
import uz.zako.lesson_42.service.CabinetService;

import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan
public class CabinetServiceImp implements CabinetService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUser(){
        try{

            String sql="select * from public.register";

            List<User> users=new ArrayList<>();

            users = jdbcTemplate.query(sql, ((resultSet, i) -> new User(
                    resultSet.getLong("id"),
                    resultSet.getString("fullname"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("phone"),
                    resultSet.getLong("district_id"),
                    resultSet.getShort("age"),
                    resultSet.getTimestamp("created_at"),
                    resultSet.getTimestamp("update_at")
            )));

            return users;

        }catch (Exception e){

            e.getMessage();

        }
        return null;
    }

//    @Override
//    public User getUser(Long id){
//        User user=new User();
//        try{
//
//            String sql="select * from public.register where id="+id;
//
//            user = jdbcTemplate.queryForObject(sql, ((resultSet, i) -> new User(
//                    resultSet.getLong("id"),
//                    resultSet.getString("fullname"),
//                    resultSet.getString("email"),
//                    resultSet.getString("password"),
//                    resultSet.getString("phone"),
//                    resultSet.getLong("district_id"),
//                    resultSet.getShort("age"),
//                    resultSet.getTimestamp("created_at"),
//                    resultSet.getTimestamp("update_at")
//            )));
//
//            return user;
//
//        }catch (Exception e){
//
//            e.getMessage();
//
//        }
//        return null;
//    }

    @Override
    public Result UpdateUser(User user){
        try{

            String sql="update public.register set email=?, password=?, fullname=?, age=?, phone=?, district_id=? where id=?";
            System.out.println("===keldi===");

            user.setPassword(Base64.encodeBase64String(user.getPassword().getBytes()));
            int a=jdbcTemplate.update(sql, new Object[]{user.getEmail(),user.getPassword(),user.getFullName(),user.getAge(),user.getPhone(),user.getDistrictId(),user.getId()});


            return a > 0 ? new Result(true, "succesfull update") : new Result(false, "not update");



        }catch (Exception e){

            e.getMessage();

        }
        return null;
    }

    @Override
    public User getUserId(Long id){
        User user=null;
        try{

            String sql="select * from public.register where id=?";

            user=jdbcTemplate.queryForObject(sql, new Object[]{id},((resultSet,i)->new User(
                    resultSet.getLong("id"),
                    resultSet.getString("fullname"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("phone"),
                    resultSet.getLong("district_id"),
                    resultSet.getShort("age"),
                    resultSet.getTimestamp("created_at"),
                    resultSet.getTimestamp("update_at")
            )));



        }catch (Exception e){

            e.getMessage();

        }
        return user;
    }

}
