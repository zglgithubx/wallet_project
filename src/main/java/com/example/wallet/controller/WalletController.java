package com.example.wallet.controller;

import com.example.wallet.entity.BalanceRecord;
import com.example.wallet.entity.dto.WalletDTO;
import com.example.wallet.service.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName WalletController
 * @Author ZhuGuangLiang <786945363@qq.com>
 * @Date 2023/02/25 11:14
 */
@Api(value = "WalletAPI")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {
	private final WalletService walletservice;

	@ApiOperation(value = "根据用户ID查询钱包余额")
	@GetMapping("/balance/{userId}")
	public BigDecimal getBalance(@PathVariable Long userId) {
		return walletservice.getBalance(userId);
	}

	@ApiOperation(value = "充值")
	@PostMapping("/recharge")
	public void recharge(@RequestBody WalletDTO walletDTO) throws Exception {
		walletservice.recharge(walletDTO.getUserId(), walletDTO.getAmount());
	}

	@ApiOperation(value = "消费")
	@PostMapping("/consume")
	public void consume(@RequestBody WalletDTO walletDTO) throws Exception {
		walletservice.consume(walletDTO.getUserId(), walletDTO.getAmount());
	}


	@ApiOperation(value = "退款")
	@PostMapping("/drawback")
	public void drawback(@RequestBody WalletDTO walletDTO) throws Exception {
		walletservice.drawback(walletDTO.getUserId(), walletDTO.getAmount());
	}

	@ApiOperation(value = "查询余额变动明细")
	@GetMapping("/balanceRecord/{userId}")
	public List<BalanceRecord> getBalanceRecord(@PathVariable Long userId) {
		return walletservice.getBalanceRecord(userId);
	}
}
