package com.ssafy.trip.model.service.common;

import com.ssafy.trip.util.exception.common.FileNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] imgToByteArray(String url, String imgName) throws FileNotFoundException {
        // 이미지를 byte array로 변환 (blob)
        byte[] imageByteArray;
        try {
            InputStream imageStream = new FileInputStream(url + File.separator + imgName);
            imageByteArray = imageStream.readAllBytes();
            imageStream.close();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }

        return imageByteArray;
    }
}
