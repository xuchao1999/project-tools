package com.xc.utils.generateCode;






import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableParseUtil {
    public TableParseUtil() {
    }

    public static ClassInfo processTableIntoClassInfo(ParamInfo paramInfo) throws IOException {
        String tableSql = paramInfo.getTableSql();
        String nameCaseType = paramInfo.getNameCaseType();
        String tinyintTransType = paramInfo.getTinyintTransType();
        if (tableSql != null && tableSql.trim().length() != 0) {
            tableSql = tableSql.trim().replaceAll("'", "`").replaceAll("\"", "`").replaceAll("，", ",").toLowerCase();
            tableSql = tableSql.trim().replaceAll("\\\\n`", "").replaceAll("\\+", "").replaceAll("``", "`").replaceAll("\\\\", "");
            String tableName = null;
            if (tableSql.contains("TABLE") && tableSql.contains("(")) {
                tableName = tableSql.substring(tableSql.indexOf("TABLE") + 5, tableSql.indexOf("("));
            } else {
                if (!tableSql.contains("table") || !tableSql.contains("(")) {
                    throw new IOException("Table structure incorrect.表结构不正确。");
                }

                tableName = tableSql.substring(tableSql.indexOf("table") + 5, tableSql.indexOf("("));
            }

            if (tableName.contains("if not exists")) {
                tableName = tableName.replaceAll("if not exists", "");
            }

            if (tableName.contains("`")) {
                tableName = tableName.substring(tableName.indexOf("`") + 1, tableName.lastIndexOf("`"));
            } else {
                tableName = tableName.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
            }

            if (tableName.contains("`.`")) {
                tableName = tableName.substring(tableName.indexOf("`.`") + 3);
            } else if (tableName.contains(".")) {
                tableName = tableName.substring(tableName.indexOf(".") + 1);
            }

            String className = StringUtils.upperCaseFirst(StringUtils.underlineToCamelCase(tableName));
            if (className.contains("_")) {
                className = className.replaceAll("_", "");
            }

            String classComment = null;
            if (!tableSql.contains("comment=") && !tableSql.contains("comment on table")) {
                classComment = tableName;
            } else {
                String classCommentTmp = tableSql.contains("comment=") ? tableSql.substring(tableSql.lastIndexOf("comment=") + 8).trim() : tableSql.substring(tableSql.lastIndexOf("comment on table") + 17).trim();
                if (classCommentTmp.contains("`")) {
                    classCommentTmp = classCommentTmp.substring(classCommentTmp.indexOf("`") + 1);
                    classCommentTmp = classCommentTmp.substring(0, classCommentTmp.indexOf("`"));
                    classComment = classCommentTmp;
                } else {
                    classComment = className;
                }
            }

            classComment = classComment.replaceAll(";", "");
            List<FieldInfo> fieldList = new ArrayList();
            String fieldListTmp = tableSql.substring(tableSql.indexOf("(") + 1, tableSql.lastIndexOf(")"));
            String commentPattenStr1 = "comment `(.*?)\\`";
            Matcher matcher1 = Pattern.compile(commentPattenStr1).matcher(fieldListTmp);

            String commentTmp;
            while(matcher1.find()) {
                commentTmp = matcher1.group();
                if (commentTmp.contains(",")) {
                    String commentTmpFinal = commentTmp.replaceAll(",", "，");
                    fieldListTmp = fieldListTmp.replace(matcher1.group(), commentTmpFinal);
                }
            }

            commentTmp = "\\`(.*?)\\`";
            Matcher matcher2 = Pattern.compile(commentTmp).matcher(fieldListTmp);

            String commentTmp2;
            while(matcher2.find()) {
                commentTmp2 = matcher2.group();
                if (commentTmp2.contains(",")) {
                    String commentTmpFinal = commentTmp2.replaceAll(",", "，").replaceAll("\\(", "（").replaceAll("\\)", "）");
                    fieldListTmp = fieldListTmp.replace(matcher2.group(), commentTmpFinal);
                }
            }

            commentTmp2 = "\\((.*?)\\)";
            Matcher matcher3 = Pattern.compile(commentTmp2).matcher(fieldListTmp);

            while(matcher3.find()) {
                String commentTmp3 = matcher3.group();
                if (commentTmp3.contains(",")) {
                    String commentTmpFinal = commentTmp3.replaceAll(",", "，");
                    fieldListTmp = fieldListTmp.replace(matcher3.group(), commentTmpFinal);
                }
            }

            String[] fieldLineList = fieldListTmp.split(",");
            if (fieldLineList.length > 0) {
                int i = 0;
                String[] var17 = fieldLineList;
                int var18 = fieldLineList.length;

                for(int var19 = 0; var19 < var18; ++var19) {
                    String columnLine = var17[var19];
                    ++i;
                    columnLine = columnLine.replaceAll("\n", "").replaceAll("\t", "").trim();
                    boolean specialFlag = !columnLine.contains("key ") && !columnLine.contains("constraint") && !columnLine.contains("using") && !columnLine.contains("unique") && (!columnLine.contains("primary ") || columnLine.indexOf("storage") + 3 <= columnLine.indexOf("(")) && !columnLine.contains("pctincrease") && !columnLine.contains("buffer_pool") && !columnLine.contains("tablespace") && (!columnLine.contains("primary ") || i <= 3);
                    if (specialFlag && columnLine.length() >= 5) {
                        String columnName = "";
                        columnLine = columnLine.replaceAll("`", " ").replaceAll("\"", " ").replaceAll("'", "").replaceAll("  ", " ").trim();
                        columnName = columnLine.substring(0, columnLine.indexOf(" "));
                        String fieldName = null;
                        if (ParamInfo.NAME_CASE_TYPE.CAMEL_CASE.equals(nameCaseType)) {
                            fieldName = StringUtils.lowerCaseFirst(StringUtils.underlineToCamelCase(columnName));
                            if (fieldName.contains("_")) {
                                fieldName = fieldName.replaceAll("_", "");
                            }
                        } else if (ParamInfo.NAME_CASE_TYPE.UNDER_SCORE_CASE.equals(nameCaseType)) {
                            fieldName = StringUtils.lowerCaseFirst(columnName);
                        } else if (ParamInfo.NAME_CASE_TYPE.UPPER_UNDER_SCORE_CASE.equals(nameCaseType)) {
                            fieldName = StringUtils.lowerCaseFirst(columnName.toUpperCase());
                        } else {
                            fieldName = columnName;
                        }

                        columnLine = columnLine.substring(columnLine.indexOf("`") + 1).trim();
                        String fieldClass = Object.class.getSimpleName();
                        if (columnLine.contains(" tinyint")) {
                            fieldClass = tinyintTransType;
                        } else if (!columnLine.contains(" int") && !columnLine.contains(" smallint")) {
                            if (columnLine.contains(" bigint")) {
                                fieldClass = Long.class.getSimpleName();
                            } else if (columnLine.contains(" float")) {
                                fieldClass = Float.class.getSimpleName();
                            } else if (columnLine.contains(" double")) {
                                fieldClass = Double.class.getSimpleName();
                            } else if (!columnLine.contains(" time") && !columnLine.contains(" date") && !columnLine.contains(" datetime") && !columnLine.contains(" timestamp")) {
                                if (!columnLine.contains(" varchar") && !columnLine.contains(" text") && !columnLine.contains(" char") && !columnLine.contains(" clob") && !columnLine.contains(" blob") && !columnLine.contains(" json")) {
                                    if (!columnLine.contains(" decimal") && !columnLine.contains(" number")) {
                                        if (columnLine.contains(" boolean")) {
                                            fieldClass = Boolean.class.getSimpleName();
                                        } else {
                                            fieldClass = String.class.getSimpleName();
                                        }
                                    } else {
                                        int startKh = columnLine.indexOf("(");
                                        if (startKh >= 0) {
                                            int endKh = columnLine.indexOf(")", startKh);
                                            String[] fanwei = columnLine.substring(startKh + 1, endKh).split("，");
                                            if ((fanwei.length <= 1 || !"0".equals(fanwei[1])) && fanwei.length != 1) {
                                                fieldClass = BigDecimal.class.getSimpleName();
                                            } else {
                                                int length = Integer.parseInt(fanwei[0]);
                                                if (fanwei.length > 1) {
                                                    length = Integer.valueOf(fanwei[1]);
                                                }

                                                if (length <= 9) {
                                                    fieldClass = Integer.class.getSimpleName();
                                                } else {
                                                    fieldClass = Long.class.getSimpleName();
                                                }
                                            }
                                        } else {
                                            fieldClass = BigDecimal.class.getSimpleName();
                                        }
                                    }
                                } else {
                                    fieldClass = String.class.getSimpleName();
                                }
                            } else {
                                fieldClass = Date.class.getSimpleName();
                            }
                        } else {
                            fieldClass = Integer.class.getSimpleName();
                        }

                        String fieldComment = null;
                        if (tableSql.contains("comment on column") && (tableSql.contains("." + columnName + " is ") || tableSql.contains(".`" + columnName + "` is"))) {
                            tableSql = tableSql.replaceAll(".`" + columnName + "` is", "." + columnName + " is");
                            Matcher columnCommentMatcher = Pattern.compile("\\." + columnName + " is `").matcher(tableSql);

                            for(fieldComment = columnName; columnCommentMatcher.find(); fieldComment = fieldComment.substring(0, fieldComment.indexOf("`")).trim()) {
                                String columnCommentTmp = columnCommentMatcher.group();
                                fieldComment = tableSql.substring(tableSql.indexOf(columnCommentTmp) + columnCommentTmp.length()).trim();
                            }
                        } else if (columnLine.contains(" comment")) {
                            String commentTmp1 = columnLine.substring(columnLine.lastIndexOf("comment") + 7).trim();
                            if (commentTmp1.contains("`") || commentTmp1.indexOf("`") != commentTmp1.lastIndexOf("`")) {
                                commentTmp1 = commentTmp1.substring(commentTmp1.indexOf("`") + 1, commentTmp1.lastIndexOf("`"));
                            }

                            if (commentTmp1.contains(")")) {
                                commentTmp1 = commentTmp1.substring(0, commentTmp1.lastIndexOf(")") + 1);
                            }

                            fieldComment = commentTmp1;
                        } else {
                            fieldComment = columnName;
                        }

                        FieldInfo fieldInfo = new FieldInfo();
                        fieldInfo.setColumnName(columnName);
                        fieldInfo.setFieldName(fieldName);
                        fieldInfo.setFieldClass(fieldClass);
                        fieldInfo.setFieldComment(fieldComment);
                        fieldList.add(fieldInfo);
                    }
                }
            }

            if (fieldList.size() < 1) {
                throw new IOException("表结构分析失败，请检查语句或者提交issue给我");
            } else {
                ClassInfo codeJavaInfo = new ClassInfo();
                codeJavaInfo.setTableName(tableName);
                codeJavaInfo.setClassName(className);
                codeJavaInfo.setClassComment(classComment);
                codeJavaInfo.setFieldList(fieldList);
                return codeJavaInfo;
            }
        } else {
            throw new IOException("Table structure can not be empty. 表结构不能为空。");
        }
    }

    public static ClassInfo processJsonToClassInfo(ParamInfo paramInfo) throws IOException {
        ClassInfo codeJavaInfo = new ClassInfo();
        codeJavaInfo.setTableName("JsonDto");
        codeJavaInfo.setClassName("JsonDto");
        codeJavaInfo.setClassComment("JsonDto");
        if (paramInfo.getTableSql().trim().startsWith("\"")) {
            paramInfo.setTableSql("{" + paramInfo.getTableSql());
        }

        if (paramInfo.getTableSql().trim().startsWith("{")) {
            JSONObject jsonObject = JSONObject.parseObject(paramInfo.getTableSql().trim());
            codeJavaInfo.setFieldList(processJsonObjectToFieldList(jsonObject));
        } else if (paramInfo.getTableSql().trim().startsWith("[")) {
            JSONArray jsonArray = JSONArray.parseArray(paramInfo.getTableSql().trim());
            codeJavaInfo.setFieldList(processJsonObjectToFieldList(jsonArray.getJSONObject(0)));
        }

        return codeJavaInfo;
    }

    public static ClassInfo processTableToClassInfoByRegex(ParamInfo paramInfo) {
        List<FieldInfo> fieldList = new ArrayList();
        ClassInfo codeJavaInfo = new ClassInfo();
        String DDL_PATTEN_STR = "\\s*create\\s+table\\s+(?<tableName>\\S+)[^\\(]*\\((?<columnsSQL>[\\s\\S]+)\\)[^\\)]+?(comment\\s*(=|on\\s+table)\\s*'(?<tableComment>.*?)'\\s*;?)?$";
        Pattern DDL_PATTERN = Pattern.compile(DDL_PATTEN_STR, 2);
        String COL_PATTERN_STR = "\\s*(?<fieldName>\\S+)\\s+(?<fieldType>\\w+)\\s*(?:\\([\\s\\d,]+\\))?((?!comment).)*(comment\\s*'(?<fieldComment>.*?)')?\\s*(,|$)";
        Pattern COL_PATTERN = Pattern.compile(COL_PATTERN_STR, 2);
        Matcher matcher = DDL_PATTERN.matcher(paramInfo.getTableSql().trim());
        if (matcher.find()) {
            String tableName = matcher.group("tableName");
            String tableComment = matcher.group("tableComment");
            codeJavaInfo.setTableName(tableName.replaceAll("'", ""));
            codeJavaInfo.setClassName(tableName.replaceAll("'", ""));
            codeJavaInfo.setClassComment(tableComment.replaceAll("'", ""));
            String columnsSQL = matcher.group("columnsSQL");
            if (columnsSQL != null && columnsSQL.length() > 0) {
                Matcher colMatcher = COL_PATTERN.matcher(columnsSQL);

                while(colMatcher.find()) {
                    String fieldName = colMatcher.group("fieldName");
                    String fieldType = colMatcher.group("fieldType");
                    String fieldComment = colMatcher.group("fieldComment");
                    if (!"key".equalsIgnoreCase(fieldType)) {
                        FieldInfo fieldInfo = new FieldInfo();
                        fieldInfo.setFieldName(fieldName.replaceAll("'", ""));
                        fieldInfo.setColumnName(fieldName.replaceAll("'", ""));
                        fieldInfo.setFieldClass(fieldType.replaceAll("'", ""));
                        fieldInfo.setFieldComment(fieldComment.replaceAll("'", ""));
                        fieldList.add(fieldInfo);
                    }
                }
            }

            codeJavaInfo.setFieldList(fieldList);
        }

        return codeJavaInfo;
    }

    public static List<FieldInfo> processJsonObjectToFieldList(JSONObject jsonObject) throws IOException {
        List<FieldInfo> fieldList = new ArrayList();
        jsonObject.keySet().stream().forEach((jsonField) -> {
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setFieldName(jsonField);
            fieldInfo.setColumnName(jsonField);
            fieldInfo.setFieldClass(String.class.getSimpleName());
            fieldInfo.setFieldComment("father:" + jsonField);
            fieldList.add(fieldInfo);
            if (jsonObject.get(jsonField) instanceof JSONArray) {
                jsonObject.getJSONArray(jsonField).stream().forEach((arrayObject) -> {
                    FieldInfo fieldInfo2 = new FieldInfo();
                    fieldInfo2.setFieldName(arrayObject.toString());
                    fieldInfo2.setColumnName(arrayObject.toString());
                    fieldInfo2.setFieldClass(String.class.getSimpleName());
                    fieldInfo2.setFieldComment("children:" + arrayObject.toString());
                    fieldList.add(fieldInfo2);
                });
            } else if (jsonObject.get(jsonField) instanceof JSONObject) {
                jsonObject.getJSONObject(jsonField).keySet().stream().forEach((arrayObject) -> {
                    FieldInfo fieldInfo2 = new FieldInfo();
                    fieldInfo2.setFieldName(arrayObject.toString());
                    fieldInfo2.setColumnName(arrayObject.toString());
                    fieldInfo2.setFieldClass(String.class.getSimpleName());
                    fieldInfo2.setFieldComment("children:" + arrayObject.toString());
                    fieldList.add(fieldInfo2);
                });
            }

        });
        if (fieldList.size() < 1) {
            throw new IOException("JSON解析失败");
        } else {
            return fieldList;
        }
    }

    public static ClassInfo processInsertSqlToClassInfo(ParamInfo paramInfo) throws IOException {
        List<FieldInfo> fieldList = new ArrayList();
        ClassInfo codeJavaInfo = new ClassInfo();
        String fieldSqlStr = paramInfo.getTableSql().toLowerCase().trim();
        fieldSqlStr = fieldSqlStr.replaceAll("  ", " ").replaceAll("\\\\n`", "").replaceAll("\\+", "").replaceAll("``", "`").replaceAll("\\\\", "");
        String valueStr = fieldSqlStr.substring(fieldSqlStr.lastIndexOf("values") + 6).replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "");
        fieldSqlStr = fieldSqlStr.substring(0, fieldSqlStr.lastIndexOf("values"));
        System.out.println(fieldSqlStr);
        String insertSqlPattenStr = "insert into (?<tableName>.*) \\((?<columnsSQL>.*)\\)";
        Matcher matcher1 = Pattern.compile(insertSqlPattenStr).matcher(fieldSqlStr);

        while(matcher1.find()) {
            String tableName = matcher1.group("tableName");
            codeJavaInfo.setClassName(tableName);
            codeJavaInfo.setTableName(tableName);
            String columnsSQL = matcher1.group("columnsSQL");
            List<String> valueList = new ArrayList();
            Arrays.stream(valueStr.split(",")).forEach((column) -> {
                valueList.add(column);
            });
            AtomicInteger n = new AtomicInteger(0);
            Arrays.stream(columnsSQL.replaceAll(" ", "").split(",")).forEach((column) -> {
                FieldInfo fieldInfo2 = new FieldInfo();
                fieldInfo2.setFieldName(column);
                fieldInfo2.setColumnName(column);
                fieldInfo2.setFieldClass(String.class.getSimpleName());
                if (n.get() < valueList.size()) {
                    fieldInfo2.setFieldComment(column + " , eg." + (String)valueList.get(n.get()));
                }

                fieldList.add(fieldInfo2);
                n.getAndIncrement();
            });
        }

        if (fieldList.size() < 1) {
            throw new IOException("INSERT SQL解析失败");
        } else {
            codeJavaInfo.setFieldList(fieldList);
            return codeJavaInfo;
        }
    }
}