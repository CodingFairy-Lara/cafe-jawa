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
        url : "../../jawa/user/newOrderList?",
        
        success : function(data) {

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
    let button = document.querySelector('button#accept_btn_'+order_num);
    // button.disabled = 'true';
    order_num = parseInt(order_num);
    order_status = parInt(order_status);

    $.ajax({
        type : "post",
        async : true, //false인 경우 동기식으로 처리한다.
        url : "../../jawa/admin/accept" + "?order_num=" + encodeURIComponent(order_num),
        
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
    orderStatus = parseInt(orderStatus);
    orderNum = parseInt(orderNum);

    if (orderStatus < 5) {
        orderStatus++;
    } else {
        orderStatus = 0;
    }

    $.ajax({
        type : "post",
        async : true, //false인 경우 동기식으로 처리한다.
        url : "../../jawa/admin/updateStatus" 
        +"?orderNum=" + encodeURIComponent(orderNum)
        +"&orderStatus=" + encodeURIComponent(orderStatus),
        
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
    orderNum = parseInt(orderNum);
    orderStatus = parseInt(orderStatus);

    if (orderStatus == 3) {
        setTimeout(() => updateOrderStatus, 60000, orderNum, orderStatus);
    } else if (orderStatus == 4) {
        setTimeout(() => updateOrderStatus, 10000, orderNum, orderStatus);
    } else if (orderStatus == 5) {
        setTimeout(() => updateOrderStatus, 60000, orderNum, orderStatus);
    }
}