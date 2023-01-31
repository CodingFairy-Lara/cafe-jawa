// cartView cart_quantity
function change_qty_cart(t, id){
    let basic_amount = Number($("#amount_init_"+id).attr("value"));
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
    $("#quantity_"+id).val(this_qty);
    $("#total_amount_"+id).html(show_total_amount.format() + " 원");

    // $("#checkbox_cartList_"+id).attr("value", show_total_amount);
    $(".get_totalPrice_"+id).attr("value", show_total_amount);
    $(".get_quantity_"+id).attr("value", this_qty);
    calcTotalPrice();
};

    function input_size(size) {
    document.getElementById('cup_size').value = size;
}

    function input_cup(cup) {
    document.getElementById('cup_kind').value = cup;
}

function cart_deleteOne(cartId, opId){
	let cart_id = Number(cartId);
	let op_id = Number(opId);
	let formObj=document.createElement("form");
	let i_cart = document.createElement("input");
	let i_op = document.createElement("input");

	i_cart.name="cart_id";
	i_cart.value=cart_id;
    i_op.name="op_id";
    i_op.value=op_id;
	
	formObj.appendChild(i_cart);
	formObj.appendChild(i_op);
    document.body.appendChild(formObj); 
    formObj.method="post";
    formObj.action="../cart/deleteOne";
    formObj.submit();
}

function cart_deleteAll(memberId){
	let member_Id = memberId;
	let formObj=document.createElement("form");
	let i_member = document.createElement("input");

    i_member.name="member_Id";
    i_member.value=member_Id;
	
	formObj.appendChild(i_member);
    document.body.appendChild(formObj); 
    formObj.method="post";
    formObj.action="../cart/deleteAll";
    formObj.submit();
}

function cart_findSelected() {
    let cartList = $("input[name=checked_cart_product]").length;
    let cart_id = 0;
    let op_id = 0;
    
    for (let i = 0; i < cartList; i++) {
        if ($("input[name=checked_cart_product]")[i].checked == true) {
            cart_id = parseInt($("input[name=get_cart_id]")[i].value);
            op_id = parseInt($("input[name=get_op_id]")[i].value);
            console.log(cart_id, op_id);
            cart_deleteSelected(cart_id, op_id);
        }
    }
}

function cart_deleteSelected(cart_id, op_id){
    let cartId = Number(cart_id);
	let opId = Number(op_id);

    $.ajax({
        type : "post",
        async : true, //false인 경우 동기식으로 처리한다.
        url : "../cart/deleteSelected"
        + "?cart_id=" + encodeURIComponent(cartId) + "&op_id=" + encodeURIComponent(opId),
        
        success : function(data) {
            if(data.trim()!='1'){
                alert("장바구니 선택삭제 실패! 다시 시도해 주세요.");	
            }
        },
        error : function(data) {
            alert("에러가 발생했습니다.["+data+"]");
        },
        done : function(data) {
        }
    }); //end ajax	
}


function order_findSelected() {
    let cartList = $("input[name=checked_cart_product]").length;
    let cart_id = 0;
    let op_id = 0;
    
    for (let i = 0; i < cartList; i++) {
        if ($("input[name=checked_cart_product]")[i].checked == true) {
            cart_id = parseInt($("input[name=get_cart_id]")[i].value);
            op_id = parseInt($("input[name=get_op_id]")[i].value);
            console.log(cart_id, op_id);
            order_selectedList(cart_id, op_id);
        }
    }
}


function modify_cart_qty(cartId,opId,quantity){
    let cart_id = Number(cartId);
	let op_id = Number(opId);
	let Qty = Number(quantity);

	$.ajax({
		type : "post",
		async : true, //false인 경우 동기식으로 처리한다.
		url : "../cart/modifyQty"
        + "?cart_id=" + encodeURIComponent(cart_id) + "&op_id=" + encodeURIComponent(op_id) + "&quantity=" + encodeURIComponent(Qty),
		
		success : function(data) {
			if(data.trim()!='1'){
				alert("장바구니 수량변경 실패! 다시 시도해 주세요.");	
			}
		},
		error : function(data) {
			alert("에러가 발생했습니다.["+data+"]");
		},
        done : function(data) {
        }
	}); //end ajax	
}

// 체크박스 전체선택 (cartList)
$(document).on("change", "#cartList_selectAll", function(){
    if($("#cartList_selectAll").is(":checked")) {
        $("input[name=checked_cart_product]").prop("checked", true);
        $("input[name=checked_cart_product]").change();
    }
    else {
        $("input[name=checked_cart_product]").prop("checked", false);
        $("input[name=checked_cart_product]").change();
    }
    calcTotalPrice();
});

$(document).on("change", "input[name=checked_cart_product]", function(){
    let total = $("input[name=checked_cart_product]").length;
    let checked = $("input[name=checked_cart_product]:checked").length;
    
    if(total != checked) $("#cartList_selectAll").prop("checked", false);
    else $("#cartList_selectAll").prop("checked", true); 
});

function calcTotalPrice(){
    let cartList = $("input[name=checked_cart_product]").length;
    let final_quantity_price = 0;
    let final_total_price = 0;
 
    for (let i = 0; i < cartList; i++) {
        if ($("input[name=checked_cart_product]")[i].checked == true) {
            final_quantity_price += parseInt($("input[name=quantity]")[i].value);
        }
    }
    for (let i = 0; i < cartList; i++) {
        if ($("input[name=checked_cart_product]")[i].checked == true) {
            final_total_price += parseInt($("input[name=get_totalPrice]")[i].value);
        }
    }
    $("#sum_total_num").html("선택 상품 수량 : " + final_quantity_price.format() + " 개");
    $("#sum_total_price").html("합계 금액 : " + final_total_price.format() + " 원");
}

function calcTotalPrice_order(){
    let cartList = $("input[name=checked_cart_product]").length;
    let final_quantity_price = 0;
    let final_total_price = 0;
 
    for (let i = 0; i < cartList; i++) {
        if ($("input[name=checked_cart_product]")[i].checked == true) {
            final_quantity_price += parseInt($("input[name=quantity]")[i].value);
        }
    }
    for (let i = 0; i < cartList; i++) {
        if ($("input[name=checked_cart_product]")[i].checked == true) {
            final_total_price += parseInt($("input[name=get_totalPrice]")[i].value);
        }
    }
    $("#sum_total_num_order").html("선택 상품 수량 : " + final_quantity_price.format() + " 개");
    $("#sum_total_price_order").html("합계 금액 : " + final_total_price.format() + " 원");
}

function getFinalPrice() {
    let tot_price_input = $("input[name=get_totalPrice]");
    let final_price = 0;

    for (let i = 0; i < tot_price_input.length; i++) {
        final_price += Number(tot_price_input[i].value);
    }
    Number(final_price);
    console.log('final_price :>> ', final_price);
    $("#final_totPrice").attr("value", final_price);

}


// 신용카드 결제 폼
const ccform = document.querySelector('form.credit_card_form');
const completeccPaymentButton = document.querySelector('button#complete-payment_cc');

ccform.addEventListener('submit', handleccFormSubmission);                       

function handleccFormSubmission(event) {
  event.preventDefault();
  validate();
  ccform.reportValidity();
  if (ccform.checkValidity() === false) {
    alert('Invalid data foun유효하지 않은 데이터입니다.');
    // Handle invalid form data.
  } else {
      completeccPaymentButton.textContent = '결제 진행중...';
      completeccPaymentButton.disabled = 'true';
      orderEnroll_cc();
        setTimeout(() => {
        alert('[ '+ Number($("input#totalPrice").val()).format() +' ] 원 결제완료!!');
        event.preventDefault();
        ccform.submit();},
        5000);
  }
}

// 모바일 결제 폼
const mbform = document.querySelector('form.mobile_check_form');
const completembPaymentButton = document.querySelector('button#complete-payment_mb');
mbform.addEventListener('submit', handlembFormSubmission);                       

function handlembFormSubmission(event) {
  event.preventDefault();
  validate();
  mbform.reportValidity();
  if (mbform.checkValidity() === false) {
    alert('유효하지 않은 데이터입니다.');
    // Handle invalid form data.
  } else {
    // On a production site do form submission.
    completembPaymentButton.textContent = '결제 진행중...';
    completembPaymentButton.disabled = 'true';
    orderEnroll_mb();
        setTimeout(() => {
        alert('[ '+ Number($("input#totalPrice").val()).format() +' ] 원 결제완료!!');
        event.preventDefault();
        mbform.submit();},
        5000);
  }
}

// Do form validation.
function validate() {
  // let message= '';
  // if (!/someregex/.test(someField.value)) {
  //   console.log(`Invalid value ${someField.value} for someField`);
	// 	 message = 'Explain how to enter a valid value';
  // }
  // someInput.setCustomValidity(message);
}

// 전화번호 입력시 자동으로 하이픈 추가
const hypenTel = (target) => {
    target.value = target.value
    .replace(/[^0-9]/g, '')
    .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

// 금액 3자리수 콤마찍기위해 가격값을 Number로 전환
function changeIntoNumber() {
    let price = $("input#totalPrice").val();
    console.log('price :>> ', price);
    price = Number(price);
    $("h3#total_price").html("주문 금액 : " + price.format() + " 원");
    $("h2#total_price_final").html("주문 금액 : " + price.format() + " 원");
}

function orderEnroll_cc(){
    let memberId = $("input[name=memberId].credit_card_form").val();
    let payment_totPrice = Number($("input[name=payment_totPrice].credit_card_form").val());
    let payment_type = $("input[name=payment_type].credit_card_form").val();
    let storeId = $("input[name=storeId].credit_card_form").val();
    let opIdList = [];
    $("input[name=opIdList].credit_card_form").each(function(i){//체크된 리스트 저장
        opIdList.push($(this).val());
    });

    $.ajax({
        type : "post",
        async : false, //false인 경우 동기식으로 처리한다.
        url : "../order/enroll"
        + "?memberId=" + encodeURIComponent(memberId) 
        +  "&payment_totPrice=" + encodeURIComponent(payment_totPrice) 
        +  "&payment_type=" + encodeURIComponent(payment_type) 
        +  "&storeId=" + encodeURIComponent(storeId) 
        +  "&opIdList=" + encodeURIComponent(opIdList),
        success : function(data) {
            if(data.trim()!='1'){
                alert("상품주문 실패! 다시 시도해 주세요.");	
            }
        },
        error : function(data) {
            alert("에러가 발생했습니다.["+data+"]");
        },
        done : function(data) {
        }
    }); //end ajax	
}

function orderEnroll_mb(){
    let memberId = $("input[name=memberId].mobile_check_form").val();
    let payment_totPrice = Number($("input[name=payment_totPrice].mobile_check_form").val());
    let payment_type = $("input[name=payment_type].mobile_check_form").val();
    let storeId = $("input[name=storeId].mobile_check_form").val();
    let opIdList = [];
    $("input[name=opIdList].mobile_check_form").each(function(i){//체크된 리스트 저장
        opIdList.push($(this).val());
    });


    $.ajax({
        type : "post",
        async : true, //false인 경우 동기식으로 처리한다.
        url : "../order/enroll"
        + "?memberId=" + encodeURIComponent(memberId) 
        +  "&payment_totPrice=" + encodeURIComponent(payment_totPrice) 
        +  "&payment_type=" + encodeURIComponent(payment_type) 
        +  "&storeId=" + encodeURIComponent(storeId) 
        +  "&opIdList=" + encodeURIComponent(opIdList),
        success : function(data) {
            if(data.trim()!='1'){
                alert("상품주문 실패! 다시 시도해 주세요.");	
            }
        },
        error : function(data) {
            alert("에러가 발생했습니다.["+data+"]");
        },
        done : function(data) {
        }
    }); //end ajax	
}