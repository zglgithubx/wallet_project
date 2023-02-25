package com.example.wallet.service;

import com.example.wallet.entity.BalanceRecord;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
	BigDecimal getBalance(Long userId);

	void recharge(Long userId, BigDecimal amount) throws Exception;

	void consume(Long userId, BigDecimal amount) throws Exception;

	void drawback(Long userId, BigDecimal amount) throws Exception;

	List<BalanceRecord> getBalanceRecord(Long userId);
}
