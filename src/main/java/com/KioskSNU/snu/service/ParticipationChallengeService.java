package com.KioskSNU.snu.service;

import com.KioskSNU.snu.logic.ChallengeSituationAnalysis;
import com.KioskSNU.snu.logic.ParticipationChallengeStatus;
import com.KioskSNU.snu.mapper.ParticipationChallengeMapper;

public interface ParticipationChallengeService extends ParticipationChallengeMapper, ParticipationChallengeStatus, ChallengeSituationAnalysis {}
