package com.example.wallet.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName BalanceRecord
 * @Author ZhuGuangLiang <786945363@qq.com>
 * @Date 2023/02/25 11:15
 */
@Data
public class BalanceRecord {
	private BigDecimal amount;
	private Integer type;
	private LocalDateTime createTime;
}
