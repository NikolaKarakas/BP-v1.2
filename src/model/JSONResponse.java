package model;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONResponse {
	
	private JSONObject jsonRespond;
	private JSONObject iterator;
	private JSONArray jsonArray;
	private int jsonArraySize;
	
	
	public JSONResponse(String response) {
		jsonRespond = new JSONObject(response);
		jsonArray= jsonRespond.getJSONArray("obj");
		jsonArraySize= jsonArray.length();
		

	}

	public int getJsonArraySize() {
		return jsonArraySize;
	}
		
	public JSONObject getIterator(int i) {
		iterator = jsonArray.getJSONObject(i);
		return iterator;
	}



}
