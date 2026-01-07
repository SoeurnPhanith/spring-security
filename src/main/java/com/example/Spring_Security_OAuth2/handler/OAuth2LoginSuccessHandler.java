package com.example.Spring_Security_OAuth2.handler;

import com.example.Spring_Security_OAuth2.model.OAuth2Account;
import com.example.Spring_Security_OAuth2.model.UserModel;
import com.example.Spring_Security_OAuth2.repo.OAuth2AccountRepository;
import com.example.Spring_Security_OAuth2.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OAuth2AccountRepository oauth2Repo;


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        //1. get userInfo from OAuth2 (OAuth2 is object ដែលផ្ទុក data មកពី Google/GitcHub)
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        //2.Detect Provider (user login from google or github or facebook)
        String provider = ((OAuth2AuthenticationToken) authentication)
                          .getAuthorizedClientRegistrationId();

        // 3. Get ProviderId (unique Id)
        Object providerIdObj=null; // Google
        if ("google".equals(provider)) {
            providerIdObj = oauthUser.getAttribute("sub"); // facebook
        }else if("github".equals(provider)){
            providerIdObj = oauthUser.getAttribute("id");
        }else if("facebook".equals(provider)){
            providerIdObj = oauthUser.getAttribute("id");
        }
        String providerUserId = providerIdObj != null ? String.valueOf(providerIdObj) : null;

        //4.get Information from oauth2 object
        String email = oauthUser.getAttribute("email");
        if (email == null) {
            email = oauthUser.getAttribute("login") + "@github.com";
        }
        String name = oauthUser.getAttribute("name");
        String picture = null;
        if ("google".equals(provider)) {
            picture = oauthUser.getAttribute("picture");
        } else if ("github".equals(provider)) {
            picture = oauthUser.getAttribute("avatar_url");
        } else if ("facebook".equals(provider)) {
            Map<String, Object> pictureObj = oauthUser.getAttribute("picture");
            if (pictureObj != null) {
                Map<String, Object> data = (Map<String, Object>) pictureObj.get("data");
                if (data != null) {
                    picture = (String) data.get("url");
                }
            }
        }
            //5.find User in db or create user into db
        String finalEmail = email;
        UserModel user = userRepository.findByEmail(email)
                .orElseGet(() -> createUser(finalEmail, name));

        //6.save OAuth2 account into db
        saveOAuth2Account(provider, providerUserId, email, picture, user);
        response.sendRedirect("/home"); //redirect to home page
    }

    //create and save user into db
    private UserModel createUser(String email, String name) {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setName(name);
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    //saving oauth2 account
    private void saveOAuth2Account(
            String provider, String providerUserId,
            String email, String picture, UserModel user
    ) {

        oauth2Repo.findByProviderAndProviderId(provider, providerUserId)
                .orElseGet(() -> {
                    OAuth2Account acc = new OAuth2Account();
                    acc.setProvider(provider);
                    acc.setProviderId(providerUserId);
                    acc.setEmail(email);
                    acc.setPicture(picture);
                    acc.setUser(user);

                    return oauth2Repo.save(acc);
                });
    }
}
