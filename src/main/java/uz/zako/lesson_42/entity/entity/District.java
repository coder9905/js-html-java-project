package uz.zako.lesson_42.entity.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class District {
    private Long id;
    private String name;

    //    many to one - province ga
    private Long provinceId;

    private Date createdAt;
    private Date updateAt;

    public District(String name) {
        this.name=name;
    }
}
