$(document).ready(
    function (){
        console.log("Men jQuery code man");

        $.get("/api/adress/province", function (data) {
            console.log(data);
            data.forEach(item => {
                $("#province").append("     <option value=" + item.id + ">" + item.name + "</option>")
            });
        })
    }
);

function getDistrict(id) {
    $.ajax({
        url: "/api/adress/district/" + id,
        type: "get",
        contentType: "application/json",
        success: function (data) {
            console.log(data);
            $("#district").html("");
            data.forEach(item => {
                $("#district").append("<option value=" + item.id + ">" + item.name + "</option>")
            });
        },
        error: function () {
            console.log("xatolik");
        }
    })
}

$("#province").on("change",function (el){
  getDistrict(el.target.options.selectedIndex);
})

$("#district").on("change",function (el){
    console.log(el.target.options);
})
