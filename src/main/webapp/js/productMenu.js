
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


});

