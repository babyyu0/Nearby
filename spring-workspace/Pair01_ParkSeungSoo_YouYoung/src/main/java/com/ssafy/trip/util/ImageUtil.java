package com.ssafy.trip.util;

import com.ssafy.trip.util.exception.ErrorMessage;
import com.ssafy.trip.util.exception.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
public class ImageUtil {
    @Value("${member.profile.img.url}")
    private static String MEMBER_PROFILE_IMG_URI;

    public static String saveMemberProfile(MultipartFile image) throws MyException {
        return saveIamge(MEMBER_PROFILE_IMG_URI, image);
    }

    public static String saveIamge(String path, MultipartFile image) throws MyException {
        String contentType = null;
        contentType = image.getContentType();  // 이미지 확장자명 찾기
        if (contentType == null || contentType.trim().equals("")) {  // 확장자명이 존재하지 않을 경우
            throw new MyException(ErrorMessage.IMAGE_NOT_FOUND);
        }

        String originalFileExtension;
        if (contentType.contains("image/jpeg")) {
            originalFileExtension = ".jpg";
        } else if (contentType.contains("image/png")) {
            originalFileExtension = ".png";
        } else {  // 확장자명이 이미지가 아닐 경우
            throw new MyException(ErrorMessage.IMAGE_NOT_FOUND);
        }

        String profileName = new Date().getTime() + originalFileExtension;
        File imgFile = new File(path + File.separator + profileName);
        log.info("파일 URL: " + imgFile.getAbsolutePath());
        boolean flag = imgFile.setExecutable(false);  // 실행 권한 없애기
        try {
            image.transferTo(imgFile);  // 이미지 저장
        } catch (IOException e) {
            throw new MyException(ErrorMessage.IMAGE_NOT_FOUND);
        }

        return profileName;
    }
}
