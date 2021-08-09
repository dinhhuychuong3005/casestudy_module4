function setItem(){
    window.localStorage.removeItem("token")
    window.localStorage.removeItem("total")
}
function checkTonke() {
    console.log(localStorage.getItem("token"))
    if (localStorage.getItem("token") == null) {
        window.location.href = "sign-in.html"
    }
}
    function getUserById1() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/" + localStorage.getItem("id"),
            headers: {"Authorization": 'Bearer ' + localStorage.getItem("token")},
            success: function (data) {
                localStorage.setItem("fullName", data.fullName)

            }
        })
    }
