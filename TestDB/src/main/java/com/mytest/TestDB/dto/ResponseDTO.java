package com.mytest.TestDB.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO<T> {
	private String status;
	private List<T> data;
//temporary class... edit someday anyway...
}
