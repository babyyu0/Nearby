package com.ssafy.trip.global.util;

import com.ssafy.trip.global.util.exception.ErrorMessage;
import com.ssafy.trip.global.util.exception.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
public class ImageUtil {
    
    public static String saveIamge(String path, MultipartFile image) throws MyException {
        String contentType = image.getContentType();  // 이미지 확장자명 찾기
        if (contentType == null || contentType.trim().isEmpty()) {  // 확장자명이 존재하지 않을 경우
            log.debug("saveMemberProfile: {}", "이미지 확장자명이 존재하지 않음");
            throw new MyException(ErrorMessage.IMAGE_NOT_FOUND);
        }

        String originalFileExtension;
        if (contentType.contains("image/jpeg")) {
            originalFileExtension = ".jpg";
        } else if (contentType.contains("image/png")) {
            originalFileExtension = ".png";
        } else {  // 확장자명이 이미지가 아닐 경우
            log.debug("saveMemberProfile: {}", "확장자명이 이미지가 아님");
            throw new MyException(ErrorMessage.IMAGE_NOT_FOUND);
        }

        String profileName = new Date().getTime() + originalFileExtension;
        File imgFile = new File(path + File.separator + profileName);
        log.debug("파일 URL: " + imgFile.getAbsolutePath());
        boolean flag = imgFile.setExecutable(false);  // 실행 권한 없애기
        try {
            image.transferTo(imgFile);  // 이미지 저장
        } catch (IOException e) {
            log.debug("saveMemberProfile: {}", "이미지 저장 실패");
            throw new MyException(ErrorMessage.IMAGE_NOT_FOUND);
        }

        return profileName;
    }

    public static byte[] toByteArray(String url, String imageName) throws MyException {
        byte[] images = null;

        try {
            InputStream inputStream = new FileInputStream(url + File.separator + imageName);
            images = inputStream.readAllBytes();
            inputStream.close();
        } catch (IOException e) {
            log.debug("toByteArray: {}", "이미지를 Byte Array로 변경 불가");
            return null;
        }

        return images;
    }
}
