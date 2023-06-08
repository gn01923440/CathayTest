package com.example.demo.currencyDto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResDto {
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date updated;
	private List<CurrentInfo> currentInfoList;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CurrentInfo {
		private String code;
		private String rate;
		private String codeCname;
	}
}
