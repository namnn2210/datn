$('.zmdi-shopping-cart').click(function () {
    var nameProduct = $(this).parent().parent().parent().find('.product-name').html();
    $(this).on('click', function(){
        swal(nameProduct, "is added to cart !", "success");
    });
})