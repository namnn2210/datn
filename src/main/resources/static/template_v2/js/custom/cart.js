$('.add-to-cart').click(function (event) {
    var nameProduct = $(this).parent().parent().parent().find('.product-name').html();
    $(this).on('click', function () {
        swal(nameProduct, "is added to cart !", "success");
    });
    var cart_quantity = parseInt($('.cart-quantity').text());
    var choose_size = $('.size-active').text();
    var item_quantity = $('.num-product').val();
    var cart_div = $('.header-dropdown');
    if (choose_size !== 's' && choose_size !== 'm' && choose_size !== 'l' && choose_size !== 'xl' && choose_size !== 'xxl') {
        swal("Please choose your size")
    } else {
        var product_id = $('#product-id').val();
        var product_data = {
            id: product_id,
            size: choose_size,
            quantity: item_quantity
        };
        event.preventDefault();
        $.ajax({
            type: "post",
            url: "/products/addToCart",
            contentType: 'application/json; charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify(product_data),
            success: function (resp) {
                if (resp.length === 0) {
                    swal("Null");
                } else {
                    swal({
                        title: "Thanks!",
                        text: "Product is added to cart",
                        icon: "success"
                    });
                    console.log(resp.cartItem);
                    $('.header-cart-item').remove();
                    //Get total price
                    var totalPrice = 0;
                    for (var i in resp.cartItem) {
                        //Add cart quantity to 1
                        console.log(resp.cartItem.length)
                        $('.cart-quantity').text(resp.cartItem.length);
                        //Add item to cart
                        var listCartItem = "";
                        listCartItem += '<li class="p-10 mb-15 header-cart-item">';
                        listCartItem += '<a href="#" class="product-image">';
                        listCartItem += '<img width="55" height="63" src="' + resp.cartItem[i].product.url.split('&')[0] + '"' + 'alt="IMG">';
                        listCartItem += '</a>';
                        listCartItem += '<div class="product-details ml-10">';
                        listCartItem += '<a href="#" class="remove">';
                        listCartItem += '<i class="zmdi zmdi-close"></i>';
                        listCartItem += '</a>';
                        listCartItem += '<a href="#" class="product-name mb-10">';
                        listCartItem += resp.cartItem[i].product.name;
                        listCartItem += '</a>';
                        listCartItem += '<span class="selected-color">';
                        listCartItem += "Size: " + resp.cartItem[i].size;
                        listCartItem += '</span>'
                        listCartItem += '<span class="selected-size">';
                        listCartItem += resp.cartItem[i].quantity + 'x' + resp.cartItem[i].product.price;
                        listCartItem += '</span>';
                        listCartItem += '</div>';
                        listCartItem += '</li>';
                        totalPrice += resp.cartItem[i].quantity * resp.cartItem[i].product.price;
                        $('.cart-list').append(listCartItem);
                        //Add total price
                        var cartTotal = "";
                        cartTotal += '<div class="border-bottom"></div>';
                        cartTotal += '<div class="cart-footer text-center">';
                        cartTotal += '<p class="total mtb-15">';
                        cartTotal += 'Subtotal:'
                        cartTotal += '<span class="ml-35">';
                        cartTotal += totalPrice;
                        cartTotal += '</p>';
                        //Add checkout button
                        var cartButton = "";
                        cartTotal += '<p class="buttons m-0">';
                        cartTotal += '<a th:text="#{label.view.cart}" href="/cart/detail" class="button extra-small">';
                        cartTotal += '<span>Checkout</span>'
                        cartTotal += '</a>';
                        cartTotal += '</p>';
                        cartTotal += '</div>';
                        $('.cart-footer').remove();
                        $('.cart-list').append(cartTotal);
                    }
                }
            },
            error: function () {
                swal("Error");
            }
        });
    }
})

$('.size').click(function () {
    $('.size').removeClass('size-active')
    $(this).addClass('size-active')
})

$('.cart-item-quantity').change(function () {
    var update_products = [];

    $('.cart-item-quantity').each(function () {
        if ($(this).val() < 1) {
            swal({
                text: "Product quantity must be bigger than 1",
                icon: "error"
            });
            $(this).val(1)
        } else {
            update_products.push({id: $(this).attr("name"), size: $(this).attr("id"), quantity: $(this).val()});
        }
    });
    console.log(update_products)
    $.ajax({
        type: "post",
        url: "/cart/updateCart",
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify(update_products),
        success: function (resp) {
            console.log(resp);
            // for (var i in resp.cartItem) {
            //     var item_class = 'item-' + resp.cartItem[i].size;
            //     $('.num-product' + item_class).val(resp.cartItem[i].quantity);
            //     $('.' + item_class).text('$' + resp.cartItem[i].quantity * resp.cartItem[i].product.price);
            // }
            $('.sub-total').text('$' + resp.totalPrice);
            $('.total-price').text('$' + resp.totalPrice);
        },
        error: function () {
            swal("Error");
        }
    });
})

$('.product-remove').click(function (event) {
    event.preventDefault();
    var deleteId = $('.table-row').attr("id").replace('item-', '');
    var size = $('.table-row').attr("name");
    var delete_product = {
        id: deleteId,
        size: size
    };
    swal({
        title: "Are you sure you want to delete this product?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
            $.ajax({
                method: "post",
                url: "/cart/removeCartProduct",
                contentType: 'application/json; charset=UTF-8',
                dataType: 'json',
                data: JSON.stringify(delete_product),
                success: function (resp) {
                    swal("Product is deleted!", {
                        icon: "success",
                    });
                    // swal("Product is deleted");
                    $("#item-" + deleteId).remove();
                    $('.cart-quantity').text(resp.cartItem.length);
                    $('.sub-total').text('$' + resp.totalPrice);
                    $('.total-price').text('$' + resp.totalPrice);
                },
                error: function () {
                    swal("Error");
                }
            })
        }
    })
    // if (confirm(confirm_question)) {
    //
    // }
})