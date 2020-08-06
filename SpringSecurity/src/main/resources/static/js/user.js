$(document).ready( () => {
    const errMsg = document.getElementById('err-msg');

    if(errMsg != null) {
        fncAlertErrMsg(errMsg);
    }
});

const fncCheckIpDuped  = () => {
    const id = document.getElementById('login-id')
        , dupChecked = document.getElementById('dup-check')
        , data = {id: id.value};

    if(!id.value) {
        alert("아이디를 입력해 주십시오.");
        id.focus();
    } else {
        $.ajax({
            url: "/check/id.do",
            type : 'POST',
            headers: {
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            data : data
        }).done( (response) => {
            if(response === 1) {
                alert("중복되는 아이디입니다. 다시 입력해 주십시오.");
                dupChecked.value = 'N';
                id.focus();
            } else {
                alert("중복되지 않은 아이디입니다.");
                dupChecked.value = 'Y';
            }
        });
    }
};

const fncCheckSignValue = () => {

    const id = document.getElementById('login-id')
        , dupChecked = document.getElementById('dup-check').value;

    if(!dupChecked) {
        alert("중복되는 아이디인지 확인해 주십시오.");
        id.focus();
        return false;
    } else if(dupChecked == 'N') {
        alert("중복되는 아이디입니다. 다시 입력해 주십시오.");
        id.focus();
        return false;
    } else {
        return confirm("가입 하시겠습니까?");
    }
};

const fncAlertErrMsg  = (element) => {
    if(element.value != null) {
        alert(element.value);
    }
};