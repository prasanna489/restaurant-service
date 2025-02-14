package com.prasanna.restaurant.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Json util methods.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class JsonUtil {

    public static final String ISO_DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";


    private static final Gson GSON = new GsonBuilder()
        .setDateFormat(ISO_DATE_FORMAT_STRING)
        .create();

    /**
     * This method deserializes the specified Json into an object of the specified class.
     *
     * @param jsonString - The input json string
     * @param classOfT - Class to convert it into
     * @param <T> - Type of class to be converted to
     * @return - Deserialised json
     */
    public static <T> T convertFromJson(String jsonString, Class<T> classOfT) {
        try {
            return GSON.fromJson(jsonString, classOfT);
        } catch (Exception e) {
            log.error("Error converting {} to {}", jsonString, classOfT.getName(), e);
            throw e;
        }
    }

    /**
     * This method deserializes the specified Json into an object of the specified class.
     *
     * @param jsonString - The input json string
     * @param type - Class to convert it into
     * @param <T> - Type of class to be converted to
     * @return - Deserialised json
     */
    public static <T> T convertFromJson(String jsonString, Type type) {
        try {
            return GSON.fromJson(jsonString, type);
        } catch (Exception e) {
            log.error("Error converting {} to {}", jsonString, type.getTypeName(), e);
            throw e;
        }
    }

    public static String convertToJsonString(Map<String, Object> inputMap) {
        return GSON.toJson(inputMap);
    }

    public static String convertToJsonString(Object object) {
        return GSON.toJson(object);
    }

    /**
     * Convert a Map to its corresponding {@link JsonObject}.
     *
     * @param map - Map to convert.
     * @return - Converted object.
     */
    public static JsonObject createFromMap(Map<String, String> map) {
        if (Objects.isNull(map) || map.isEmpty()) {
            return new JsonObject();
        }
        JsonObject attributes = new JsonObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            attributes.addProperty(entry.getKey(), entry.getValue());
        }
        return attributes;
    }

}
