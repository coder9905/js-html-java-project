package uz.zako.lesson_42.service;

import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.model.Result;

public interface UserService {
    User getUserEmail(String email);

    Result deleteCommentId(Long id);

    Result deleteUserId(Long id);

    User getUserId(Long id);

    public User getUser(String email, String password);

    public boolean saveUser(User user);

    public User getByEmail(String email);

    Result delete(Long id);
}
