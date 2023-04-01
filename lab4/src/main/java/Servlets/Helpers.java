package Servlets;

import Entities.CarEntity;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Helpers {
    public static JsonElement bodyParse(HttpServletRequest request){
        JsonElement jsonElement = null;

        try {
            jsonElement = JsonParser.parseReader(request.getReader());
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }

        return jsonElement;
    }

    public static CarEntity carParse(HttpServletRequest request) {
        CarEntity car = new CarEntity();
        JsonElement jsonElement = bodyParse(request);
        car.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
        car.setImg(jsonElement.getAsJsonObject().get("img").getAsString());
        car.setName(jsonElement.getAsJsonObject().get("name").getAsString());
        car.setPrice(jsonElement.getAsJsonObject().get("price").getAsInt());
        return car;
    }

    public static int getNextId(List<CarEntity> list) {
        int maxId = 0;

        Iterator<CarEntity> iterator = list.iterator();
        while(iterator.hasNext()) {
            int currentId = iterator.next().getId();
            if(currentId>maxId) maxId=currentId;
        }
        return maxId+1;
    }

    public static int getIndexByCarId(int id, List<CarEntity> list) {
        int listId = id;

        Iterator<CarEntity> iterator = list.iterator();
        while (iterator.hasNext()) {
            CarEntity temp = iterator.next();
            if (temp.getId() == listId) {
                listId = list.indexOf(temp);
                break;
            }
        }
        return listId;
    }
}
