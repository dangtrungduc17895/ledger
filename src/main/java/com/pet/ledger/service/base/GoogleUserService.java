package com.pet.ledger.service.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.ledger.exceptionhandler.exception.MyException;
import com.pet.ledger.model.type.GoogleUser;
import com.pet.ledger.model.type.User;
import com.pet.ledger.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

@Component
public class GoogleUserService {

    private final Environment environment;

    @Autowired
    public GoogleUserService(Environment environment) {
        this.environment = environment;
    }

    public GoogleUser getGoogleUserInfo(String accessToken) throws IOException, MyException {
        if (ValidateUtils.isStringNullOrEmpty(accessToken)){
            throw new MyException("Access token is null or empty.");
        }
        String link = environment.getProperty("google.link.get.user_info")+accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, GoogleUser.class);
    }

    public User getUserFromGoogleUser(GoogleUser googleUser) {
        return new User(googleUser.getEmail(),googleUser.getName(), googleUser.getPicture());
    }
}
