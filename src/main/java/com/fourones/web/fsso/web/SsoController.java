package com.fourones.web.fsso.web;

import com.fourones.web.fsso.entity.AccessToken;
import com.fourones.web.fsso.service.SsoService;
import com.fourones.web.fsso.web.dto.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SsoController {
    //
    @Autowired
    private SsoService ssoService;

    @RequestMapping(value="/userInfo", method= RequestMethod.POST)
    @ResponseBody
    public UserInfoResponse userInfo(@RequestParam(name="token") String token,
                                     @RequestParam(name="clientId") String clientId) {
        //
        AccessToken accessToken = ssoService.getAccessToken(token, clientId);

        UserInfoResponse response = new UserInfoResponse();
        if (accessToken == null) {
            //
            response.setResult(false);
            response.setMessage("사용자 정보를 조회할 수 없습니다.");
        }
        else {
            //
            response.setUserName(accessToken.getUserName());
        }

        return response;
    }

//    @RequestMapping(value="/userLogout", method=RequestMethod.GET)
//    public String userLogout(@RequestParam(name="clientId") String clientId,
//                             HttpServletRequest request) {
//        //
//        String userName = request.getRemoteUser();
//        String baseUri = ssoService.logoutAllClients(clientId, userName);
//
//        request.getSession().invalidate();
//
//        return "redirect:" + baseUri;
//    }
}