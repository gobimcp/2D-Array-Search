package array.search.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import array.search.exception.InputException;
import array.search.util.SearchUtility;

@RestController
public class SearchController {

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@RequestBody String jsonInput) throws InputException {
		boolean result = false;
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		JsonObject response = new JsonObject();
		int[][] inputArray;
		JsonElement jsonElement = null;
		int target = 0;

		try {
			jsonObject = gson.fromJson(jsonInput, JsonObject.class);
			jsonElement = jsonObject.get("input");
			if (jsonElement == null)
				throw new InputException();
			inputArray = gson.fromJson(jsonElement, int[][].class);

			jsonElement = jsonObject.get("target");
			if (jsonElement == null)
				throw new InputException();
			target = jsonElement.getAsInt();

			if (inputArray.length == 0) {
				result = false;
			} else {
				result = SearchUtility.find_logic1(inputArray, 0, 0, inputArray.length - 1, inputArray[0].length - 1,
						target);
				// result = SearchUtility.find_logic2(inputArray, target);
			}

			// Creating the response json
			response.addProperty("result", result);

		} catch (InputException e) {
			response.addProperty("result", result);
			throw e;
		}
		return response.toString();
	}

}
