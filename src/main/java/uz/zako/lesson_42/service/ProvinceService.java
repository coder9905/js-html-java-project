package uz.zako.lesson_42.service;

import uz.zako.lesson_42.entity.entity.Province;

import java.util.List;

public interface ProvinceService {
    public List<Province> getAllProvince();
    public Province getProvinceId(Long provinceId);
}
