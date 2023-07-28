package com.KioskSNU.snu.service;

import com.KioskSNU.snu.logic.PaymentCart;
import com.KioskSNU.snu.logic.PaymentSituationAnalysis;
import com.KioskSNU.snu.mapper.PaymentMapper;

public interface PaymentService extends PaymentMapper, PaymentSituationAnalysis, PaymentCart {}
