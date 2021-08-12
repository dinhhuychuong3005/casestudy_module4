function setItem() {
    window.localStorage.removeItem("token")
    window.localStorage.removeItem("total")
    window.localStorage.removeItem("idFriend");
    window.localStorage.removeItem("fullNameUs")
    window.localStorage.removeItem("fullName")
    window.localStorage.removeItem("idPost_comment")
    window.localStorage.removeItem("id")
    window.localStorage.removeItem("user")
    window.localStorage.removeItem("password")
    window.localStorage.removeItem("nameSearch")
}

function checkTonke() {
    console.log(localStorage.getItem("token"))
    if (localStorage.getItem("token") == null) {
        window.location.href = "sign-in.html"
    }
}

function getUserById1(id) {
    console.log(id)
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/" + id,
        headers: {"Authorization": 'Bearer ' + localStorage.getItem("token")},
        success: function (data) {
            console.log(data.fullName)
            localStorage.setItem("fullNameUs", data.fullName)
            document.getElementById("name").innerHTML = data.fullName;
        }
    })
}
