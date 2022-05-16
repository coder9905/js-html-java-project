package uz.zako.lesson_42.service;

import uz.zako.lesson_42.entity.entity.District;

import java.util.List;

public interface DistrictService {
    public List<District> getAllByProvinceId(Long provinceId);
    public District getRegionId(Long provinceId);
}
