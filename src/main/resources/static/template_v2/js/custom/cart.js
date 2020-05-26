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