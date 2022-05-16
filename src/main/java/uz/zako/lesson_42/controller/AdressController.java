package uz.zako.lesson_42.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zako.lesson_42.entity.entity.District;
import uz.zako.lesson_42.entity.entity.Province;
import uz.zako.lesson_42.service.DistrictService;
import uz.zako.lesson_42.service.ProvinceService;

import java.util.List;

@RestController
@RequestMapping("/api/adress")
public class AdressController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @GetMapping("/province")
    public List<Province> getAll(){
        return provinceService.getAllProvince();
    }

    @GetMapping("/district/{id}")
    public List<District> getAll(@PathVariable long id){
        return districtService.getAllByProvinceId(id);
    }
}
