$(document).ready(function() {
     let users = $("#example").DataTable( {
        "ajax": {
            "url": "/api/user/all",
            "type": "get",
            "dataSrc": "",
        },
                "columns" : [
                    { "title" : "Id",
                        "data": "id" },
                    { "title" : "Email",
                        "data": "email" },
                    { "title" : "FullName",
                        "data": "fullName" },
                    { "title" : "Password",
                        "data": "password" },
                    { "title" : "Phone",
                        "data": "phone" },
                    { "title" : "Royhatdan otgan vaqti",
                        "data": "createdAt" },
                    { "title" : "O'zgartirilgan vaqti",
                        "data": "updatedAt" },
                    { "title" : "Age",
                        "data": "age" },
                    { "title" : "Adress",
                        "data": "districtId" },
                    {
                        data: "id",
                        render: function (data, r, c){
                            console.log(data);
                            return "\n" +
                                '<form action="/api/user/user/' + data + '" method="post">' +
                                "<button type=\"submit\" id=\"delete\" class=\"btn btn-danger\">Delete</button>\n" +
                                "</form>"
                        }
                    },
                    {defaultContent: "<button type=\"button\" class=\"btn btn-danger delete\" id=\"deleteId\" name=\"delete\">delete</button>"},
                    {defaultContent: "<button type=\"button\" class=\"btn btn-success btn-edit\" data-bs-toggle='modal' data-bs-target='#exampleModal'>edit</button>"}
                ]
    } )

    $(users.table().body()).on('click','.delete',function (){
        console.log("bu yerdaman")
        let cEl=users.row($(this).parents('tr')).data();
        $.ajax({
            url: "/api/user/user/" + cEl.id,
            type: "delete",
            error: (err) => {
                alert(err)
            },
            success: (data) => {
                alert(data.message)
                users.ajax.reload();
            }
        })
        // $("#deleteById").click(data => {
        //     console.log(data.target)
        // });
    });
    $("#deleteById").click(data => {
        console.log(data.target)
    });

    $(users.table().body()).on("click",".btn-edit",function () {
        var data = users.row($(this).parents('tr')).data();
        $("#id1a").val(data.age);
        $("#id").val(data.id);
        $("#id1").val(data.email);
        $("#id1f").val(data.fullName);
        $("#id1t").val(data.phone);
        $("#idp").val(data.password);
    })
    $("#edit-send").click(function (e) {
        console.log(e);
        var age = $("#id1a").val();
        var id =$("#id").val();
        var email =$("#id1").val();
        var phone =$("#id1t").val();
        var fullName =$("#id1f").val();
        var password =$("#idp").val();
        var confirPassword =$("#idc").val();
        let obj = {
            age,
            id,
            email,
            fullName,
            password,
            phone
        }
        console.log(obj)
        $.ajax({
            url:`/api/user/edit/${obj.id}`,
            data: JSON.stringify(obj),
            type:"post",
            contentType:"application/json; charset=utf-8",
            success : function (data) {
                console.log(data+"=data=");
                $('#data').DataTable().ajax.reload();
            }
        })
    })
} );



