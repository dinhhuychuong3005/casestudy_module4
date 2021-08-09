function setItem() {
    window.localStorage.removeItem("token")
    window.localStorage.removeItem("total")
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
