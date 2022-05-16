<%--
  Created by IntelliJ IDEA.
  User: Orifjon Yunusjonov
  Date: 10.03.2022
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<section class="container ">
    <h3>
        Register*
    </h3>
    <div class="bg-light d-flex mt-4 align-items-center mx-auto justify-content-center ">
        <form action="/api/auth/register" width="170px" heigth="300px" method="POST">

<%--            <span class="text-danger">${error}</span>--%>
            <input name="email" class="d-block" width="100%" type="email" placeholder="email">
            <input name="fullName" class="d-block" width="100%" type="text" placeholder="fullName">
             <input name="phone" class="d-block" width="100%" type="text" placeholder="phone">
              <input name="age" class="d-block" width="100%" type="number" placeholder="age">
<%--            <span class="text-danger">${isPasswordEqual}</span>--%>
            <input name="password" class="d-block" width="100%" type="password" placeholder="password">
            <input name="rePassword" class="d-block" width="100%" type="password" placeholder="rewrite pass">
            <select name="provinceId" id="province" class="form-select w-100"
                    title="Viloyat Tanlang">
                <option selected disabled>Viloyat Tanlang</option>
            </select>
            <select name="regionId" id="district" class="form-select w-100" title="Tuman Tanlang">
                <option selected disabled>Tuman Tanlang</option>
            </select>
            <button width="100%" type="submit" class="w-100 btn btn-success">Register</button>
            <a href="/api/auth/login">Login</a>
        </form>
    </div>

</section>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>
<script src="./../../../assets/js/adress.js" type="text/javascript"/>
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
