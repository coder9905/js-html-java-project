package uz.zako.lesson_42.entity.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Province {
    private Long id;
    private String name;
    private Date createdAt;
    private Date updateAt;
}
