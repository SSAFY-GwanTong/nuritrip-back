package com.ssafy.nuri_trip.domain.ai.service;

import com.ssafy.nuri_trip.domain.ai.dto.AiPlan;
import com.ssafy.nuri_trip.domain.ai.dto.AiPlanDetail;
import com.ssafy.nuri_trip.domain.ai.dto.AiServicePlan;
import com.ssafy.nuri_trip.domain.ai.dto.PossibleAiPlan;
import com.ssafy.nuri_trip.domain.ai.repository.AiPlanRepository;
import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import com.ssafy.nuri_trip.domain.attraction.repository.AttractionRepository;
import com.ssafy.nuri_trip.domain.user.dto.User;
import com.ssafy.nuri_trip.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {
    private final AiPlanRepository aiRepo;
    private final UserRepository userRepo;
    private final AttractionRepository attrRepo;

    public List<Attraction> getAiPlans(AiServicePlan.Request request){
        User user = userRepo.selectById(request.getUserPrimaryKey());
        Integer strengthLevel = user.getStrengthLevel();
        List<PossibleAiPlan> possibleAiPlans = aiRepo.selectPossiblePlanListByLevelAndAreaCode(strengthLevel, request.getSidoCode());
        //TODO
        List<AiPlanDetail> aiPlanDetails = aiRepo.selectAiPlanDetailListByAiPlanId(possibleAiPlans.get(0).getAiPlanId(), request.getDuration());
//        System.out.println("aiPlanDetails = " + aiPlanDetails);
        List<Long> list = aiPlanDetails.stream().map((a -> a.getAttractionId())).toList();
        System.out.println("list = " + list);
        List<Attraction> attractions = attrRepo.selectAttractionByIdList(list);
        System.out.println("attractions = " + attractions);
        return attractions;
    }

}
