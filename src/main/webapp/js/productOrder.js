// cartView cart_quantity
function change_qty_cart(t, id){
    let basic_amount = $("#p_price_"+id).val();
    //let min_qty = '수량버튼'*1;
    let min_qty = 1;
    let this_qty = $("#quantity_"+id).val()*1;
    if(t=="m"){
        this_qty -= 1;
        if(this_qty<min_qty){
        //alert("최소구매수량 이상만 구매할 수 있습니다.");
        alert("수량은 1개 이상 입력해 주십시오.");
        return;
        }
        }
        else if(t=="p"){
        this_qty += 1;
        }
    
    let show_total_amount = basic_amount * this_qty;
    //$("#ct_qty_txt").text(this_qty); 
    $("#quantity_"+id).val(this_qty);
    $("#total_amount_"+id).html(show_total_amount.format() + " 원");
};

    function input_size(size) {
    document.getElementById('cup_size').value = size;
}

    function input_cup(cup) {
    document.getElementById('cup_kind').value = cup;
}
