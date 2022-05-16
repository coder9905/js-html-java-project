package uz.zako.lesson_42.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.zako.lesson_42.entity.entity.District;
import uz.zako.lesson_42.service.DistrictService;

import java.util.List;

@Service
@ComponentScan
public class DistrictServiceImp implements DistrictService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<District> getAllByProvinceId(Long provinceId) {
        try {
            String sql = "select * from public.district where province_id=" + provinceId;
            List<District> list = null;
            list = jdbcTemplate.query(sql, ((resultSet, i) -> new District(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("province_id"),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("update_at")

            )));
            System.out.println(list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public District getRegionId(Long provinceId) {
        try {
            String sql = "select * from public.district where d.id="+provinceId;
            return jdbcTemplate.queryForObject(sql,((resultSet, i) -> new District(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("province_id"),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("update_at")
            )));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
