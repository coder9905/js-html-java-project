package uz.zako.lesson_42.entity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    private Long Id;
    private String comment;
    private Long userId;
    private Timestamp Time;
    private Long replyId;

    public Comment(Long Id,String comment, Long userId, Timestamp time) {
        this.Id=Id;
        this.comment = comment;
        this.userId = userId;
        Time = time;
    }
    public Comment(String comment, Long userId, Timestamp time) {
        this.comment = comment;
        this.userId = userId;
        Time = time;
    }
    public Comment(String comment, Long userId, Timestamp time,Long replyId) {
        this.comment = comment;
        this.userId = userId;
        Time = time;
        this.replyId=replyId;
    }

    public Comment(Long Id) {
        this.Id=Id;
    }
}
