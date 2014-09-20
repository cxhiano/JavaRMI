package rmi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {
	public static JSONObject fromStream(InputStream in) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder builder = new StringBuilder();
		String str = new String();
		try {
			while ((str = reader.readLine()) != null)
				builder.append(str);
			JSONObject object = new JSONObject(str);
			return object;
		} catch (IOException e) {

		} catch (JSONException e) {

		}
		return null;
	}
}
