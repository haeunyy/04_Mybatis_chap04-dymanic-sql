package com.greedy.section02.provider;

import org.apache.ibatis.jdbc.SQL;

import com.greedy.common.MenuDTO;

public class SqlBuilderProvider {
	
	public String registMenu(MenuDTO menu) {
		
		SQL sql = new SQL();
		
		sql.INSERT_INTO("TBL_MENU")
		   .VALUES("MENU_CODE", "SEQ_MENU_CODE.NEXTVAL")
		   .VALUES("MENU_NAME", "#{ menuName }")
		   .VALUES("MENU_PRICE", "#{ menuPrice }")
		   .VALUES("CATEGORY_CODE", "#{ categoryCode }")
		   .VALUES("ORDERABLE_STATUS", "#{ orderableStatus }");
		
		return sql.toString();
	}

	public String modifyMenu(MenuDTO menu) {
		
		SQL sql = new SQL();
		
		/* 비어 있지 않은 값만 업데이트 되는 동적 쿼리로 작성한다. */
		sql.UPDATE("TBL_MENU");
		
		if(menu.getMenuName() != null && !"".equals(menu.getMenuName())) {
			sql.SET("MENU_NAME = #{ menuName }");
		}
		
		if(menu.getMenuPrice() > 0) {
			sql.SET("MENU_PRICE = #{ menuPrice }");
		}
		
		if(menu.getCategoryCode() > 0) {
			sql.SET("CATEGORY_CODE = #{ categoryCode }");
		}
		
		if(menu.getOrderableStatus() != null && !"".equals(menu.getOrderableStatus())) {
			sql.SET("ORDERABLE_STATUS = #{ orderableStatus }");
		}
		
		sql.WHERE("MENU_CODE = #{ menuCode }");
		
		return sql.toString();
	}
	
	public String deleteMenu() {
		
		SQL sql = new SQL();
		
		sql.DELETE_FROM("TBL_MENU")
		   .WHERE("MENU_CODE = #{ code }");
		
		return sql.toString();
	}
	

	
	

	
}
