function setItem(){
    window.localStorage.removeItem("token")
    window.localStorage.removeItem("total")
}
function checkTonke(){
    console.log(localStorage.getItem("token"))
    if (localStorage.getItem("token") == null ){
        window.location.href = "sign-in.html"
    }
}