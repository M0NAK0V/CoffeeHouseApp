$(function(){
    $(document).ready(function(){
        var coffeeCount = $('#count').val();
        var price = $('#coffee_Price').text();
        var total_price = price * coffeeCount;

        var h4 = document.createElement("h4");
        h4.setAttribute("id","coffeePrice");
        h4.innerHTML = comma(total_price);
        $('#total').append(h4);
    }),
    $("#count").change(function(){
        var coffeeCount = $('#count').val();
        var price = $('#coffee_Price').text();
        var total_price = price * coffeeCount;

        $('#coffeePrice').text(comma(total_price));
    })
})

let comma = (num) => {
    let len, point, str;

    num = num + "";
    point = num.length % 3 ;
    len = num.length;

    str = num.substring(0, point);
    while (point < len) {
        if (str != "") str += ",";
        str += num.substring(point, point + 3);
        point += 3;
    }

    return str;

}

let orderBtn = () => {
    let countValue = $("#count").val();
    let coffeeUid = new URLSearchParams(location.search).get("uid");

    window.location.href="/orders/addOrder?coffeeUid="+coffeeUid+"&count="+countValue;
}