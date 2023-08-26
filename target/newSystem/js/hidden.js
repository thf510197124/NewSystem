window.onload=function () {
    var addressForm = document.getElementsByClassName("addAddress-form");
    if (addressForm.length === 1){
        addressForm[0].style.display="none";
    }
    addressForm.forEach(function (value) {
        value.style.display="none";
    });
    /*$(".addAddress-form").hide();*/
    /*$(".hidden-employee").hide();*/
    var hidden_employee = document.getElementsByClassName("hidden-employee");
    hidden_employee.forEach(function (value) {
        value.style.display="none";
    });
    var hidden_company = document.getElementsByClassName("hidden-company");
    hidden_company.forEach(function (value) {
        value.style.display="none";
    });
   /* $(".hidden-company").hide();*/
};