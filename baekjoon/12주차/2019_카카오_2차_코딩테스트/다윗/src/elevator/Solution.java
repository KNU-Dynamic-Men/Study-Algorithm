package elevator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
/**
 * 외부 라이브러리
 * Gson v2.8.6
 * https://github.com/google/gson
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Solution {
	/*gsonUesed*/
	private static Gson gson = new Gson();
	/* String_url */
	private final String HOST_URL = "http://localhost:8000";
	/* start_api */
	private String userKey;
	private int problemId;
	private int numberOfElevators;
	/* datam */
	private String token = "";
	/* Commands */
	public static final String COMMAND_STOP = "STOP";
	public static final String COMMAND_ENTER = "ENTER";
	public static final String COMMAND_EXIT = "EXIT";
	public static final String COMMAND_UP = "UP";
	public static final String COMMAND_DOWN = "DOWN";
	public static final String COMMAND_OPEN = "OPEN";
	public static final String COMMAND_CLOSE = "CLOSE";
	/* Elevator Status */
	public static final String STATUS_STOPPED = "STOPPED";
	public static final String STATUS_OPENED = "OPENED";
	public static final String STATUS_UPWARD = "UPWARD";
	public static final String STATUS_DOWNWARD = "DOWNWARD";

	private CustomParser customParser = new CustomParser();
	private List<Elevator> elevatorList = new ArrayList<>();
	private List<Call> callList = new ArrayList<>();
	private List<Command> commandList = new ArrayList<>();

	public static final int MAXIMUM_NUMBER_OF_PEOPLE = 8;

	/* Direction of Elevators and Calls */
	public static final int DIR_UP = 1;
	public static final int DIR_DOWN = -1;

	/* Constraints */
	private int minHeight = 1;
	private int maxHeight;

	private int[] elevDirection; // default: 1 (DIR_UP)
	// call Direction은 Call.getDirection()을 이용한다.

	public Solution(String userKey, int problemId, int numberOfElevators) {
		this.userKey = userKey;
		this.problemId = problemId;
		this.numberOfElevators = numberOfElevators;
		this.elevDirection = new int[numberOfElevators];
		Arrays.fill(this.elevDirection, DIR_UP);
	}
	
	public void simulator() {
		start();
		switch (problemId) {
		case 0:
			System.out.println("APEACH_MANSION");
			this.maxHeight = 5;
			break;
		case 1:
			System.out.println("JAY_G_BUILDING");
			this.maxHeight = 25;
			break;
		case 2:
			System.out.println("RYAN_TOWER");
			this.maxHeight = 25;
			break;
		}
		while (true) {
			boolean is_finished = onCalls();
			if (is_finished)
				break;
			System.out.println("남은  Call 개수 :" + callList.size());
			generateCommands();
			
			action();
			
		}
	}

	// 다중 elevator를 위한 메소드
	private void generateCommands() {
		commandList = new ArrayList<>();

		for (Elevator elevator : elevatorList) {
			Command command = generateCommand(elevator);
			commandList.add(command);
		}
	}

	private Command generateCommand(Elevator elevator) {
		int eID = elevator.getId(); // elevator ID
		int curFloor = elevator.getFloor();
		String curStatus = elevator.getStatus();
		ArrayList<Call> curPassengers = elevator.getPassengers();
		int numOfCurPassengers = curPassengers.size();
		int curDir = elevDirection[eID];

		Command command = new Command(eID); // be returned

		/* checkList */
		boolean takeOff = curPassengers.stream().anyMatch((c) -> c.getEnd() == curFloor); // 내릴 손님 유무
		boolean takeOnSameDir = callList.stream()
				.anyMatch((c) -> c.getStart() == curFloor && c.getDirection() == curDir); // 같은 방향으로 갈 손님의 유무
		boolean takeOnDiffDir = callList.stream()
				.anyMatch((c) -> c.getStart() == curFloor && c.getDirection() != curDir); // 최상층, 반대방향으로 갈 손님의 유무

		/* 올라가는 중 */
		if (curDir == DIR_UP) {
			// 최 상층.
			if (curFloor == maxHeight) {
				// 1.올라가던 중이였으면 정지
				if (curStatus.equals(STATUS_UPWARD)) {
					command.setCommand(COMMAND_STOP);
				}
				// 2.정지된 상태면 탈사람, 내릴사람 판단하여 command
				else if (curStatus.equals(STATUS_STOPPED)) {

					if (takeOff || takeOnDiffDir) { // 내릴사람, 탈사람 있다면 문 열기
						command.setCommand(COMMAND_OPEN);
					} else { // 없다면 반대 방향으로 이동
						command.setCommand(COMMAND_DOWN);
						toggleElevDir(eID);
					}
				}
				// 3.문이 열려 있는 상태라면
				else if (curStatus.equals(STATUS_OPENED)) {
					// 3-1. 내릴사람이 있으면 내린다
					if (takeOff) {
						int[] callIds = new int[numOfCurPassengers];
						int index = 0;
						for (Call passenger : curPassengers) {
							callIds[index++] = passenger.getId();
						}
						command.setCommand(COMMAND_EXIT);
						command.setCallIds(callIds);
					}
					// 3-2. 탈사람이 있으면 태운다
					else if (takeOnDiffDir && MAXIMUM_NUMBER_OF_PEOPLE - numOfCurPassengers > 0) {
						// 태운 사람은 callList에서 삭제 다른 elevator가 중복으로 태우는 것 방지!
						int count = MAXIMUM_NUMBER_OF_PEOPLE - numOfCurPassengers;

						List<Call> removeList = new ArrayList<>();
						for (Call passenger : callList) { // 모든 call중 최상층, DOWN방향이면 다 태운다.
							if (passenger.getDirection() == DIR_DOWN && passenger.getStart() == maxHeight) {
								curPassengers.add(passenger);
								removeList.add(passenger);
								count--;
								if (count == 0)
									break;
							}
						}
						callList.removeAll(removeList);

						int[] callIds = new int[curPassengers.size()];
						int idx = 0;
						for (Call passenger : curPassengers) {
							callIds[idx++] = passenger.getId();
						}
						command.setCommand(COMMAND_ENTER);
						command.setCallIds(callIds);
					}

					// 3-3. 내릴사람, 탈사람이 더이상 없으면 문을닫고 반대방향으로 지정한다.
					else {
						command.setCommand(COMMAND_CLOSE);
						toggleElevDir(eID);
					}
				} // 최상층에서 문열려 있는 상태 끝

				// 상승중, 최상층 아님!
			} else {
				// 내릴사람이 있다
				if (takeOff) {

					// 1.올라가는 중 이면 STOP
					if (curStatus.equals(STATUS_UPWARD)) {
						command.setCommand(COMMAND_STOP);

						// 2.정지 상태이면 OPEN
					} else if (curStatus.equals(STATUS_STOPPED)) {
						command.setCommand(COMMAND_OPEN);

						// 3.문이 열려 있으면 내릴 사람들 내려주기
					} else if (curStatus.equals(STATUS_OPENED)) {
						List<Integer> takeOffList = new LinkedList<>();
						for (Call passenger : curPassengers) {
							if (passenger.getEnd() == curFloor) { // 내릴층이 같으면
								takeOffList.add(passenger.getId());
							}
						}
						int[] callIds = new int[takeOffList.size()];
						int index = 0;
						for (Integer id : takeOffList) {
							callIds[index++] = id;
						}
						command.setCommand(COMMAND_EXIT);
						command.setCallIds(callIds);
					}

					// 내릴사람이 없고 탈사람만 있으면서 탈 공간이 있다.
				} else if (takeOnSameDir && numOfCurPassengers < MAXIMUM_NUMBER_OF_PEOPLE) {

					// 올라가는 중 이면 STOP
					if (curStatus.equals(STATUS_UPWARD)) {
						command.setCommand(COMMAND_STOP);

						// 정지 상태이면 OPEN
					} else if (curStatus.equals(STATUS_STOPPED)) {
						command.setCommand(COMMAND_OPEN);

						// 문이 열려 있으면 태울 사람 태우기
					} else if (curStatus.equals(STATUS_OPENED)) {
						// 태운 사람은 callList에서 삭제 다른 elevator가 중복으로 태우는 것 방지!
						List<Integer> takeOnList = new LinkedList<>();
						List<Call> removeList = new ArrayList<>();
						int count = MAXIMUM_NUMBER_OF_PEOPLE - numOfCurPassengers;
						for (Call passenger : callList) { // 모든 call중 현재 층, UP방향이면 다 태운다.
							if (passenger.getDirection() == curDir && passenger.getStart() == curFloor) {
								takeOnList.add(passenger.getId());
								removeList.add(passenger);
								count--;
								if (count == 0)
									break;
							}
						}
						callList.removeAll(removeList);
						int[] callIds = new int[takeOnList.size()];
						int idx = 0;
						for (Integer id : takeOnList) {
							callIds[idx++] = id;
						}
						command.setCommand(COMMAND_ENTER);
						command.setCallIds(callIds);
					}

					// 내릴사람도 없고 탈사람도 없다 -> 올라가도록 함
				} else {

					// 문 열려있으면 문닫고
					if (curStatus.equals(STATUS_OPENED)) {
						command.setCommand(COMMAND_CLOSE);

						// 문 닫겨있거나 위로 올라가던 중이라면, 올라가거라
					} else {
						command.setCommand(COMMAND_UP);
					}
				}
			}
		} else if (curDir == DIR_DOWN) {// 내려가는 중
			// 최하층.
			if (curFloor == minHeight) {
				// 1.내려가던 중이였으면 정지
				if (curStatus.equals(STATUS_DOWNWARD)) {
					command.setCommand(COMMAND_STOP);
				}

				// 2.정지된 상태면 탈사람, 내릴사람 판단하여 command
				else if (curStatus.equals(STATUS_STOPPED)) {

					if (takeOff || takeOnDiffDir) { // 내릴사람, 탈사람 있다면 문 열기
						command.setCommand(COMMAND_OPEN);
					} else { // 없다면 반대 방향으로 이동
						command.setCommand(COMMAND_UP);
						toggleElevDir(eID);
					}
				}

				// 3.문이 열려 있는 상태라면
				else if (curStatus.equals(STATUS_OPENED)) {

					// 3-1. 내릴사람이 있으면 내린다
					if (takeOff) {
						int[] callIds = new int[numOfCurPassengers];
						int idx = 0;
						for (Call passenger : curPassengers) {
							callIds[idx++] = passenger.getId();
						}
						command.setCommand(COMMAND_EXIT);
						command.setCallIds(callIds);
					}

					// 3-2. 탈사람이 있으면 태운다
					else if (takeOnDiffDir && MAXIMUM_NUMBER_OF_PEOPLE - numOfCurPassengers > 0) {
						// 태운 사람은 callList에서 삭제, 다른 elevator가 중복으로 태우는 것 방지!
						int count = MAXIMUM_NUMBER_OF_PEOPLE - numOfCurPassengers;
						List<Call> removeList = new ArrayList<>();
						for (Call passenger : callList) { // 모든 call중 최상층, DOWN방향이면 다 태운다.
							if (passenger.getDirection() == DIR_UP && passenger.getStart() == minHeight) {
								curPassengers.add(passenger);
								removeList.add(passenger);
								count--;
								if (count == 0)
									break;
							}
						}
						callList.removeAll(removeList);
						int[] callIds = new int[curPassengers.size()];
						int idx = 0;
						for (Call passenger : curPassengers) {
							callIds[idx++] = passenger.getId();
						}
						command.setCommand(COMMAND_ENTER);
						command.setCallIds(callIds);
					}

					// 3-3. 내릴사람, 탈사람이 더이상 없으면 문을닫고 반대방향으로 지정한다.
					else {
						command.setCommand(COMMAND_CLOSE);
						toggleElevDir(eID);
					}
				}
				// 문 열려있는 상태 완료
			}
			// 내려가면서 최하층은 아닌경우
			else {
				// 내릴사람이 있다
				if (takeOff) {

					// 1.올라가는 중 이면 STOP
					if (curStatus.equals(STATUS_DOWNWARD)) {
						command.setCommand(COMMAND_STOP);

						// 2.정지 상태이면 OPEN
					} else if (curStatus.equals(STATUS_STOPPED)) {
						command.setCommand(COMMAND_OPEN);

						// 3.문이 열려 있으면 내릴 사람들 내려주기
					} else if (curStatus.equals(STATUS_OPENED)) {
						List<Integer> takeOffList = new LinkedList<>();
						for (Call passenger : curPassengers) {
							if (passenger.getEnd() == curFloor) { // 내릴층이 같으면
								takeOffList.add(passenger.getId());
							}
						}
						int[] callIds = new int[takeOffList.size()];
						int idx = 0;
						for (Integer id : takeOffList) {
							callIds[idx++] = id;
						}
						command.setCommand(COMMAND_EXIT);
						command.setCallIds(callIds);
					}

					// 내릴사람이 없고 탈사람만 있으면서 탈 공간이 있다.
				} else if (takeOnSameDir && numOfCurPassengers < MAXIMUM_NUMBER_OF_PEOPLE) {

					// 1.올라가는 중 이면 STOP
					if (curStatus.equals(STATUS_DOWNWARD)) {
						command.setCommand(COMMAND_STOP);

						// 2.정지 상태이면 OPEN
					} else if (curStatus.equals(STATUS_STOPPED)) {
						command.setCommand(COMMAND_OPEN);

						// 3.문이 열려 있으면 태울 사람 태우기
					} else if (curStatus.equals(STATUS_OPENED)) {
						// 태운 사람은 callList에서 삭제, 다른 elevator가 중복으로 태우는 것 방지!
						List<Integer> takeOnList = new LinkedList<>();
						List<Call> removeList = new ArrayList<>();
						int count = MAXIMUM_NUMBER_OF_PEOPLE - numOfCurPassengers;
						for (Call passenger : callList) { // 모든 call중 현재 층, UP방향이면 다 태운다.
							if (passenger.getDirection() == curDir && passenger.getStart() == curFloor) {
								takeOnList.add(passenger.getId());
								removeList.add(passenger);
								count--;
								if (count == 0)
									break;
							}
						}
						callList.removeAll(removeList);

						int[] callIds = new int[takeOnList.size()];
						int idx = 0;
						for (Integer id : takeOnList) {
							callIds[idx++] = id;
						}
						command.setCommand(COMMAND_ENTER);
						command.setCallIds(callIds);
					}

					// 내릴사람도 없고 탈사람도 없다 -> 올라가도록 함
				} else {

					// 문 열려있으면 문닫고
					if (curStatus.equals(STATUS_OPENED)) {
						command.setCommand(COMMAND_CLOSE);

						// 문 닫겨있거나 위로 올라가던 중이라면, 올라가거라
					} else {
						command.setCommand(COMMAND_DOWN);
					}

				}
			}
		}
		
		return command;
	}

	private void toggleElevDir(int eID) {
		this.elevDirection[eID] *= (-1);
	}

	public void start() {
		HttpURLConnection conn = null;
		JsonObject responseJson = null;

		try {
			URL url = new URL(
					this.HOST_URL + "/start/" + this.userKey + "/" + this.problemId + "/" + this.numberOfElevators);

			conn = (HttpURLConnection) url.openConnection();
			// Request
			conn.setRequestMethod("POST");
			// POST 파라미터 전달을 위한 설정
			conn.setDoOutput(true);

			// 보내고 결과값 받기
			int responseCode = conn.getResponseCode();
			if (responseCode == 400) {
				System.out.println(
						"400:: 해당 명령을 실행할 수 없음 (실행할 수 없는 상태일 때, 엘리베이터 수와 Command 수가 일치하지 않을 때, 엘리베이터 정원을 초과하여 태울 때)");
			} else if (responseCode == 401) {
				System.out.println("401:: X-Auth-Token Header가 잘못됨");
			} else if (responseCode == 403) {
				System.out.println("403:: user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재");
			} else if (responseCode == 500) {
				System.out.println("500:: 서버 에러, 문의 필요");
			} else { // 성공 후 응답 JSON 데이터받기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				responseJson = gson.fromJson(sb.toString(),JsonObject.class);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.token = responseJson.get("token").getAsString();
	}

	private boolean onCalls() {
		HttpURLConnection conn = null;
		JsonObject responseJson = null;

		try {
			URL url = new URL(this.HOST_URL + "/oncalls");
			conn = (HttpURLConnection) url.openConnection();
			// Request
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Auth-Token", this.token);
			// 읽기모드 지정
			conn.setDoInput(true);

			// 보내고 결과값 받기
			int responseCode = conn.getResponseCode();
			if (responseCode == 400) {
				System.out.println(
						"400:: 해당 명령을 실행할 수 없음 (실행할 수 없는 상태일 때, 엘리베이터 수와 Command 수가 일치하지 않을 때, 엘리베이터 정원을 초과하여 태울 때)");
			} else if (responseCode == 401) {
				System.out.println("401:: X-Auth-Token Header가 잘못됨");
			} else if (responseCode == 403) {
				System.out.println("403:: user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재");
			} else if (responseCode == 500) {
				System.out.println("500:: 서버 에러, 문의 필요");
			} else { // 성공 후 응답 JSON 데이터받기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				
				responseJson = gson.fromJson(sb.toString(),JsonObject.class);
				System.out.println("onCalls JSONDATA : " + responseJson + "\n");
				elevatorList.clear();
				callList.clear();

				elevatorList.addAll(customParser.getElevatorsFromOnCalls(responseJson));
				callList.addAll(customParser.getCallsFromOnCalls(responseJson));
				return responseJson.get("is_end").getAsBoolean();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void action() {
		HttpURLConnection conn = null;
		JsonObject responseJson = null;
		JsonArray commadArray = customParser.getCommandsJSONArray(commandList);

		try {
			URL url = new URL(this.HOST_URL + "/action");

			conn = (HttpURLConnection) url.openConnection();
			// Request
			conn.setRequestMethod("POST");
			conn.setRequestProperty("X-Auth-Token", this.token);
			conn.setRequestProperty("Content-Type", "application/json");

			// request에 data 준비
			conn.setDoOutput(true);// 쓰기모드준비
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			JsonObject commands = new JsonObject();
			commands.add("commands", commadArray);

			// request에 쓰기
			bw.write(commands.toString());
			bw.flush();
			bw.close();

			// 보내고 결과값 받기
			int responseCode = conn.getResponseCode();
			if (responseCode == 400) {
				System.out.println(
						"400:: 해당 명령을 실행할 수 없음 (실행할 수 없는 상태일 때, 엘리베이터 수와 Command 수가 일치하지 않을 때, 엘리베이터 정원을 초과하여 태울 때)");
			} else if (responseCode == 401) {
				System.out.println("401:: X-Auth-Token Header가 잘못됨");
			} else if (responseCode == 403) {
				System.out.println("403:: user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재");
			} else if (responseCode == 500) {
				System.out.println("500:: 서버 에러, 문의 필요");
			} else { // 성공 후 응답 JSON 데이터받기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				responseJson = gson.fromJson(sb.toString(),JsonObject.class);
				if (responseJson.get("is_end").getAsBoolean()) {
					System.out.print(responseJson.get("timestamp"));
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//	 	HTTP GET request
//		private JsonObject sendGet(ArrayList<String> targetUrl) throws Exception {
//			StringBuilder originUrl = new StringBuilder();
//			for(String tmp : targetUrl) {
//				originUrl.append("/");
//				originUrl.append(tmp);
//			}
//			URL url = new URL(originUrl.toString());
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET"); // optional default is GET
//			con.setRequestProperty("X-Auth-Token", token); // add request header
//			int responseCode = con.getResponseCode();
//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close(); // print result
//			System.out.println("HTTP 응답 코드 : " + responseCode);
//			System.out.println("HTTP body : " + response.toString());
//			JsonParser parser = new JsonParser();
//			return (JsonObject) parser.parse(response.toString());
//			
//		} // HTTP POST request
//
//		private JsonObject sendPost(ArrayList<String> targetUrl, JsonObject parameters) throws Exception {
//			StringBuilder originUrl = new StringBuilder();
//			for(String tmp : targetUrl) {
//				originUrl.append("/");
//				originUrl.append(tmp);
//			}
//			URL url = new URL(originUrl);
//			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//			con.setRequestMethod("POST"); // HTTP POST 메소드 설정
//			con.setRequestProperty("X-Auth-Token", token); // add request header
//			con.setRequestProperty("Content-Type", "application/json");
//			con.setDoOutput(true); // POST 파라미터 전달을 위한 설정
//			// Send post request
//			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//			wr.write(parameters);
//			wr.flush();
//			wr.close();
//			int responseCode = con.getResponseCode();
//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close(); // print result
//			System.out.println("HTTP 응답 코드 : " + responseCode);
//			System.out.println("HTTP body : " + response.toString());
//			JsonParser parser = new JsonParser();
//			return (JsonObject) parser.parse(response.toString());
//		}
//
//	public void gsonExample() {
//		String str = "[{'INDEX':0, 'TYPE':'동물', 'NAME':[{'str': '다윗','age':26},{'str':'성미','age':52}]}, {'INDEX':1, 'TYPE':'동물', 'NAME':[{'str': '다윗','age':26},{'str':'성미','age':52}]}]";
//		JsonParser parser = new JsonParser();
//		JsonArray jsonArray = (JsonArray) parser.parse(str);
//		System.out.println(jsonArray);
//
//		JsonObject object = (JsonObject) jsonArray.get(0);
//		System.out.println(object);
//
//		String index = object.get("INDEX").getAsInt();
//		String type = object.get("TYPE").getAsString();
//		JsonArray name = object.get("NAME").getAsJsonArray();
//		object = (JsonObject) name.get(0);
//
//		String str1 = object.get("str").getAsString();
//
//		System.out.println("INDEX : " + index);
//		System.out.println("TYPE : " + type);
//		System.out.println("NAME : " + name);
//		System.out.println(str1);
//	}
