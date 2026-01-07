package com.example.Spring_Security_OAuth2.repo;

import com.example.Spring_Security_OAuth2.model.OAuth2Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuth2AccountRepository extends JpaRepository<OAuth2Account, Long> {
    Optional<OAuth2Account> findByProviderAndProviderId(
            String provider, String providerUserId
    );
}
