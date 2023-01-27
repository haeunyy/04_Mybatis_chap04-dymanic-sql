package com.greedy.section02.provider;

import org.apache.ibatis.jdbc.SQL;

import com.greedy.common.SearchCriteria;

public class SelectBuilderProvider {
	
	/* SQL문을 문자열 형태로 반환하도록 반환 타입을 String으로 지정한다. */
	public String selectAllMenu() {
		
		return new SQL()
				.SELECT("MENU_CODE")
				.SELECT("MENU_NAME")
				.SELECT("MENU_PRICE")
				.SELECT("CATEGORY_CODE")
				.SELECT("ORDERABLE_STATUS")
				.FROM("TBL_MENU")
				.WHERE("ORDERABLE_STATUS = 'Y'").toString();
	}
	
	public String searchMenuByCondition(SearchCriteria searchCriteria) {
		
		SQL sql = new SQL();
		
		sql.SELECT("MENU_CODE")
		.SELECT("MENU_NAME")
		.SELECT("MENU_PRICE")
		.SELECT("CATEGORY_CODE")
		.SELECT("ORDERABLE_STATUS")
		.FROM("TBL_MENU");
		
		if("category".equals(searchCriteria.getCondition())) {
			sql.JOIN("TBL_CATEGORY USING(CATEGORY_CODE)")
			   .WHERE("ORDERABLE_STATUS = 'Y'")
			   .AND()
			   .WHERE("CATEGORY_NAME = #{ value }");
		} else if ("name".equals(searchCriteria.getCondition())) {
			/* 가변인자를 이용하면 자동 AND로 처리하기 때문에 OR를 사용해야 하는 경우는 .OR() 메소드를 사용한다. */
			sql.WHERE("ORDERABLE_STATUS = 'Y'", "MENU_NAME LIKE '%' || #{ value } || '%'");
		}
		
		return sql.toString();
		
	}

	
}
