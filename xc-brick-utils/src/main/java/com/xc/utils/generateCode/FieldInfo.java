package com.xc.utils.generateCode;

public class FieldInfo {
    private String columnName;
    private String fieldName;
    private String fieldClass;
    private String fieldComment;

    public FieldInfo() {
    }

    public String getColumnName() {
        return this.columnName;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public String getFieldClass() {
        return this.fieldClass;
    }

    public String getFieldComment() {
        return this.fieldComment;
    }

    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldClass(final String fieldClass) {
        this.fieldClass = fieldClass;
    }

    public void setFieldComment(final String fieldComment) {
        this.fieldComment = fieldComment;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof FieldInfo)) {
            return false;
        } else {
            FieldInfo other = (FieldInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$columnName = this.getColumnName();
                    Object other$columnName = other.getColumnName();
                    if (this$columnName == null) {
                        if (other$columnName == null) {
                            break label59;
                        }
                    } else if (this$columnName.equals(other$columnName)) {
                        break label59;
                    }

                    return false;
                }

                Object this$fieldName = this.getFieldName();
                Object other$fieldName = other.getFieldName();
                if (this$fieldName == null) {
                    if (other$fieldName != null) {
                        return false;
                    }
                } else if (!this$fieldName.equals(other$fieldName)) {
                    return false;
                }

                Object this$fieldClass = this.getFieldClass();
                Object other$fieldClass = other.getFieldClass();
                if (this$fieldClass == null) {
                    if (other$fieldClass != null) {
                        return false;
                    }
                } else if (!this$fieldClass.equals(other$fieldClass)) {
                    return false;
                }

                Object this$fieldComment = this.getFieldComment();
                Object other$fieldComment = other.getFieldComment();
                if (this$fieldComment == null) {
                    if (other$fieldComment != null) {
                        return false;
                    }
                } else if (!this$fieldComment.equals(other$fieldComment)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FieldInfo;
    }

    @Override
    public int hashCode() {

        int result = 1;
        Object $columnName = this.getColumnName();
        result = result * 59 + ($columnName == null ? 43 : $columnName.hashCode());
        Object $fieldName = this.getFieldName();
        result = result * 59 + ($fieldName == null ? 43 : $fieldName.hashCode());
        Object $fieldClass = this.getFieldClass();
        result = result * 59 + ($fieldClass == null ? 43 : $fieldClass.hashCode());
        Object $fieldComment = this.getFieldComment();
        result = result * 59 + ($fieldComment == null ? 43 : $fieldComment.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FieldInfo(columnName=" + this.getColumnName() + ", fieldName=" + this.getFieldName() + ", fieldClass=" + this.getFieldClass() + ", fieldComment=" + this.getFieldComment() + ")";
    }
}
