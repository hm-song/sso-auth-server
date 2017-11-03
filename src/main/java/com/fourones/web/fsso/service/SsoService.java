package com.fourones.web.fsso.service;

import com.fourones.web.fsso.entity.AccessToken;
import com.fourones.web.fsso.entity.Client;
import com.fourones.web.fsso.repository.AccessTokenRepository;
import com.fourones.web.fsso.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SsoService {
    //
    private static final Logger log = LoggerFactory.getLogger(SsoService.class);

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public AccessToken getAccessToken(String token, String clientId) {
        //
        String tokenId = extractTokenId(token);

        return accessTokenRepository.findByTokenIdAndClientId(tokenId, clientId);
    }

    private String extractTokenId(String value) {
        //
        if (value == null) {
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] bytes = digest.digest(value.getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }

//    @Transactional
//    public String logoutAllClients(String clientId, String userName) {
//        //
//        requestLogoutToAllClients(userName);
//
//        removeAccessTokens(userName);
//
//        Client client = clientRepository.findOne(clientId);
//
//        return client.getBaseUri();
//    }
//
//    private void requestLogoutToAllClients(String userName) {
//        //
//        List<AccessToken> tokens = accessTokenRepository.findByUserName(userName);
//
//        for (AccessToken token : tokens) {
//            requestLogoutToClient(token);
//        }
//    }
//
//    private void requestLogoutToClient(AccessToken token) {
//        //
//        Client client = clientRepository.findOne(token.getClientId());
//
//        String logoutUri = client.getLogoutUri();
//        String authorizationHeader = null;
//
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("tokenId", token.getTokenId());
//        paramMap.put("userName", token.getUserName());
//
////        HttpPost post = buildHttpPost(logoutUri, paramMap, authorizationHeader);
////        executePostAndParseResult(post, Object.class);
//    }
}
