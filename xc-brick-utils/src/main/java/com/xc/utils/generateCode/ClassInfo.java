package com.xc.utils.generateCode;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ClassInfo {
    private String tableName;
    private String className;
    private String packageName;
    private String packageNameForApi;
    private String moduleName;
    private String classComment;
    private String authorName;
    private List<FieldInfo> fieldList;
}
