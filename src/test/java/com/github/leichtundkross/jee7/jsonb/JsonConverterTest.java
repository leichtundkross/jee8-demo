package com.github.leichtundkross.jee7.jsonb;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import org.junit.Test;

public class JsonConverterTest {

    private JsonConverter converter = new JsonConverter();

    @Test
    public void object2json_primitives() {
        Entity myEntity = new Entity();
        myEntity.stringAttribute = "sample String value";
        myEntity.intAttribute = 4711;
        myEntity.booleanAttribute = true;

        String jsonResult = converter.object2json(myEntity);

        String expectedJson = "{" //
                + "\"booleanAttribute\":true," //
                + "\"intAttribute\":4711," //
                + "\"stringAttribute\":\"sample String value\"" //
                + "}";
        assertEquals(expectedJson, jsonResult);
    }

    @Test
    public void object2json_date() {
        Entity myEntity = new Entity();
        myEntity.dateTimeAttribute = LocalDateTime.of(2017, Month.FEBRUARY, 15, 20, 30, 40);
        myEntity.dateAttribute = LocalDate.of(2017, Month.FEBRUARY, 20);

        String jsonResult = converter.object2json(myEntity);

        String expectedJson = "{" //
                + "\"booleanAttribute\":false," //
                + "\"dateAttribute\":\"2017-02-20\"," //
                + "\"dateTimeAttribute\":\"2017-02-15T20:30:40\"," //
                + "\"intAttribute\":0" //
                + "}";
        assertEquals(expectedJson, jsonResult);
    }

    @Test
    public void object2json_transient() {
        Entity myEntity = new Entity();
        myEntity.ignoredAttribute = "secret";

        String jsonResult = converter.object2json(myEntity);

        String expectedJson = "{" //
                + "\"booleanAttribute\":false," //
                + "\"intAttribute\":0" //
                + "}";
        assertEquals(expectedJson, jsonResult);
    }

    @Test
    public void object2json_collections() {
        Entity myEntity = new Entity();
        myEntity.list = Arrays.asList("foo", "bar");

        String jsonResult = converter.object2json(myEntity);

        String expectedJson = "{" //
                + "\"booleanAttribute\":false," //
                + "\"intAttribute\":0," //
                + "\"list\":[\"foo\",\"bar\"]" //
                + "}";
        assertEquals(expectedJson, jsonResult);
    }

    @Test
    public void object2json_customTypes() {
        Entity myEntity = new Entity();
        myEntity.subEntity = new SubEntity("Tom");

        String jsonResult = converter.object2json(myEntity);

        String expectedJson = "{" //
                + "\"booleanAttribute\":false," //
                + "\"intAttribute\":0," //
                + "\"subEntity\":\"Tom\"" //
                + "}";
        assertEquals(expectedJson, jsonResult);
    }

    @Test
    public void object2jsonWithCustomConfig() {
        Entity myEntity = new Entity();
        myEntity.dateTimeAttribute = LocalDateTime.of(2017, Month.FEBRUARY, 15, 20, 30, 40);

        String jsonResult = converter.object2jsonWithCustomConfig(myEntity);

        String expectedJson = "{" //
                + "\"booleanAttribute\":false," //
                + "\"dateTimeAttribute\":\"1487187040000\"," //
                + "\"intAttribute\":0" //
                + "}";
        assertEquals(expectedJson, jsonResult);
    }

    @Test
    public void json2object() {
        String json = "{" //
                + "\"booleanAttribute\":true," //
                + "\"list\":[\"foo\",\"bar\"]," //
                + "\"dateAttribute\":\"2017-02-20\"," //
                + "\"dateTimeAttribute\":\"2017-02-15T20:30:40\"," //
                + "\"intAttribute\":4711," //
                + "\"stringAttribute\":\"sample String value\"," //
                + "\"subEntity\":\"Tom\"" //
                + "}";

        Entity result = converter.json2object(json, Entity.class);

        assertEquals("sample String value", result.stringAttribute);
        assertEquals(4711, result.intAttribute);
        assertEquals(true, result.booleanAttribute);
        assertEquals(Arrays.asList("foo", "bar"), result.list);
        assertEquals(LocalDateTime.of(2017, Month.FEBRUARY, 15, 20, 30, 40), result.dateTimeAttribute);
        assertEquals(LocalDate.of(2017, Month.FEBRUARY, 20), result.dateAttribute);
        assertEquals(new SubEntity("Tom"), result.subEntity);
    }
}
