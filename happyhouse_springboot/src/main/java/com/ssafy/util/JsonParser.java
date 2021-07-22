package com.ssafy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
	// 동별 카페 데이터수: 20642개
	static String serviceKey = "%2F5ak0IYnWsjtN%2BWfDj8Jqa5raHLkN2zEVpqTOIu4MhvtvIa6lsuk4mGhJ6P4Ov0RtFnzsNMcyP%2FNEw%2FdF7xBkQ%3D%3D";

	public static void main(String[] args) {
		// 행정동별
		getCoffeeShop();
		// 업종별
		getStore();
	}

	// 행정동별
	private static void getCoffeeShop() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnect();
			for (int pn = 1; pn <= 206; pn++) {
				try {
					String urlStr = "http://apis.data.go.kr/B553077/api/open/sdsc/storeListInDong?" + "divId=ctprvnCd"
							+ "&key=11&indsSclsCd=Q12A01" 
							+ "&numOfRows=100" 
							+ "&pageNo=" + pn 
							+ "&type=json"
							+ "&ServiceKey=" + serviceKey;
					URL url = new URL(urlStr);
					String line = "";
					String result = "";
					BufferedReader br;
					br = new BufferedReader(new InputStreamReader(url.openStream()));
					while ((line = br.readLine()) != null) {
						result = result.concat(line);
						// System.out.println(line);
					}
					// JSON parser 만들어 문자열 데이터를 객체화한다.
					JSONParser parser = new JSONParser();
					JSONObject obj = (JSONObject) parser.parse(result);
					JSONObject body = (JSONObject) obj.get("body");
					JSONArray items = (JSONArray) body.get("items");

					for (int i = 0; i < items.size(); i++) {
						JSONObject item = (JSONObject) items.get(i);
						String cno = (String) item.get("bizesId"); 
						String cname = (String) item.get("bizesNm"); 
						String brachname = (String) item.get("brchNm"); 
						String sido_code = (String) item.get("ctprvnCd"); 
						String city = (String) item.get("ctprvnNm"); 
						String gugun_code = (String) item.get("signguCd"); 
						String gugun_name = (String) item.get("signguNm");
						String dongcode = (String) item.get("ldongCd"); 
						String dong = (String) item.get("ldongNm"); 
						String address1 = (String) item.get("lnoAdr");
						String address2 = (String) item.get("rdnmAdr"); 
						String lng = (double) item.get("lon") + ""; 
						String lat = (double) item.get("lat") + "";
						// 해제시 미세먼지농도
						StringBuilder sql = new StringBuilder();
						sql.append("insert into coffeeshop values \n");
						sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						pstmt = conn.prepareStatement(sql.toString());
						pstmt.setString(1, cno);
						pstmt.setString(2, cname);
						pstmt.setString(3, brachname);
						pstmt.setString(4, sido_code);
						pstmt.setString(5, city);
						pstmt.setString(6, gugun_code);
						pstmt.setString(7, gugun_name);
						pstmt.setString(8, dongcode);
						pstmt.setString(9, dong);
						pstmt.setString(10, address1);
						pstmt.setString(11, address2);
						pstmt.setString(12, lng);
						pstmt.setString(13, lat);
						if (i == items.size() - 1)
							sql.setLength(sql.length() - 1);
						pstmt.executeUpdate();
					}
//            System.out.println("저장완료");
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(pn + "번 저장 끝");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

	}

	// 업종별
	static void getStore() {
		try {
			String urlStr = "http://apis.data.go.kr/B553077/api/open/sdsc/storeListInUpjong?" + "divId=indsMclsCd"
					+ "&key=Q12" + "&type=json" + "&ServiceKey=" + serviceKey;

			URL url = new URL(urlStr);

			String line = "";
			String result = "";

			BufferedReader br;
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = br.readLine()) != null) {
				result = result.concat(line);
				// System.out.println(line);
			}

			// JSON parser 만들어 문자열 데이터를 객체화한다.
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(result);
			JSONObject body = (JSONObject) obj.get("body");
			JSONArray items = (JSONArray) body.get("items");

			String miseType = "";

			for (int i = 0; i < items.size(); i++) {
				JSONObject item = (JSONObject) items.get(i);
				String dataDate = (String) item.get("bizesId"); // 발령날짜
				String districtName = (String) item.get("bizesNm"); // 발령지역
				String moveName = (String) item.get("indsMclsNm"); // 발령권역
				String issueDate = (String) item.get("ksicNm"); // 발령일자
				String issueTime = (String) item.get("lnoAdr"); // 발령시간
				Long issueVal = (Long) item.get("bldMnno"); // 발령농도
				String itemCode = (String) item.get("bldMngNo"); // 미세먼지 구분 PM10, PM25
				String issueGbn = (String) item.get("issueGbn"); // 경보단계 : 주의보/경보
				String clearDate = (String) item.get("rdnmAdr"); // 해제일자
				String clearTime = (double) item.get("lon") + ""; // 해제시간
				String clearVal = (double) item.get("lat") + ""; // 해제시 미세먼지농도

				StringBuffer sb = new StringBuffer();
				sb.append("위도 : " + clearTime);
				System.out.println(sb.toString());
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
