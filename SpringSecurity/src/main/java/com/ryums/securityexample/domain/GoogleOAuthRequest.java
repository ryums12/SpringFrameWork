/*
*  OAuth 요청 데이터 모델
*
*  매개변수 : https://developers.google.com/identity/protocols/oauth2/web-server#creatingclient
* */
package com.ryums.securityexample.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleOAuthRequest {

    private String redirectUri;
    private String clientId;
    private String clientSecret;
    private String code;
    private String responseType;
    private String scope;
    private String accessType;
    private String grantType;
    private String state;
    private String includeGrantedScopes;
    private String loginHint;
    private String prompt;

}
