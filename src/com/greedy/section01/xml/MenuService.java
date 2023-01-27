package com.greedy.section01.xml;
import static com.greedy.common.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.greedy.common.MenuDTO;
import com.greedy.common.SearchCriteria;

public class MenuService {
	
	private DynamicSqlMapper mapper;

	public void selectMenuByPrice(int price) {
		
		SqlSession sqlSession = getSqlSession();
		mapper = sqlSession.getMapper(DynamicSqlMapper.class);
		
		/* 기본 자료형으로는 조건문의 값을 비교하기 어렵다. Map의 key 또는 DTO의 getter를 이용해서 값을 확인한다. */
		Map<String,Integer> map = new HashMap<>();
		map.put("price", price);
		
		List<MenuDTO> menuList = mapper.selectMenuByPrice(map);
		
		if( menuList!=null && !menuList.isEmpty() ) {
			for(MenuDTO menu : menuList ) {
				System.out.println(menu);
			}
		} else {
			System.out.println("검색 결과가 존재하지 않습니다. ");
		}
		
	}

	public void searchMenu(SearchCriteria searchCriteria) {
		
		SqlSession sqlSession = getSqlSession();
		mapper = sqlSession.getMapper(DynamicSqlMapper.class);
		
		List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);
		
		if( menuList!=null && !menuList.isEmpty() ) {
			for(MenuDTO menu : menuList ) {
				System.out.println(menu);
			}
		} else {
			System.out.println("검색 결과가 존재하지 않습니다. ");
		}
	}

	public void searchMenuBySupCategory(SearchCriteria searchCriteria) {
		
		SqlSession sqlSession = getSqlSession();
		mapper = sqlSession.getMapper(DynamicSqlMapper.class);
		
		List<MenuDTO> menuList = mapper.searchMenuBySupCategory(searchCriteria);
		
		if( menuList!=null && !menuList.isEmpty() ) {
			for(MenuDTO menu : menuList ) {
				System.out.println(menu);
			}
		} else {
			System.out.println("검색 결과가 존재하지 않습니다. ");
		}
	}

	public void searchMenuByRandomMenuCode(List<Integer> randomMenuCodeList) {
		SqlSession sqlSession = getSqlSession();
		mapper = sqlSession.getMapper(DynamicSqlMapper.class);
		
		Map<String,List<Integer>> criteria = new HashMap<>();
		criteria.put("randomMenuCodeList", randomMenuCodeList);
		
		List<MenuDTO> menuList = mapper.searchMenuByRandomMenuCode(criteria);
		
		if( menuList!=null && !menuList.isEmpty() ) {
			for(MenuDTO menu : menuList ) {
				System.out.println(menu);
			}
		} else {
			System.out.println("검색 결과가 존재하지 않습니다. ");
		}
		sqlSession.close();
	}

	public void searchMenuByNameOrCategory(Map<String,Object> searchCriteria) {
		SqlSession sqlSession = getSqlSession();
		mapper = sqlSession.getMapper(DynamicSqlMapper.class);
		
		List<MenuDTO> menuList = mapper.searchMenuByNameOrCategory(searchCriteria);
		
		if( menuList!=null && !menuList.isEmpty() ) {
			for(MenuDTO menu : menuList ) {
				System.out.println(menu);
			}
		} else {
			System.out.println("검색 결과가 존재하지 않습니다. ");
		}
		sqlSession.close();
	}

	public void modifyMenu(Map<String, Object> changeInfo) {
		
		SqlSession sqlSession = getSqlSession();
		mapper = sqlSession.getMapper(DynamicSqlMapper.class);
		
		int result = mapper.modifyMenu(changeInfo);
		
		if(result > 0) {
			sqlSession.commit();
			System.out.println("메뉴 정보 변경에 성공하였습니다. ");
		} else {
			sqlSession.rollback();
			System.out.println("메뉴 정보 변경에 실패하였습니다. ");
		}
		
		sqlSession.close();
	}
	
	
	
	}

	
	
	
	

