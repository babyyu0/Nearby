package com.ssafy.trip.util;

import com.ssafy.trip.model.entity.MemberSec;
import com.ssafy.trip.model.repository.MemberSecRepository;
import com.ssafy.trip.util.exception.common.PasswordEncodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class MyPasswordEncoder extends BCryptPasswordEncoder {

    private final MemberSecRepository memberSecRepository;

    @Autowired
    public MyPasswordEncoder(MemberSecRepository memberSecRepository) {
        this.memberSecRepository = memberSecRepository;
    }

    public byte[] genKey() throws PasswordEncodeException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey key = keyGenerator.generateKey();
            return key.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new PasswordEncodeException();
        }
    }
    public SecretKey getKey(String memberId) throws PasswordEncodeException {
        MemberSec memberSec = memberSecRepository.findByMemberId(memberId).orElse(null);
        if (memberSec == null) {
            log.error("MyPasswordEncoder: MemberSec 미존재");
            throw new PasswordEncodeException();
        }
        byte[] encryptedKey = memberSec.getSecKey().getBytes();
        return new SecretKeySpec(encryptedKey, 0, encryptedKey.length, "AES");
    }
}