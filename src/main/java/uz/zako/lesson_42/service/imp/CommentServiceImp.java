package uz.zako.lesson_42.service.imp;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.zako.lesson_42.entity.entity.Admin;
import uz.zako.lesson_42.entity.entity.Comment;
import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.model.Result;
import uz.zako.lesson_42.service.AdminService;
import uz.zako.lesson_42.service.CommentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan
public class CommentServiceImp implements CommentService {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/login_register";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";

    @Override
    public List<Comment> getAllComment() {

        List<Comment> comments=new ArrayList<>();
        Comment comment=null;

        String sql = "select * from public.comment";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                comment=new Comment(
                        resultSet.getLong("id"),
                        resultSet.getString("comments"),
                        resultSet.getLong("chat_id"),
                        resultSet.getTimestamp("time"),
                        resultSet.getLong("reply_id")
                );
                comments.add(comment);
            }

            System.out.println(comments.toString()+"=keldi=");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public List<Comment> getAllIdComment() {

        List<Comment> comments=new ArrayList<>();
        Comment comment=null;

        String sql = "select * from public.comment";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                comment=new Comment(
                        resultSet.getLong("id"),
                        resultSet.getString("comments"),
                        resultSet.getLong("chat_id"),
                        resultSet.getTimestamp("time"),
                        resultSet.getLong("reply_id")
                );
                comments.add(comment);
            }

            System.out.println(comments.toString()+"=keldi=");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Result saveComment(Comment comment, Long number){
        int a=0;
        try{

            if (number==0) {
                String sql = "insert into public.comment(comments,time,chat_id) values(?,?,?)";

                a = jdbcTemplate.update(sql, new Object[]{comment.getComment(), comment.getTime(), comment.getUserId()});
            }else {
                String sql = "insert into public.comment(comments,time,chat_id,reply_id) values(?,?,?,?)";

                a = jdbcTemplate.update(sql, new Object[]{comment.getComment(), comment.getTime(), comment.getUserId(), comment.getReplyId()});
            }
            System.out.println("keldi");

            return a>0 ? new Result(true,"save"):new Result(false,"not save");

        }catch (Exception e){

            e.printStackTrace();
            return new Result(false, e.getMessage());

        }
    }

}
