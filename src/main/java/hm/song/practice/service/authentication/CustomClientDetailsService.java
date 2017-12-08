package hm.song.practice.service.authentication;


import hm.song.practice.entity.Client;
import hm.song.practice.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomClientDetailsService implements ClientDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomClientDetailsService.class);

    @Autowired
    private ClientRepository repo;

    @Transactional
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = repo.findOne(clientId);
        if (client == null) {
            throw new ClientRegistrationException("Client is not found. clientId=" + clientId);
        }

        return client;
    }
}
