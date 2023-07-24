package com.KioskSNU.snu.service;

import com.KioskSNU.snu.logic.AccountCommutationTicketCheck;
import com.KioskSNU.snu.mapper.AccountMapper;

public interface AccountService extends AccountMapper, AccountCommutationTicketCheck {}
