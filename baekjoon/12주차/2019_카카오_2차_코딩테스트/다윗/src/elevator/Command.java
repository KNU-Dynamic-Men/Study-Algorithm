package elevator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Command {
	private int elevatorId;
	private String command;
	private int[] callIds;

	public Command(int elevatorId) {
		this.elevatorId = elevatorId;
	}

	public Command(int elevatorId, String command) {
		this.elevatorId = elevatorId;
		this.command = command;
	}

	public Command(int elevatorId, String command, int[] callIds) {
		this.elevatorId = elevatorId;
		this.command = command;
		this.callIds = callIds;
	}

	public int getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(int elevatorId) {
		this.elevatorId = elevatorId;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public int[] getCallIds() {
		return callIds;
	}

	public void setCallIds(int[] callIds) {
		this.callIds = callIds;
	}

	public JsonObject getJsonCommandData() {
		JsonObject jsonObject = new JsonObject();
		try {
			Gson gson = new Gson();
			jsonObject.addProperty("elevator_id", elevatorId);
			jsonObject.addProperty("command", command);
			if (callIds != null)
				jsonObject.add("call_ids", gson.toJsonTree(callIds));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}
	public String getJsonCallsFromIntArray(int[] callIds) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0; i<callIds.length; i++) {
			sb.append(callIds[i]);
			if(i+1<callIds.length)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}
}
