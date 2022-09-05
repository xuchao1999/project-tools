package com.xc.utils.generateCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ParamInfo {
    private String tableSql;
    private String authorName;
    private String projectPath;
    private String packageName;
    private String projectPathForApi;
    private String packageNameForApi;
    private String moduleName;
    private String returnUtil;
    private String nameCaseType;
    private String tinyintTransType;
    private String dataType;
    private String url;
    private String user;
    private String password;
    private String tableName;
    private Boolean swagger;
    public ParamInfo() {
    }

    public String getTableSql() {
        return this.tableSql;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public String getProjectPath() {
        return this.projectPath;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getProjectPathForApi() {
        return this.projectPathForApi;
    }

    public String getPackageNameForApi() {
        return this.packageNameForApi;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public String getReturnUtil() {
        return this.returnUtil;
    }

    public String getNameCaseType() {
        return this.nameCaseType;
    }

    public String getTinyintTransType() {
        return this.tinyintTransType;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public String getTableName() {
        return this.tableName;
    }

    public Boolean getSwagger() {
        return this.swagger;
    }

    public void setTableSql(final String tableSql) {
        this.tableSql = tableSql;
    }

    public void setAuthorName(final String authorName) {
        this.authorName = authorName;
    }

    public void setProjectPath(final String projectPath) {
        this.projectPath = projectPath;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public void setProjectPathForApi(final String projectPathForApi) {
        this.projectPathForApi = projectPathForApi;
    }

    public void setPackageNameForApi(final String packageNameForApi) {
        this.packageNameForApi = packageNameForApi;
    }

    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }

    public void setReturnUtil(final String returnUtil) {
        this.returnUtil = returnUtil;
    }

    public void setNameCaseType(final String nameCaseType) {
        this.nameCaseType = nameCaseType;
    }

    public void setTinyintTransType(final String tinyintTransType) {
        this.tinyintTransType = tinyintTransType;
    }

    public void setDataType(final String dataType) {
        this.dataType = dataType;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public void setSwagger(final Boolean swagger) {
        this.swagger = swagger;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ParamInfo)) {
            return false;
        } else {
            ParamInfo other = (ParamInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label203: {
                    Object this$tableSql = this.getTableSql();
                    Object other$tableSql = other.getTableSql();
                    if (this$tableSql == null) {
                        if (other$tableSql == null) {
                            break label203;
                        }
                    } else if (this$tableSql.equals(other$tableSql)) {
                        break label203;
                    }

                    return false;
                }

                Object this$authorName = this.getAuthorName();
                Object other$authorName = other.getAuthorName();
                if (this$authorName == null) {
                    if (other$authorName != null) {
                        return false;
                    }
                } else if (!this$authorName.equals(other$authorName)) {
                    return false;
                }

                Object this$projectPath = this.getProjectPath();
                Object other$projectPath = other.getProjectPath();
                if (this$projectPath == null) {
                    if (other$projectPath != null) {
                        return false;
                    }
                } else if (!this$projectPath.equals(other$projectPath)) {
                    return false;
                }

                label182: {
                    Object this$packageName = this.getPackageName();
                    Object other$packageName = other.getPackageName();
                    if (this$packageName == null) {
                        if (other$packageName == null) {
                            break label182;
                        }
                    } else if (this$packageName.equals(other$packageName)) {
                        break label182;
                    }

                    return false;
                }

                label175: {
                    Object this$projectPathForApi = this.getProjectPathForApi();
                    Object other$projectPathForApi = other.getProjectPathForApi();
                    if (this$projectPathForApi == null) {
                        if (other$projectPathForApi == null) {
                            break label175;
                        }
                    } else if (this$projectPathForApi.equals(other$projectPathForApi)) {
                        break label175;
                    }

                    return false;
                }

                label168: {
                    Object this$packageNameForApi = this.getPackageNameForApi();
                    Object other$packageNameForApi = other.getPackageNameForApi();
                    if (this$packageNameForApi == null) {
                        if (other$packageNameForApi == null) {
                            break label168;
                        }
                    } else if (this$packageNameForApi.equals(other$packageNameForApi)) {
                        break label168;
                    }

                    return false;
                }

                Object this$moduleName = this.getModuleName();
                Object other$moduleName = other.getModuleName();
                if (this$moduleName == null) {
                    if (other$moduleName != null) {
                        return false;
                    }
                } else if (!this$moduleName.equals(other$moduleName)) {
                    return false;
                }

                label154: {
                    Object this$returnUtil = this.getReturnUtil();
                    Object other$returnUtil = other.getReturnUtil();
                    if (this$returnUtil == null) {
                        if (other$returnUtil == null) {
                            break label154;
                        }
                    } else if (this$returnUtil.equals(other$returnUtil)) {
                        break label154;
                    }

                    return false;
                }

                Object this$nameCaseType = this.getNameCaseType();
                Object other$nameCaseType = other.getNameCaseType();
                if (this$nameCaseType == null) {
                    if (other$nameCaseType != null) {
                        return false;
                    }
                } else if (!this$nameCaseType.equals(other$nameCaseType)) {
                    return false;
                }

                label140: {
                    Object this$tinyintTransType = this.getTinyintTransType();
                    Object other$tinyintTransType = other.getTinyintTransType();
                    if (this$tinyintTransType == null) {
                        if (other$tinyintTransType == null) {
                            break label140;
                        }
                    } else if (this$tinyintTransType.equals(other$tinyintTransType)) {
                        break label140;
                    }

                    return false;
                }

                Object this$dataType = this.getDataType();
                Object other$dataType = other.getDataType();
                if (this$dataType == null) {
                    if (other$dataType != null) {
                        return false;
                    }
                } else if (!this$dataType.equals(other$dataType)) {
                    return false;
                }

                Object this$url = this.getUrl();
                Object other$url = other.getUrl();
                if (this$url == null) {
                    if (other$url != null) {
                        return false;
                    }
                } else if (!this$url.equals(other$url)) {
                    return false;
                }

                label119: {
                    Object this$user = this.getUser();
                    Object other$user = other.getUser();
                    if (this$user == null) {
                        if (other$user == null) {
                            break label119;
                        }
                    } else if (this$user.equals(other$user)) {
                        break label119;
                    }

                    return false;
                }

                label112: {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            break label112;
                        }
                    } else if (this$password.equals(other$password)) {
                        break label112;
                    }

                    return false;
                }

                Object this$tableName = this.getTableName();
                Object other$tableName = other.getTableName();
                if (this$tableName == null) {
                    if (other$tableName != null) {
                        return false;
                    }
                } else if (!this$tableName.equals(other$tableName)) {
                    return false;
                }

                Object this$swagger = this.getSwagger();
                Object other$swagger = other.getSwagger();
                if (this$swagger == null) {
                    if (other$swagger != null) {
                        return false;
                    }
                } else if (!this$swagger.equals(other$swagger)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ParamInfo;
    }

    @Override
    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $tableSql = this.getTableSql();
        result = result * 59 + ($tableSql == null ? 43 : $tableSql.hashCode());
        Object $authorName = this.getAuthorName();
        result = result * 59 + ($authorName == null ? 43 : $authorName.hashCode());
        Object $projectPath = this.getProjectPath();
        result = result * 59 + ($projectPath == null ? 43 : $projectPath.hashCode());
        Object $packageName = this.getPackageName();
        result = result * 59 + ($packageName == null ? 43 : $packageName.hashCode());
        Object $projectPathForApi = this.getProjectPathForApi();
        result = result * 59 + ($projectPathForApi == null ? 43 : $projectPathForApi.hashCode());
        Object $packageNameForApi = this.getPackageNameForApi();
        result = result * 59 + ($packageNameForApi == null ? 43 : $packageNameForApi.hashCode());
        Object $moduleName = this.getModuleName();
        result = result * 59 + ($moduleName == null ? 43 : $moduleName.hashCode());
        Object $returnUtil = this.getReturnUtil();
        result = result * 59 + ($returnUtil == null ? 43 : $returnUtil.hashCode());
        Object $nameCaseType = this.getNameCaseType();
        result = result * 59 + ($nameCaseType == null ? 43 : $nameCaseType.hashCode());
        Object $tinyintTransType = this.getTinyintTransType();
        result = result * 59 + ($tinyintTransType == null ? 43 : $tinyintTransType.hashCode());
        Object $dataType = this.getDataType();
        result = result * 59 + ($dataType == null ? 43 : $dataType.hashCode());
        Object $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        Object $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : $user.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        Object $swagger = this.getSwagger();
        result = result * 59 + ($swagger == null ? 43 : $swagger.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ParamInfo(tableSql=" + this.getTableSql() + ", authorName=" + this.getAuthorName() + ", projectPath=" + this.getProjectPath() + ", packageName=" + this.getPackageName() + ", projectPathForApi=" + this.getProjectPathForApi() + ", packageNameForApi=" + this.getPackageNameForApi() + ", moduleName=" + this.getModuleName() + ", returnUtil=" + this.getReturnUtil() + ", nameCaseType=" + this.getNameCaseType() + ", tinyintTransType=" + this.getTinyintTransType() + ", dataType=" + this.getDataType() + ", url=" + this.getUrl() + ", user=" + this.getUser() + ", password=" + this.getPassword() + ", tableName=" + this.getTableName() + ", swagger=" + this.getSwagger() + ")";
    }

    public static class NAME_CASE_TYPE {
        public static String CAMEL_CASE = "CamelCase";
        public static String UNDER_SCORE_CASE = "UnderScoreCase";
        public static String UPPER_UNDER_SCORE_CASE = "UpperUnderScoreCase";

        public NAME_CASE_TYPE() {
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ParamInfo.NAME_CASE_TYPE)) {
                return false;
            } else {
                ParamInfo.NAME_CASE_TYPE other = (ParamInfo.NAME_CASE_TYPE)o;
                return other.canEqual(this);
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ParamInfo.NAME_CASE_TYPE;
        }

        @Override
        public int hashCode() {
//            int result = true;
            return 1;
        }

        @Override
        public String toString() {
            return "ParamInfo.NAME_CASE_TYPE()";
        }
    }
}
