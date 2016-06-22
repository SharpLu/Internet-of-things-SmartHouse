package Devices;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/*
 * @author: Hamed Talebi, Konstantin Bauer
 * Date: 2013-10-16
 * THIS CLASS BUILT FOR OUR COMPLEX GSON OBJECTS
 */
public class DevicesAdapter implements JsonSerializer<Devices>, JsonDeserializer<Devices>{

	@Override
	public Devices deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		String type = jsonObject.get("type").getAsString();
		JsonElement element = jsonObject.get("properties");
		
		try{
			return context.deserialize(element, Class.forName("Devices." + type));
		}catch(ClassNotFoundException cnfe){
			throw new JsonParseException("Unknown element type: " + type, cnfe);
		}
		
	}

	@Override
	public JsonElement serialize(Devices src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
		result.add("properties", context.serialize(src, src.getClass()));
		return result;
	}
	

}