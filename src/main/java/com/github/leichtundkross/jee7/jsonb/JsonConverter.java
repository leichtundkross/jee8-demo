package com.github.leichtundkross.jee7.jsonb;

import java.util.Locale;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbDateFormat;

public class JsonConverter {

    public <T> T json2object(String json, Class<T> entityType) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(json, entityType);
    }

    public String object2json(Object entity) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(entity);
    }

    public String object2jsonWithCustomConfig(Object entity) {
        JsonbConfig jsonbConfig = new JsonbConfig() //
                .withDateFormat(JsonbDateFormat.TIME_IN_MILLIS, Locale.GERMAN);
        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);
        return jsonb.toJson(entity);
    }
}
