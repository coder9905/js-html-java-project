package uz.zako.lesson_42.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.zako.lesson_42.entity.entity.Comment;
import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.model.Result;
import uz.zako.lesson_42.service.CabinetService;
import uz.zako.lesson_42.service.CommentService;
import uz.zako.lesson_42.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;


    @PostMapping("/addcomment")
    public String getSaveComment(HttpServletRequest req, HttpServletResponse resp, Model model){

       String SendText=req.getParameter("SendText");
       Long id= Long.valueOf(req.getParameter("sendId"));
       LocalDateTime dateTime=LocalDateTime.now();
       Timestamp data= Timestamp.valueOf(dateTime);

       Comment comment=new Comment(
               SendText,
               id,
               data
       );
     commentService.saveComment(comment, Long.valueOf(0));
     return "redirect:/api/auth/";
   };

    @PostMapping("/SaveReplyComment")
    public String SaveComment(HttpServletRequest req, HttpServletResponse resp, Model model,@RequestBody Comment comment){

        LocalDateTime dateTime=LocalDateTime.now();
        Timestamp data= Timestamp.valueOf(dateTime);

        Comment comment1=new Comment();
        comment1.setComment(comment.getComment());
        comment1.setUserId(comment.getUserId());
        comment1.setReplyId(comment.getReplyId());
        comment1.setTime(data);

        commentService.saveComment(comment1, Long.valueOf(1));
        return "redirect:/api/auth/";
    };

    @ResponseBody
    @GetMapping("/getReplyComment")
    public List<Comment> getReplyComment(){
        return commentService.getAllIdComment();
    };

    @PostMapping("/delete")
    public String deleteComment(HttpServletRequest req, HttpServletResponse resp, Model model){


        Long Id= Long.valueOf(req.getParameter("delete"));
        userService.deleteCommentId(Id);
        return "redirect:/api/auth/";
    };

    @PostMapping("/deleteUser")
    public String deleteCommentUSer(HttpServletRequest req, HttpServletResponse resp, Model model){

        Long Id= Long.valueOf(req.getParameter("delete"));
        userService.deleteUserId(Id);
        return "redirect:/api/auth/";
    };

    @PostMapping("/LogOut")
    public String LogOutUSer(HttpServletRequest req, HttpServletResponse resp, Model model){

        Long Id= Long.valueOf(req.getParameter("logOut"));
        System.out.println("logOutga keldi");
        User user=userService.getUserId(Id);

        if (user != null) {
            Cookie cookie = new Cookie("user", Base64.encodeBase64String(user.getEmail().getBytes()));
//        sekund
            cookie.setPath("/");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }

        return "redirect:/api/auth/";
    };

}
