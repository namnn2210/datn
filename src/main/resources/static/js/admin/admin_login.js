$('.btn-admin-login').click(function (event) {
    let username = $('.admin-username').val();
    let password = $('.admin-password').val();
    let data = {
        "username": username,
        "password": password
    };
    event.preventDefault();
    $.ajax({
        type: "post",
        url: "/jwt/login",
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(data),
        success:function (resp) {
            swal({
                title: "Login successfully!",
                // text: "Product is added to cart",
                icon: "success"
            });
            $.ajax({
                type:"get",
                url:"/admin/dashboard",
                headers : {
                    'Authorization':resp
                }
            })
        },
        error:function (resp) {
            if(resp.status === 403) {
                swal({
                    title: "Permission Denied",
                    text: "You're not allowed to login with this account",
                    icon: "error"
                });
            }
            if(resp.status === 404) {
                swal({
                    title: "Not found",
                    text: "User not found! Please contact administrator for more detail",
                    icon: "error"
                });
            }
            console.log(resp)
        }
    });
})