package com.berk2s.dentist.infra.adapters.token.facade;

import com.berk2s.dentist.domain.authentication.event.GenerateToken;
import com.berk2s.dentist.infra.adapters.token.exceptions.TokenException;
import com.berk2s.dentist.infra.time.TimeUtils;
import com.berk2s.dentist.infra.config.JwtPkiConfiguration;
import com.berk2s.dentist.infra.config.ServerConfiguration;
import com.berk2s.dentist.infra.exceptions.ErrorDesc;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccessTokenFacade {

    private final IdGenerator idGenerator;
    private final JwtPkiConfiguration jwtPkiConfiguration;
    private final ServerConfiguration serverConfiguration;

    /**
     * Creates Json Web Token within given instructions
     */
    public SignedJWT createJwt(GenerateToken generateToken) {
        var notBeforeTime = TimeUtils.toDate(Optional.ofNullable(generateToken.getNotBeforeTime()));
        var expiryDateTime = TimeUtils.toDate(Optional.ofNullable(generateToken.getExpiryDateTime()));

        JWTClaimsSet.Builder jwtClaimsSetBuilder = new JWTClaimsSet.Builder()
                .subject(generateToken.getUserId().toString())
                .jwtID(idGenerator.generateId().toString())
                .audience(serverConfiguration.getServerUrl())
                .issuer(serverConfiguration.getServerUrl())
                .issueTime(new Date())
                .notBeforeTime(notBeforeTime)
                .expirationTime(expiryDateTime);

        for (Map.Entry<String, Object> claim: generateToken.getClaims().entrySet()) {
            jwtClaimsSetBuilder.claim(claim.getKey(), claim.getValue());
        }

        JWTClaimsSet jwtClaimsSet = jwtClaimsSetBuilder.build();

        log.info("Access token is generated for the User [userId: {}]",
                generateToken.getUserId().toString());

        return signJWT(jwtClaimsSet);
    }


    /**
     * Signs JWT with secret key
     */
    public SignedJWT signJWT(JWTClaimsSet jwtClaimsSet) {
        try {
            JWSHeader.Builder jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256);
            jwsHeader.keyID(jwtPkiConfiguration.getPublicKey().getKeyID());

            SignedJWT signedJWT =  new SignedJWT(jwsHeader.build(), jwtClaimsSet);
            signedJWT.sign(jwtPkiConfiguration.getJwsSigner());

            return signedJWT;
        } catch (JOSEException ex) {
            log.warn("Error while signing jwt [message: {}]", ex.getMessage());
            throw new TokenException(ErrorDesc.SERVER_ERROR.getDesc());
        }
    }

    /**
     * Validates and parses given token
     */
    public JWTClaimsSet parseAndValidate(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            signedJWT.verify(jwtPkiConfiguration.getJwsVerifier());

            return signedJWT.getJWTClaimsSet();
        } catch (JOSEException | ParseException ex) {
            log.warn("Error while parsing jwt [message: {}]", ex.getMessage());
            throw new TokenException(ErrorDesc.INVALID_TOKEN.getDesc());
        }
    }

    /**
     * Validates given token
     */
    public boolean validate(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.verify(jwtPkiConfiguration.getJwsVerifier());
        } catch (JOSEException | ParseException ex) {
            log.warn("Error while parsing jwt [message: {}]", ex.getMessage());
            throw new TokenException(ErrorDesc.INVALID_TOKEN.getDesc());
        }
    }
}
