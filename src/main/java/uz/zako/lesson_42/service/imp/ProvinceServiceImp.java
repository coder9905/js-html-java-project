package uz.zako.lesson_42.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.zako.lesson_42.entity.entity.District;
import uz.zako.lesson_42.entity.entity.Province;
import uz.zako.lesson_42.service.ProvinceService;

import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan
public class ProvinceServiceImp implements ProvinceService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Province> getAllProvince(){
        String sql="select * from public.province";
        try{

            List<Province> list=null;

            list=jdbcTemplate.query(sql,((resultSet,i)-> new Province(
                resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getTimestamp("created_at"),
                    resultSet.getTimestamp("update_at")
            )));
            return list;

        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public Province getProvinceId(Long provinceId) {
        try {
            String sql = "select * from public.province where d.id="+provinceId;
            return jdbcTemplate.queryForObject(sql,((resultSet, i) -> new Province(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("update_at")
            )));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}
