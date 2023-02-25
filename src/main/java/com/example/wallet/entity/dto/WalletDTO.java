package com.example.wallet.entity.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @ClassName WalletDTO
 * @Author ZhuGuangLiang <786945363@qq.com>
 * @Date 2023/02/25 11:19
 */
@Data
public class WalletDTO {
	private Long userId;
	private BigDecimal amount;
}
