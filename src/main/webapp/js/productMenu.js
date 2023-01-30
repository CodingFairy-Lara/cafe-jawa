// 메인 카테고리 분류(food / drink)
$(document).on('click', "a#drinkTab", function () {
    if($(this).hasClass("selected") == true) {
        $("#product_all_drink").prop("checked", true);
        $("#product_all_drink").change();
        $("div.product_drink_select").show();
        $("div.product_food_select").hide();
        $("dl.product_list_food").hide();
        $("dl.product_list_drink").show();
    }else {
        $(this).removeClass("unselected").addClass("selected");
        $(".dt2").children().removeClass("selected").addClass("unselected");
        $("#product_all_drink").prop("checked", true);
        $("#product_all_drink").change();
        $("input[name=product_drink]").change();
        $("div.product_drink_select").show();
        $("div.product_food_select").hide();
        $("dl.product_list_food").hide();
        $("dl.product_list_drink").show();
    }
});

// 서브 카테고리 분류 (메뉴링크 타고 들어왔을때)
function go_to_sub_cate(sub_cate) {
    if(sub_cate == 'product_bakery' || sub_cate == 'product_cake' || sub_cate == 'product_sandwich' || sub_cate == 'product_icecream') {
        $("a#foodTab").click();
        $("input:checkbox").prop("checked", false);
        $("input:checkbox").change();
        $("input[id="+sub_cate+"]:checkbox").prop("checked", true);
        $("input[name=product_food]").change();

    }else {
        $("input:checkbox").prop("checked", false);
        $("input:checkbox").change();
        $("input[id="+sub_cate+"]:checkbox").prop("checked", true);
        $("input[name=product_drink]").change();
    }
}

$(document).on('click', "a#foodTab", function () {
    if($(this).hasClass("selected") == true) {
        $("#product_all_food").prop("checked", true);
        $("#product_all_food").change();
        $(".product_food_select").show();
        $(".product_drink_select").hide();
        $(".product_list_drink").hide();
        $(".product_list_food").show();
    }else {
        $(this).removeClass("unselected").addClass("selected");
        $(".dt1").children().removeClass("selected").addClass("unselected");
        $("#product_all_food").prop("checked", true);
        $("#product_all_food").change();
        $("input[name=product_food]").change();
        $(".product_food_select").show();
        $(".product_drink_select").hide();
        $(".product_list_drink").hide();
        $(".product_list_food").show();
    }
});

// html dom 이 다 로딩된 후 실행됨
// $(document).ready(function(){
// 메인카테고리 선택 (drink / food)
    
    // 체크박스 전체선택 (food)
    // $("#product_all_food").change(function() {
    $(document).on("change", "#product_all_food", function(){
        if($("#product_all_food").is(":checked")) {
            $("input[name=product_food]").prop("checked", true);
            $("input[name=product_food]").change();
        }
        else {
            $("input[name=product_food]").prop("checked", false);
            $("input[name=product_food]").change();
        }
    });
    
    // $("input[name=product_food]").change(function() {
    $(document).on("change", "input[name=product_food]", function(){
        let total = $("input[name=product_food]").length;
        let checked = $("input[name=product_food]:checked").length;
        
        if(total != checked) $("#product_all_food").prop("checked", false);
        else $("#product_all_food").prop("checked", true); 
    });

    // id="product_bakery" 체크박스의 값이 변경될때
    // $("input[id=product_bakery]:checkbox").change(function(){
        // id="menu_list_bread" 옆의 태그중 dd 태그에 hide 클래스 태그를 넣던지 빼던지 한다.
    $(document).on("change", "input[id=product_bakery]:checkbox", function(){       
        if($(this).is(":checked")){
            $("#menu_list_bread").show();
            $("#menu_list_bread").next("dd").show();
        }else{
            ($("#menu_list_bread")).hide();
            $("#menu_list_bread").next("dd").hide();
        }
    });

    // $("input[id=product_cake]:checkbox").change(function(){
    $(document).on("change", "input[id=product_cake]:checkbox", function(){
        if($(this).is(":checked")){
            $("#menu_list_cake").show();
            $("#menu_list_cake").next("dd").show();
        }else{
            ($("#menu_list_cake")).hide();
            $("#menu_list_cake").next("dd").hide();
        }
    });

    // $("input[id=product_sandwich]:checkbox").change(function(){
    $(document).on("change", "input[id=product_sandwich]:checkbox", function(){
        if($(this).is(":checked")){
            $("#menu_list_sandwich").show();
            $("#menu_list_sandwich").next("dd").show();
        }else{
            ($("#menu_list_sandwich")).hide();
            $("#menu_list_sandwich").next("dd").hide();
        }
    });

    // $("input[id=product_icecream]:checkbox").change(function(){
    $(document).on("change", "input[id=product_icecream]:checkbox", function(){
        if($(this).is(":checked")){
            $("#menu_list_icecream").show();
            $("#menu_list_icecream").next("dd").show();
        }else{
            ($("#menu_list_icecream")).hide();
            $("#menu_list_icecream").next("dd").hide();
        }
    });


    // $("#product_all_drink").click(function() {
    $(document).on("change", "#product_all_drink", function(){
        if($("#product_all_drink").is(":checked")) {
            $("input[name=product_drink]").prop("checked", true);
            $("input[name=product_drink]").change();
        }
        else {
            $("input[name=product_drink]").prop("checked", false);
            $("input[name=product_drink]").change();
        }
    });
    
    // $("input[name=product_drink]").change(function() {
    $(document).on("change", "input[name=product_drink]", function(){
        let total = $("input[name=product_drink]").length;
        let checked = $("input[name=product_drink]:checked").length;
        
        if(total != checked) $("#product_all_drink").prop("checked", false);
        else $("#product_all_drink").prop("checked", true); 
    });

    // $("input[id=product_cold_brew]:checkbox").change(function(){
    $(document).on("change", "input[id=product_cold_brew]:checkbox", function(){
        if($(this).is(":checked")){
            $("#menu_list_coldbrew").show();
            $("#menu_list_coldbrew").next("dd").show();
        }else{
            ($("#menu_list_coldbrew")).hide();
            $("#menu_list_coldbrew").next("dd").hide();
        }
    });

    // $("input[id=product_espresso]:checkbox").change(function(){
    $(document).on("change", "input[id=product_espresso]:checkbox", function(){
        if($(this).is(":checked")){
            $("#menu_list_espresso").show();
            $("#menu_list_espresso").next("dd").show();
        }else{
            ($("#menu_list_espresso")).hide();
            $("#menu_list_espresso").next("dd").hide();
        }
    });

    // $("input[id=product_frappuccino]:checkbox").change(function(){    
    $(document).on("change", "input[id=product_frappuccino]:checkbox", function(){
        if($(this).is(":checked")){
            $("#menu_list_frappuccino").show();
            $("#menu_list_frappuccino").next("dd").show();
        }else{
            ($("#menu_list_frappuccino")).hide();
            $("#menu_list_frappuccino").next("dd").hide();
        }
    });

    // $("input[id=product_blended]:checkbox").change(function(){
    $(document).on("change", "input[id=product_blended]:checkbox", function(){
        if($(this).is(":checked")){
            $("#menu_list_blended").show();
            $("#menu_list_blended").next("dd").show();
        }else{
            ($("#menu_list_blended")).hide();
            $("#menu_list_blended").next("dd").hide();
        }
    });

    // $("input[id=product_tea]:checkbox").change(function(){
    $(document).on("change", "input[id=product_tea]:checkbox", function(){
        if($(this).is(":checked")){
            $("#menu_list_tea").show();
            $("#menu_list_tea").next("dd").show();
        }else{
            ($("#menu_list_tea")).hide();
            $("#menu_list_tea").next("dd").hide();
        }
    });


    // menuView product_option_size
    $(document).on('click', "#tall_size", function () {
        if($(this).hasClass("selected") != true) {
            $(this).addClass("selected");
            $("#grande_size").removeClass("selected");
            $("#venti_size").removeClass("selected");
            $(".circle").hide();
            $(this).children(".circle").show();
        }
    });
    $(document).on('click', "#grande_size", function () {
        if($(this).hasClass("selected") != true) {
            $(this).addClass("selected");
            $("#tall_size").removeClass("selected");
            $("#venti_size").removeClass("selected");
            $(".circle").hide();
            $(this).children(".circle").show();
        }
    });
    $(document).on('click', "#venti_size", function () {
        if($(this).hasClass("selected") != true) {
            $(this).addClass("selected");
            $("#tall_size").removeClass("selected");
            $("#grande_size").removeClass("selected");
            $(".circle").hide();
            $(this).children(".circle").show();
        }
    });

    // menuView product_option_cup
    $(document).on('click', "#n_togo_cup", function () {
        if($(this).hasClass("selected") != true) {
            $(this).addClass("selected");
            $("#togo_cup").removeClass("selected");
        }
    });
    
    $(document).on('click', "#togo_cup", function () {
        if($(this).hasClass("selected") != true) {
            $(this).addClass("selected");
            $("#n_togo_cup").removeClass("selected");
        }
    });

    // 페이지 price값이 load 됐을때 value를 Number로 형변환하여 3자리 , 찍고 '원' 붙여서 html출력
    // function cart_price_init(div) {
    //     let price_val = Number(div.attr("value"));
    //     console.log('price_val :>> ', price_val);
    //     $(div).html(price_val + " 원");
    //     // $(div).removeAttr("init");
    // }

    // menuView quantity_n_price_amount
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
    
    function change_qty2(t){
        let basic_amount = $("#pv_price").val();
        //let min_qty = '수량버튼'*1;
        let min_qty = 1;
        let this_qty = $("#quantity_pv").val()*1;
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
        $("#quantity_pv").val(this_qty);
        $("#total_amount_pv").html(show_total_amount.format() + " 원");
    }

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

    function cartEnrollSubmit(frm) { 
        frm.action='../cart/cartEnroll'; 
        frm.submit(); 
        return true; 
      } 

    function testEncoding(productId, subCategory){
        productId+"";
        subCategory+"";
        let cup = '매장컵';

        if (subCategory == "BRD  " || subCategory == "CKE  " || subCategory == "SND  " || subCategory == "ICR  ") {
            window.location = "../cart/cartEnroll?productId="+productId+"&quantity=1&subCategory="+subCategory;
        }else {
            window.location = "../cart/cartEnroll?productId="+productId+"&quantity=1&subCategory="+subCategory+"&cup_size=Tall&cup_kind="+encodeURIComponent(cup);
        }
    }
       

