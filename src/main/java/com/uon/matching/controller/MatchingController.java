package com.uon.matching.controller;

import com.uon.matching.dto.Activity;
import com.uon.matching.model.service.MatchingService;
import com.uon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/activities")
@Slf4j
public class MatchingController {

    private final MatchingService matchingService;
    private final JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> insertMatchingRoom(@RequestBody Activity activity,
                                                @RequestHeader("Authorization") String tokenHeader) {
        String tokenUserId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
        activity.setUserId(tokenUserId);
        int isSuccess = matchingService.insertMatchingRoom(activity);

        if (isSuccess == 1) {
            return ResponseEntity.status(200).body("SUCCESS! CREATE MATCHING ROOM");
        }
        return ResponseEntity.status(400).body("FAILD! CREATE MATCHING ROOM");
    }
}
