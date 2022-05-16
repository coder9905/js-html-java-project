package uz.zako.lesson_42.service;

import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.model.Result;

import java.util.List;

public interface CabinetService {
    public List<User> getAllUser();

//    User getUser(Long id);

    Result UpdateUser(User user);

    User getUserId(Long id);
}
