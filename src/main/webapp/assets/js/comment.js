// function comment() {
//     $.ajax({
//         url: "/api/comment/getReplyComment",
//         type: "get",
//         contentType: "application/json; charset=utf-8",
//         success: function (data) {
//             for (let a in data) {
//                 let b=data[a].id;
//                 // console.log(b)
//                 // console.log(($("#"+b)))
//                 // let result = (($("#"+b))).substring(7, 3);
//                 // console.log(result)
//                 // if (result==("#"+b)){
//                 //     console.log("oxwadi")
//                 // }
//                 if (b == $("#b").val()){
//                     $("#" + a).on("click", function (el) {
//                         var a = $(".replyId").val();
//                         console.log(a);
//                         $("#exampleModal").modal("show");
//                     })
//                     $("#reply_send").on("click", function (e) {
//                         var userId = $("#userId").val();
//                         var comment = $("#messageReply").val();
//                         let obj = {
//                             userId,
//                             comment
//                         }
//                         console.log(obj);
//
//                         $.ajax({
//                             url: "/api/comment/SaveReplyComment",
//                             data: JSON.stringify(obj),
//                             type: "post",
//                             contentType: "application/json; charset=utf-8",
//                             success: function (data) {
//                                 console.log(data);
//                                 alert(data);
//                             }
//                         })
//                     })
//                 }
//             }
//         }
//     })
// }
$(".replyId").on("click", function (el){
    let data=$(".replyId").val()
    $("#exampleModal").modal("show");
    console.log($("#replyId").val())
})
$("#reply_send").on("click", function (e){
                var userId=$("#userId").val();
                var comment=$("#messageReply").val();
                var replyId=$("#replyId").val()
                let obj={
                    userId,
                    comment,
                    replyId
                }
                console.log(obj);
                $.ajax({
                    url: "/api/comment/SaveReplyComment",
                    data: JSON.stringify(obj),
                    type: "post",
                    contentType:"application/json; charset=utf-8",
                    succes: function (data) {
                        alert("zor");
                        ('data').ajax.reload()
                    }
                })
})

$("#editUser").on("click", function (){
    let id=$("#editUser").val()
    $("#exampleModal1").modal("show");
    $.ajax({
        url: "/api/user/all/"+id,
        type: "get",
        contentType: "application/json",
        success: function (data){
            $("#email").val(data.email)
            $("#fullname").val(data.fullName)
            $("#age").val(data.age)
            $("#phone").val(data.phone)
            $("#districtId").val(data.districtId)
        }
    })
})

$("#editUserComment").click(function (e){
    console.log("editUserComment")
        var email=$("#email").val();
    console.log(email)
        var id=$("#editUser").val()
        console.log(id)
        var fullName=$("#fullname").val();
        var age=$("#age").val();
        var phone=$("#phone").val();
        var password=$("#password").val();
    console.log($("#districtId").val())
        var districtId=$("#districtId").val();
        let obj= {
            id,
            email,
            fullName,
            age,
            phone,
            password,
            districtId
        }
    console.log(obj)
    $.ajax({
        url:`/api/user/edit/${obj.id}`,
        data: JSON.stringify(obj),
        type:"post",
        contentType:"application/json; charset=utf-8",
        success : function (data) {
            console.log(data)
        }
    })
})

function togleModal(){
    $("#exampleModal").modal("toggle");
}

function togleModal1(){
    $("#exampleModal1").modal("toggle");
}
