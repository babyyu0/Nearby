package com.ssafy.trip.model.service.common;

import com.ssafy.trip.util.exception.common.FileNotFoundException;

public interface ImageService {
    byte[] imgToByteArray(String url, String imgName) throws FileNotFoundException;
}
