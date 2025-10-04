package com.openclassrooms.mddapi.configuration;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

@Configuration
public class JwtEncoderConfig {

    @Value("${jwt.public.key}")
    private String publicKey;

    @Value("${jwt.private.key}")
    private String privateKey;

    @Bean
    public JwtEncoder jwtEncoder() throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKey rsaPublicKey = getPublicKey(publicKey);
        RSAPrivateKey rsaPrivateKey = getPrivateKey(privateKey);

        RSAKey key = new RSAKey.Builder(rsaPublicKey).privateKey(rsaPrivateKey).build();

        JWKSet jwkSet = new JWKSet(key);
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(jwkSet);
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder() throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKey rsaPublicKey = getPublicKey(publicKey);
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

    /**
     * Decode a Base64 encoded RSA public key string into an RSAPublicKey object.
     *
     * @param publicKey the Base64 encoded RSA public key string
     * @return an RSAPublicKey object
     * @throws NoSuchAlgorithmException if the RSA algorithm is not supported
     * @throws InvalidKeySpecException  if the provided key spec is invalid
     */
    private RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * Decode a Base64 encoded RSA private key string into an RSAPrivateKey object.
     *
     * @param privateKey the Base64 encoded RSA private key string
     * @return an RSAPrivateKey object
     * @throws NoSuchAlgorithmException if the RSA algorithm is not supported
     * @throws InvalidKeySpecException  if the provided key spec is invalid
     */
    private RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}
