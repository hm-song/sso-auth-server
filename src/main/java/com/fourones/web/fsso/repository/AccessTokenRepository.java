package com.fourones.web.fsso.repository;

import com.fourones.web.fsso.entity.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {
    //
    AccessToken findByTokenIdAndClientId(String tokenId, String clientId);

    int deleteByUserName(String userName);

    List<AccessToken> findByUserName(String userName);
}
