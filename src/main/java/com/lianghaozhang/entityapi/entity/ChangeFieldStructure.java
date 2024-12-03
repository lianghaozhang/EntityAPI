package com.lianghaozhang.entityapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChangeFieldStructure {
    public String tableName;
    public String oldFieldName;
    public String newFieldName;
    public String newFieldType;
    public int newFieldTypeLength;
    public String isNotNull;
    public String isUnique;
    public String comment;
}
