package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.logic.ChallengeSituationAnalysis;
import com.KioskSNU.snu.logic.ChallengeSuccessCheck;
import com.KioskSNU.snu.mapper.ParticipationChallengeMapper;

import java.util.List;

public interface ParticipationChallengeService extends ParticipationChallengeMapper,
            										   ChallengeSuccessCheck,
            										   ChallengeSituationAnalysis {
}
