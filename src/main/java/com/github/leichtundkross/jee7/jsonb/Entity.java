package com.github.leichtundkross.jee7.jsonb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Use public fields for simplicity.
 */
public class Entity {

    public String stringAttribute;

    public int intAttribute;

    public boolean booleanAttribute;

    @JsonbTransient
    public String ignoredAttribute;

    public List<String> list;

    public LocalDateTime dateTimeAttribute;

    public LocalDate dateAttribute;

    @JsonbTypeAdapter(SubEntityJsonbAdapter.class)
    public SubEntity subEntity;
}
