<%@ page import="uz.zako.lesson_42.service.UserService" %>
<%@ page import="uz.zako.lesson_42.service.imp.UserServiceImp" %>
<%@ page import="uz.zako.lesson_42.entity.entity.User" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.zako.lesson_42.entity.entity.Comment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uz.zako.lesson_42.service.CommentService" %>
<%@ page import="uz.zako.lesson_42.service.imp.CommentServiceImp" %>
<%@ page import="uz.zako.lesson_42.service.CabinetService" %>
<%@ page import="uz.zako.lesson_42.service.imp.CabinetServiceImp" %><%--
  Created by IntelliJ IDEA.
  User: Baxtiyor
  Date: 3/27/2022
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>

<%
    UserService userService=new UserServiceImp();
    Cookie[] cookie=request.getCookies();
    String email=null;
    if (cookie != null){
        for (int i = 0; i < cookie.length; i++) {
            if (cookie[i].getName().equals("user")) {
                email = new String(org.apache.commons.codec.binary.Base64.decodeBase64(cookie[i].getValue()));
            }
        }
    }
    User user=null;
    if (email != null){
        user=userService.getUserEmail(email);
    }
%>
<h2 style="text-align: center">Cabinet :)</h2>
<h3>Id: <%=user!=null?user.getId():"Kelmadi"%></h3>
<h3>FullName: <%=user!=null?user.getEmail():""%></h3>


<div class="container">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th >Id</th>
            <th >Email</th>
            <th >FullName</th>
            <th >Yoshi</th>
            <th >Phone</th>
            <th >Password</th>
            <th >CreatedAt</th>
            <th >UpdatedAt</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td ><%=user.getId()%></td>
            <td ><%=user.getFullName()%></td>
            <td ><%=user.getEmail()%></td>
            <td ><%=user.getAge()%></td>
            <td ><%=user.getPhone()%></td>
            <td ><%=user.getPassword()%></td>
            <td ><%=user.getCreatedAt()%></td>
            <td ><%=user.getUpdatedAt()%></td>
            <form action="/api/comment/deleteUser" method="post">
                <input type="hidden" name="delete" value="<%=user.getId()%>">
                <td ><button type="submit" id="delete" class="btn btn-danger">delete</button></td>
            </form>
            <td ><button type="submit" value="<%=user.getId()%>" id="editUser" class="btn btn-primary">edit</button></td>
            <form action="/api/comment/LogOut" method="post">
                <input  type="hidden" value="<%=user.getId()%>" name="logOut">
                <td ><button type="submit" id="LogOut" class="btn btn-success">LogOut</button></td>
            </form>
        </tr>
        </tbody>
    </table>

    <div class="row border border-dark">
        <%
            CommentService commentService=new CommentServiceImp();
            List<Comment> comments=new ArrayList<>();
            comments=commentService.getAllComment();
            if (comments != null){
            for (int i = 0; i < comments.size(); i++) {
                if (comments.get(i).getUserId() != user.getId()){
                    User user1=userService.getUserId(comments.get(i).getUserId());
                    %>
        <div class="row" id="replyIconca">
        <div class="col-6 p-3 mb-2 bg-primary text-white">
            <%=comments.get(i).getReplyId()!=0?("Reply_Comment=> Id:"+comments.get(i).getReplyId()+"ga UserId:"+comments.get(i).getUserId()+" dan"):("ID: "+comments.get(i).getId())%>
            <br>
            Name: <%=user1.getEmail()%>
            <br>
            <%=comments.get(i).getComment()%>
        </div>
        <div class="col-2 p-3 mb-2 bg-primary text-white"><%=comments.get(i).getTime()%></div>
        <div class="col-1 p-3 mb-2 bg-primary text-white">
            <button class="replyId" value="<%=comments.get(i).getId()%>" id="replyId" type="submit"><i class="fas fa-reply-all"></i></button>
            <form action="/api/comment/delete" method="post">
                <input type="hidden" name="delete" value="<%=comments.get(i).getId()%>">
                <button type="submit" value="<%=comments.get(i).getId()%>"><i class="fa-solid fa-trash-can"></i></button>
            </form>
        </div>
        <div class="col-3"></div>
        </div>
        <%
                }else{
                    %>
        <div class="col-3"></div>
        <div class="col-6 p-3 mb-2 bg-success text-white">
            <%=comments.get(i).getReplyId()!=0?("Reply_Comment=> Id:"+comments.get(i).getReplyId()+"ga UserId:"+comments.get(i).getUserId()+" dan"):("ID: "+comments.get(i).getId())%>
            <br>
            Name: <%=user.getEmail()%>
            <br>
            <%=comments.get(i).getComment()%>
        </div>
        <div class="col-2 p-3 mb-2 bg-success text-white"><%=comments.get(i).getTime()%></div>
        <div class="col-1 p-3 mb-2 bg-success text-white">
            <form action="/api/comment/delete" method="post">
                <input type="hidden" name="delete" value="<%=comments.get(i).getId()%>">
                <button type="submit" value="<%=comments.get(i).getId()%>"><i class="fa-solid fa-trash-can"></i></button>
            </form>
        </div>
        <%
                }
            }}
        %>
        <form action="/api/comment/addcomment" method="post">
            <label for="sendText"></label>
            <textarea name="SendText" class="form-control" id="sendText" rows="3"></textarea>
            <button name="sendId" type="submit" value="<%=user.getId()%>" ><i class="fa-solid fa-paper-plane"></i></button>
        </form>
    </div>

</div>

<%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@fat">Open modal for @fat</button>--%>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name"  class="col-form-label">UserId</label>
                        <input type="text" id="userId" value="<%=user.getId()%>" placeholder="<%=user.getId()%>" class="form-control" id="recipient-name">
                    </div>
                    <div class="form-group">
                        <label for="messageReply" class="col-form-label">ReplyComment</label>
                        <textarea class="form-control" id="messageReply"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="togleModal()" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="reply_send" onclick="togleModal()">Send message</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel1">New message</h5>
            </div>
            <div class="modal-body">
                <input id="email" name="email" class="d-block" width="100%" type="email" placeholder="email">
                <input id="fullname" name="fullName" class="d-block" width="100%" type="text" placeholder="fullName">
                <input id="phone" name="phone" class="d-block" width="100%" type="text" placeholder="phone">
                <input id="age" name="age" class="d-block" width="100%" type="number" placeholder="age">
                <%--            <span class="text-danger">${isPasswordEqual}</span>--%>
                <input id="password" name="password" class="d-block" width="100%" type="password" placeholder="password">
                <input name="rePassword" class="d-block" width="100%" type="password" placeholder="rewrite pass">
                <select name="provinceId" id="province" class="form-select w-100"
                        title="Viloyat Tanlang">
                    <option id="provinceId" selected disabled>Viloyat Tanlang</option>
                </select>
                <select name="regionId" id="district" class="form-select w-100" title="Tuman Tanlang">
                    <option id="districtId" selected disabled>Tuman Tanlang</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="togleModal1()" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="editUserComment">Send</button>
            </div>
        </div>
    </div>
</div>



</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>
<script src="../../../assets/js/comment.js"></script>
<script src="../../../assets/js/adress.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
</html>
