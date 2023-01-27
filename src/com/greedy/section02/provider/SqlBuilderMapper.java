package com.greedy.section02.provider;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.greedy.common.MenuDTO;

public interface SqlBuilderMapper {

	@InsertProvider(type=SqlBuilderProvider.class, method="registMenu")
	int registMenu(MenuDTO menu);

	@UpdateProvider(type=SqlBuilderProvider.class, method="modifyMenu")
	int modifyMenu(MenuDTO menu);

	/* Map이나 getter가 있는 DTO가 아닌 기본 자료형 값을 전달해야 하는 경우에는 @Param 어노테이션을 이용한다.
	 * 또는 전달해야 하는 값이 2개 이상인 경우도 @Param 어노테이션을 이용할 수 있다.
	 * 단, Provider 메소드의 매개변수 선언부는 없어야 한다. */
	@DeleteProvider(type=SqlBuilderProvider.class, method="deleteMenu")
	int deleteMenu(@Param("code") int code);

	
}
	
