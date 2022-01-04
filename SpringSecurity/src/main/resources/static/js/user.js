function fncCheckIpDuped () {
    const id            = document.getElementById("login-id")
        , dupChecked    = document.getElementById('dup-check')
        , data          = { id: id.value };

    if(!id.value) {
        alert("아이디를 입력해 주십시오.");
        id.focus();
    } else {
        $.ajax({
            url: "/check/id",
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
}

function fncCheckSignValue () {

    const id            = document.getElementById('login-id')
        , dupChecked    = document.getElementById('dup-check').value;

    if(!dupChecked) {
        alert("중복되는 아이디인지 확인해 주십시오.");
        id.focus();
        return false;
    } else if(dupChecked === 'N') {
        alert("중복되는 아이디입니다. 다시 입력해 주십시오.");
        id.focus();
        return false;
    } else {
        return confirm("가입 하시겠습니까?");
    }
}

function onClickGoogleLogin () {
    //구글 인증 서버로 인증코드 발급 요청
    // const clientId = "client id";
    // let url = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + clientId
    //         + "&redirect_uri=http://localhost:8081/login/oauth/google"
    //         + "&response_type=code"
    //         + "&scope=email%20profile%20openid"
    //         + "&access_type=offline";

    let url = "/oauth2/authorization/google";

    window.location.replace(url);
}