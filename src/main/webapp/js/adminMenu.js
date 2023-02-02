function getNewOrderList(){
    console.log('time :>> 5');

    $.ajax({
        type : "post",
        async : true, //false인 경우 동기식으로 처리한다.
        url : "../../jawa/admin/newOrderList",
        
        success : function(data) {

			
        },
        error : function(data) {
            alert("에러가 발생했습니다.["+data+"]");
        },
        done : function(data) {
        }
    }); //end ajax	
}

function getNewOrderList_user() {

    $.ajax({
        type : "post",
        async : true, //false인 경우 동기식으로 처리한다.
        url : "../../jawa/user/newOrderList",
        
        success : function(data) {
            console.log('data :>> ', data);
        },
        error : function(data) {
            alert("에러가 발생했습니다.["+data+"]");
        },
        done : function(data) {
        }
    }); //end ajax	
}

function adminStopInterval(intervalId) {
    clearInterval(intervalId);
  }

function acceptOrder_admin(order_num, order_status) {
    // button.disabled = 'true';
    let orderNum = Number(order_num);
    let orderStatus = Number(order_status);
    console.log(orderNum, orderStatus);

    $.ajax({
        type : "post",
        async : true, //false인 경우 동기식으로 처리한다.
        url : "../../jawa/admin/accept" + "?orderNum="
         + encodeURIComponent(orderNum),
        
        success : function(data) {
            console.log('data :>> ', data);
            $('#acceptOrder_result_'+order_num).html("주문수락 / 상품 준비중");
        },
        error : function(data) {
            alert("에러가 발생했습니다.["+data+"]");
        },
        done : function(data) {
        }
    }); //end ajax	
}

function updateOrderStatus(orderNum, orderStatus) {
    let order_num = Number(orderNum);
    let order_status = Number(orderStatus);
    console.log(order_num, order_status);

    $.ajax({
        type : "post",
        async : true, //false인 경우 동기식으로 처리한다.
        url : "../../jawa/admin/updateStatus" 
        +"?order_num=" + encodeURIComponent(order_num)
        +"&order_status=" + encodeURIComponent(order_status),
        
        success : function(data) {
        },
        error : function(data) {
            alert("에러가 발생했습니다.["+data+"]");
        },
        done : function(data) {
        }
    }); //end ajax	

}


function updateUserStatus(orderNum, orderStatus) {
    console.log(orderNum, orderStatus);
    let order_num = Number(orderNum);
    let order_status = Number(orderStatus);
    console.log(orderNum, orderStatus);
    if (order_status < 2) {
        $("button#accept_btn_"+orderNum).html("*수령완료 상품*");
        $("button#accept_btn_"+orderNum).css("color","red");
        $("button#accept_btn_"+orderNum).disabled = 'true';
        return;
    } else {
        setTimeout(() => {
            acceptOrder_admin(order_num); // 3
            $("#acceptOrder_result_"+order_num).html("주문수락됨 / 상품 준비중");
            updateTdColor();
            setTimeout(() => {
                order_status = 4;
                updateOrderStatus(order_num, order_status);
                $("#acceptOrder_result_"+order_num).html("상품 준비 임박");
                updateTdColor();
	            setTimeout(() => {
	                order_status = 5;
	                updateOrderStatus(order_num, order_status);
	                $("#acceptOrder_result_"+order_num).html("상품 준비완료 / 수령대기");
	                updateTdColor();
		            setTimeout(() => {
		                order_status = 0;
		                updateOrderStatus(order_num, order_status);
		                $("#acceptOrder_result_"+order_num).html("수령완료");
		                $("button#accept_btn_"+orderNum).html("*수령완료 상품*");
		                $("button#accept_btn_"+orderNum).css("color","red");
		                $("button#accept_btn_"+orderNum).disabled = 'true';
		                updateTdColor();
		            }, 50000);
	            }, 12000);
            }, 50000);
        }, 1000);
    }
}