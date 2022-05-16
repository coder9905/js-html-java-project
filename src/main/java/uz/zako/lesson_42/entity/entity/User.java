package uz.zako.lesson_42.entity.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;


//@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {

    private Long id;
    private String email;
    private String fullName;

    public User(Long id, String fullName, String email, String password, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    private String password;
    private String phone;
    private Long districtId;
    private short age;
    private Date createdAt;
    private Date updatedAt;

    public User(Long id, String fullName, String email, String password, String phone, Long districtId, short age, Date createdAt, Date updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.districtId = districtId;
        this.age = age;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //    public User(Long id,String fullName, String email, String password, short age) {
//        this.id = id;
//        this.fullName = fullName;
//        this.email = email;
//        this.password = password;
//        this.age = age;
//    }
//
//    public User(String fullName, String email, String password, short age) {
//        this.fullName = fullName;
//        this.email = email;
//        this.password = password;
//        this.age = age;
//    }
//
    public User(String fullName, String email, String password, Long districtId, short age) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.districtId = districtId;
        this.age = age;
    }
    //
    public User(Long id) {
        this.id = id;
    }

    public User(String fullName, String email, String password, Long districtId, short age, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.districtId = districtId;
        this.age = age;
        this.phone=phone;
    }

    public User(long id, String email, String fullname, short age) {
        this.id=id;
        this.email=email;
        this.fullName=fullname;
        this.age=age;
    }

}
