package com.github.leichtundkross.jee7.jsonb;

import javax.json.bind.adapter.JsonbAdapter;

public class SubEntityJsonbAdapter implements JsonbAdapter<SubEntity, String> {

    @Override
    public String adaptToJson(SubEntity subEntity) throws Exception {
        return subEntity.name;
    }

    @Override
    public SubEntity adaptFromJson(String jsonValue) throws Exception {
        return new SubEntity(jsonValue);
    }
}
