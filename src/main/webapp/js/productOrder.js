// $(document).ready(function(){
//     $("#product_all_food").change(function() {
//         if($("#product_all_food").is(":checked")) {
//             $("input[name=product_food]").prop("checked", true);
//             $("input[name=product_food]").change();
//         }
//         else {
//             $("input[name=product_food]").prop("checked", false);
//             $("input[name=product_food]").change();
//         }
//     });

// }

function cartEnrollSubmit(frm) { 
    frm.action='../cart/cartEnroll'; 
    frm.submit(); 
    return true; 
  } 

// cartView cart_quantity
Number.prototype.format = function(){
    if(this==0) return 0;
    
    let reg = /(^[+-]?\d+)(\d{3})/;
    let n = (this + '');
    
    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');
    
    return n;
    };
    
String.prototype.format = function(){
let num = parseFloat(this);
if( isNaN(num) ) return "0";

return num.format();
};

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

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};

    function input_size(size) {
    document.getElementById('cup_size').value = size;
}

    function input_cup(cup) {
    document.getElementById('cup_kind').value = cup;
}
