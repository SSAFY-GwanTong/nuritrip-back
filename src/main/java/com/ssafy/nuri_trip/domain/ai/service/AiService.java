package com.ssafy.nuri_trip.domain.ai.service;

import com.ssafy.nuri_trip.domain.ai.repository.AiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {
    private final AiRepository aiRepo;

}
