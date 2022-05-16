package uz.zako.lesson_42.service.imp;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.zako.lesson_42.entity.entity.Admin;
import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.model.Result;
import uz.zako.lesson_42.service.AdminService;
import uz.zako.lesson_42.service.CabinetService;

import java.util.List;

@Service
@ComponentScan
public class AdminServiceImp implements AdminService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Admin getAllAdmin(){
        try{

            String sql="select * from public.admin";

            Admin admin = jdbcTemplate.queryForObject(sql, ((resultSet, i) -> new Admin(
                    resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            )));

            return admin;

        }catch (Exception e){

            e.getMessage();

        }
        return null;
    }


}
