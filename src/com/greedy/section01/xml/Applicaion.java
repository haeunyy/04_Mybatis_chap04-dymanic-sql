package com.greedy.section01.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.greedy.common.SearchCriteria;

public class Applicaion {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("===== 마이바티스 동적 SQL =====");
			System.out.println("1. if 확인하기");
			System.out.println("2. choose(when, otherwise) 확인하기");
			System.out.println("3. foreach 확인하기");
			System.out.println("4. trim(where, set) 확인하기");
			System.out.println("9. 종료하기");
			System.out.print("메뉴 선택 : ");
			int no = sc.nextInt();
			
			switch(no) {
			case 1 : ifSubMenu(); break;
			case 2 : chooseSubMenu(); break;
			case 3 : foreachSubMenu(); break;
			case 4 : trimSubMenu(); break;
			case 9 : System.out.println("프로그램을 종료합니다."); return;
			}
		} while(true);
	}
	
	private static void ifSubMenu() {
		
		Scanner sc = new Scanner(System.in);
		MenuService menuService = new MenuService();
		
		do {
			System.out.println("===== if 서브 메뉴 =====");
			System.out.println("1. 원하는 금액대에 적합한 추천 메뉴 목록 보여주기");
			System.out.println("2. 메뉴 이름 혹은 카테고리 명으로 검색하여 메뉴 목록 보여주기");
			System.out.println("9. 이전 메뉴로");
			System.out.print("메뉴 번호 입력 : ");
			int no = sc.nextInt();
			
			switch(no) {
			case 1 : menuService.selectMenuByPrice(inputPrice()); break;
			case 2 : menuService.searchMenu(inputSearchCriteria()); break;
			case 9 : return;
			}
			
		} while(true);
		
	}
	
	private static int inputPrice() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("검색하실 가격의 최대 금액을 입력 해주세요 : ");
		
		return sc.nextInt();
	}
	
	private static SearchCriteria inputSearchCriteria() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("검색 기준을 입력해주세요(name or category) : ");
		String condition = sc.nextLine();
		System.out.print("검색어를 입력해주세요 : ");
		String value = sc.nextLine();
		
		return new SearchCriteria(condition, value);
	}

	private static void chooseSubMenu() {
		
		Scanner sc = new Scanner(System.in);
		MenuService menuService = new MenuService();
		
		do {
			System.out.println("===== choose 서브 메뉴 =====");
			System.out.println("1. 카테고리 상위 분류별 메뉴 보여주기(식사, 음료, 디저트)");
			System.out.println("9. 이전 메뉴로");
			System.out.print("메뉴 번호를 입력하세요 : ");
			int no = sc.nextInt();
			
			switch(no) {
			case 1 : menuService.searchMenuBySupCategory(inputSupCategory()); break;
			case 9 : return;
			}
			
		} while(true);
		
	}

	private static SearchCriteria inputSupCategory() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("상위 분류를 입력해주세요(식사, 음료, 디저트) : ");
		String value = sc.nextLine();
		
		return new SearchCriteria("category", value);
	}


	private static void foreachSubMenu() {
		
		Scanner sc = new Scanner(System.in);
		MenuService menuService = new MenuService();
		
		do {
			System.out.println("===== foreach 서브 메뉴 =====");
			System.out.println("1. 랜덤한 메뉴 5개 추출해서 조회하기");
			System.out.println("9. 이전 메뉴로");
			System.out.print("메뉴 번호를 입력하세요 : ");
			int no = sc.nextInt();
			
			switch(no) {
			case 1 : menuService.searchMenuByRandomMenuCode(createRandomMenuCodeList()); break;
			case 9 : return;
			}
			
		} while(true);
		
	}

	private static List<Integer> createRandomMenuCodeList() {
		
		Set<Integer> set = new HashSet<>();
		while(set.size() < 5) {
			/* 현재 메뉴 번호는 1 ~ 21번 사이이다. */
			int temp = ((int) (Math.random() * 21)) + 1;
			set.add(temp);
		}
		
		List<Integer> list = new ArrayList<>(set);
		
		return list;
	}
	

	private static void trimSubMenu() {
		
		Scanner sc = new Scanner(System.in);
		MenuService menuService = new MenuService();
		
		do {
			System.out.println("===== trim(where, set) 서브 메뉴 =====");
			System.out.println("1. 메뉴 혹은 카테고리 코드로 검색, 단 메뉴와 카테고리 둘 다 일치하는 경우도 검색하며,"
					+ " 검색 조건이 없는 경우 전체 검색");
			System.out.println("2. 원하는 메뉴 정보만 수정하기");
			System.out.println("9. 이전 메뉴로");
			System.out.print("메뉴 번호를 입력하세요 : ");
			int no = sc.nextInt();
			
			switch(no) {
			case 1 : menuService.searchMenuByNameOrCategory(inputSearchCriteriaMap()); break;
			case 2 : menuService.modifyMenu(inputChangeInfo()); break;
			case 9 : return;
			}
			
		} while(true);
		
	}

	private static Map<String, Object> inputSearchCriteriaMap() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 조건을 입력하세요(category or name or both or null) : ");
		String condition = sc.nextLine();
		
		Map<String, Object> criteria = new HashMap<>();
		
		if("category".equals(condition)) {
			
			System.out.print("검색할 카테고리 코드 입력 : ");
			int categoryValue = sc.nextInt();
			
			criteria.put("categoryValue", categoryValue);
			
		} else if("name".equals(condition)) {
			
			System.out.print("검색할 메뉴 이름 입력 : ");
			String nameValue = sc.nextLine();
			
			criteria.put("nameValue", nameValue);
			
		} else if("both".equals(condition)) {
			
			System.out.print("검색할 카테고리 코드 입력 : ");
			int categoryValue = sc.nextInt();
			sc.nextLine();
			System.out.print("검색할 메뉴 이름 입력 : ");
			String nameValue = sc.nextLine();
			
			criteria.put("categoryValue", categoryValue);
			criteria.put("nameValue", nameValue);
		}
		
		return criteria;
	}

	private static Map<String, Object> inputChangeInfo() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("변경할 메뉴 코드 입력 : ");
		int code = sc.nextInt();
		System.out.print("변경할 메뉴 이름 입력 : ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("변경할 카테고리 코드 입력 : ");
		int categoryCode = sc.nextInt();
		System.out.print("판매 여부 결정(Y/N) : ");
		sc.nextLine();
		String orderableStatus = sc.nextLine();
		
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("code", code);
		criteria.put("name", name);
		criteria.put("categoryCode", categoryCode);
		criteria.put("orderableStatus", orderableStatus);
		
		return criteria;
	}
	
	
	

		
		
		
	}


