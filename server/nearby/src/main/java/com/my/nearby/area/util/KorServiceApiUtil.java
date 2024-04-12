package com.my.nearby.area.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Objects;

@Component
public class KorServiceApiUtil {
    private static RestClient restClient;

    public KorServiceApiUtil(@Value("${api.korservice.key}") String secretKey) {
        restClient = RestClient.builder()
                .baseUrl("https://apis.data.go.kr/B551011/KorService1?serviceKey={serviceKey}&MobileOS={MobileOS}&MobileApp={MobileApp}&_type={_type}")
                .defaultUriVariables(new HashMap<>(){{
                    put("serviceKey", secretKey);
                    put("MobileOS", "ETC");
                    put("MobileApp", "nearby");
                    put("_type", "json");
                }})
                .build();
    }

    public static JsonNode getAreaCode(String areaCode) {
        JsonNode result = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/areaCode1")
                        .queryParam("areaCode", areaCode)
                        .build())
                .exchange((clientRequest, clientResponse) -> {
                    if(clientResponse.getHeaders().containsKey("content-type") && clientResponse.getHeaders().get("content-type").get(0).equals("application/json")) {
                        return Objects.requireNonNull(clientResponse.bodyTo(JsonNode.class));
                    }
                    throw new RuntimeException("공공데이터포털 API /areaCode1 오류");
                });
        System.out.println(result);
        return result.get("response").get("body").get("items").get("item");
    }
}
