<%@ page import="uz.zako.lesson_42.entity.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
</head>
<body>
<div class="container">

    <div class="row d-flex justify-content-end align-items-end">
        <!-- Button trigger modal -->
        <div class="col-2">
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Add User
            </button>
        </div>

        <!-- Modal -->
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
<%--                    <h5 class="modal-title" id="exampleModalLabel">Add User</h5>--%>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="col-12">
                        <form class="form" >
                            <label for="id1">Email</label>
                            <input id="id1" name="email" class="form-control" type="email" placeholder="Email" >
                            <input id="id" name="id" class="form-control" type="hidden" placeholder="" >
                            <label for="id1a">Age</label>
                            <input id="id1a" name="age" class="form-control" type="number" placeholder="Age" >
                            <label for="id1f">Fullname</label>
                            <input id="id1f" name="fullname" class="form-control" type="text" placeholder="Fullname">
                            <label for="id1t">Phone</label>
                            <input id="id1t" name="phone" class="form-control" type="text" placeholder="phone">
                            <label for="idp">Password</label>
                            <input id="idp" name="password" class="form-control" type="password" placeholder="Password"
                            >
                            <label for="idc">Confirm Password</label>
                            <input id="idc" name="confirm_password" class="form-control" type="password"
                                   placeholder="Confirm Password">
                            <select name="provinceId" id="province" class="form-select w-100"
                                title="Viloyat Tanlang">
                            <option selected disabled>Viloyat Tanlang</option>
                            </select>
                            <select name="regionId" id="district" class="form-select w-100" title="Tuman Tanlang">
                                    <option selected disabled>Tuman Tanlang</option>
                            </select>
                        </form>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="edit-send" data-bs-dismiss="modal">Save</button>
                </div>
            </div>
        </div>
    </div>
</div>

<table id="example" class="display nowrap" style="width:100%"></table>

</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="./../../../assets/js/cabinet.js" type="text/javascript"/>
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
