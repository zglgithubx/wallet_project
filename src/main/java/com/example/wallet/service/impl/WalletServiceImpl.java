package com.example.wallet.service.impl;

import com.example.wallet.entity.BalanceRecord;
import com.example.wallet.mapper.WalletMapper;
import com.example.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName WalletServiceImpl
 * @Author ZhuGuangLiang <786945363@qq.com>
 * @Date 2023/02/25 11:16
 */
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
	private final WalletMapper walletMapper;

	@Override
	public BigDecimal getBalance(Long userId) {
		return walletMapper.getBalance(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void recharge(Long userId, BigDecimal amount) throws Exception {
		Integer addRes = walletMapper.addBalance(userId, amount);
		if (addRes == 0) {
			throw new Exception("更新用户余额失败");
		}
		Integer tye = 1;
		Integer createRes = walletMapper.createBalanceRecord(userId, amount, tye);
		if (createRes == 0) {
			throw new Exception("添加用户余额变更记录失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void consume(Long userId, BigDecimal amount) throws Exception {
		Integer subRes = walletMapper.subtractBalance(userId, amount);
		if (subRes == 0) {
			throw new Exception("消费失败");
		}
		Integer tye = 2;
		Integer createRes = walletMapper.createBalanceRecord(userId, amount, tye);
		if (createRes == 0) {
			throw new Exception("添加用户余额变更记录失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void drawback(Long userId, BigDecimal amount) throws Exception {
		Integer addRes = walletMapper.addBalance(userId, amount);
		if (addRes == 0) {
			throw new Exception("退款失败");
		}
		Integer tye = 3;
		Integer createRes = walletMapper.createBalanceRecord(userId, amount, tye);
		if (createRes == 0) {
			throw new Exception("添加用户余额变更记录失败");
		}

	}

	@Override
	public List<BalanceRecord> getBalanceRecord(Long userId) {
		return walletMapper.getBalanceRecord(userId);
	}
}
