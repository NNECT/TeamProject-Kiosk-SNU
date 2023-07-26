package com.KioskSNU.snu.service;

import com.KioskSNU.snu.logic.CommutationTicketSituationAnalysis;
import com.KioskSNU.snu.mapper.UsageCommutationTicketMapper;

public interface UsageCommutationTicketService extends UsageCommutationTicketMapper, CommutationTicketSituationAnalysis {}
