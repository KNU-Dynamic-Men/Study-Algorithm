package elevator;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CustomParser {
	public ArrayList<Elevator> getElevatorsFromOnCalls(JsonObject responseJson) {
		ArrayList<Elevator> elevatorList = new ArrayList<>();

		try {
			JsonArray elevators = responseJson.getAsJsonArray("elevators");
			for (int i = 0; i < elevators.size(); i++) {
				JsonObject data = (JsonObject) elevators.get(i);

				JsonArray passengerdatas = data.getAsJsonArray("passengers");
				ArrayList<Call> passengers = new ArrayList<>();
				for (int j = 0; j < passengerdatas.size(); j++) {
					JsonObject passengerData = (JsonObject) passengerdatas.get(j);
					Call call = new Call();
					call.setId(passengerData.get("id").getAsInt());
					call.setTimestamp(passengerData.get("timestamp").getAsInt());
					call.setStart(passengerData.get("start").getAsInt());
					call.setEnd(passengerData.get("end").getAsInt());
					passengers.add(call);
				}

				Elevator elevator = new Elevator();
				elevator.setId(data.get("id").getAsInt());
				elevator.setFloor(data.get("floor").getAsInt());
				elevator.setStatus(data.get("status").getAsString());
				elevator.setPassengers(passengers);
				elevatorList.add(elevator);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return elevatorList;
	}

	public ArrayList<Call> getCallsFromOnCalls(JsonObject responseJson) {
		ArrayList<Call> callList = new ArrayList<Call>();

		try {
			JsonArray calls = responseJson.getAsJsonArray("calls");

			for (int i = 0; i < calls.size(); i++) {
				JsonObject data = (JsonObject) calls.get(i);
				Call call = new Call();
				call.setId(data.get("id").getAsInt());
				call.setTimestamp(data.get("timestamp").getAsInt());
				call.setStart(data.get("start").getAsInt());
				call.setEnd(data.get("end").getAsInt());
				callList.add(call);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return callList;
	}

	public JsonArray getCommandsJSONArray(List<Command> commandList) {
		JsonArray commandArray = new JsonArray();
		for (Command command : commandList) {
			commandArray.add(command.getJsonCommandData());
		}

		return commandArray;
	}
}
