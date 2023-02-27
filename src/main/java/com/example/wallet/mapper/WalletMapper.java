package com.example.wallet.mapper;

import com.example.wallet.entity.BalanceRecord;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface WalletMapper {
	@Select("SELECT balance FROM user WHERE id = #{userId}")
	BigDecimal getBalance(Long userId);

	@Update("UPDATE user SET balance = balance - #{amount} WHERE id = #{userId}")
	Integer subtractBalance(@Param("userId") Long userId, @Param("amount") BigDecimal amount);

	@Update("UPDATE user SET balance = balance + #{amount} WHERE id = #{userId}")
	Integer addBalance(@Param("userId") Long userId, @Param("amount") BigDecimal amount);

	@Insert("INSERT INTO balance_record (user_id,amount,type) VALUES (#{userId},#{amount},#{type})")
	Integer createBalanceRecord(@Param("userId") Long userId, @Param("amount") BigDecimal amount, @Param("type") Integer type);

	@Select("SELECT amount,type,create_time FROM balance_record WHERE user_id = #{userId}")
	List<BalanceRecord> getBalanceRecord(Long userId);

}
