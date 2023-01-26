// 메인 카테고리 분류(food / drink)
$(document).on('click', "a#drinkTab", function () {
    if($(this).hasClass("selected") == true) {
        $("#product_all_drink").prop("checked", true);
        $("#product_all_drink").change();
        $(".product_drink_select").show();
        $(".product_food_select").hide();
        $(".product_list_food").hide();
        $(".product_list_drink").show();
    }else {
        $(this).removeClass("unselected").addClass("selected");
        $(".dt2").children().removeClass("selected").addClass("unselected");
        $("#product_all_drink").prop("checked", true);
        $("#product_all_drink").change();
        $("input[name=product_drink]").change();
        $(".product_drink_select").show();
        $(".product_food_select").hide();
        $(".product_list_food").hide();
        $(".product_list_drink").show();
    }
});

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
$(document).ready(function(){
    // 메인카테고리 선택 (drink / food)

    // 체크박스 전체선택 (food)
    $("#product_all_food").change(function() {
        if($("#product_all_food").is(":checked")) {
            $("input[name=product_food]").prop("checked", true);
            $("input[name=product_food]").change();
        }
        else {
            $("input[name=product_food]").prop("checked", false);
            $("input[name=product_food]").change();
        }
    });
    
    $("input[name=product_food]").change(function() {
        let total = $("input[name=product_food]").length;
        let checked = $("input[name=product_food]:checked").length;
        
        if(total != checked) $("#product_all_food").prop("checked", false);
        else $("#product_all_food").prop("checked", true); 
    });

    // id="product_bakery" 체크박스의 값이 변경될때
    $("input[id=product_bakery]:checkbox").change(function(){
        // id="menu_list_bread" 옆의 태그중 dd 태그에 hide 클래스 태그를 넣던지 빼던지 한다.
        if($(this).is(":checked")){
            $("#menu_list_bread").removeClass("hide");
            $("#menu_list_bread").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_bread")).addClass("hide");
            $("#menu_list_bread").next("dd").addClass("hide");
        }
    });

    $("input[id=product_cake]:checkbox").change(function(){
        if($(this).is(":checked")){
            $("#menu_list_cake").removeClass("hide");
            $("#menu_list_cake").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_cake")).addClass("hide");
            $("#menu_list_cake").next("dd").addClass("hide");
        }
    });

    $("input[id=product_sandwich]:checkbox").change(function(){
        if($(this).is(":checked")){
            $("#menu_list_sandwich").removeClass("hide");
            $("#menu_list_sandwich").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_sandwich")).addClass("hide");
            $("#menu_list_sandwich").next("dd").addClass("hide");
        }
    });

    $("input[id=product_icecream]:checkbox").change(function(){
        if($(this).is(":checked")){
            $("#menu_list_icecream").removeClass("hide");
            $("#menu_list_icecream").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_icecream")).addClass("hide");
            $("#menu_list_icecream").next("dd").addClass("hide");
        }
    });


    $("#product_all_drink").click(function() {
        if($("#product_all_drink").is(":checked")) {
            $("input[name=product_drink]").prop("checked", true);
            $("input[name=product_drink]").change();
        }
        else {
            $("input[name=product_drink]").prop("checked", false);
            $("input[name=product_drink]").change();
        }
    });
    
    $("input[name=product_drink]").change(function() {
        let total = $("input[name=product_drink]").length;
        let checked = $("input[name=product_drink]:checked").length;
        
        if(total != checked) $("#product_all_drink").prop("checked", false);
        else $("#product_all_drink").prop("checked", true); 
    });

    $("input[id=product_cold_brew]:checkbox").change(function(){
        if($(this).is(":checked")){
            $("#menu_list_coldbrew").removeClass("hide");
            $("#menu_list_coldbrew").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_coldbrew")).addClass("hide");
            $("#menu_list_coldbrew").next("dd").addClass("hide");
        }
    });

    $("input[id=product_espresso]:checkbox").change(function(){
        if($(this).is(":checked")){
            $("#menu_list_espresso").removeClass("hide");
            $("#menu_list_espresso").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_espresso")).addClass("hide");
            $("#menu_list_espresso").next("dd").addClass("hide");
        }
    });

    $("input[id=product_frappuccino]:checkbox").change(function(){
        if($(this).is(":checked")){
            $("#menu_list_frappuccino").removeClass("hide");
            $("#menu_list_frappuccino").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_frappuccino")).addClass("hide");
            $("#menu_list_frappuccino").next("dd").addClass("hide");
        }
    });

    $("input[id=product_blended]:checkbox").change(function(){
        if($(this).is(":checked")){
            $("#menu_list_blended").removeClass("hide");
            $("#menu_list_blended").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_blended")).addClass("hide");
            $("#menu_list_blended").next("dd").addClass("hide");
        }
    });

    $("input[id=product_tea]:checkbox").change(function(){
        if($(this).is(":checked")){
            $("#menu_list_tea").removeClass("hide");
            $("#menu_list_tea").next("dd").removeClass("hide");
        }else{
            ($("#menu_list_tea")).addClass("hide");
            $("#menu_list_tea").next("dd").addClass("hide");
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


});
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
          
      let basic_amount = parseInt($("#total_amount").text());
      
      function change_qty2(t){
        //let min_qty = '수량버튼'*1;
        let min_qty = 1;
        let this_qty = $("#quantity").val()*1;
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
        $("#quantity").val(this_qty);
        $("#it_pay").val(show_total_amount);
        $("#total_amount").html(show_total_amount.format() + " 원");
      }

      function input_size(size) {
        document.getElementById('cup_size').value = size;
    }

      function input_cup(cup) {
        document.getElementById('cup_kind').value = cup;
    }
