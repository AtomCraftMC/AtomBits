package ir.alijk.megacore;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.TimeStampType;

import java.sql.SQLException;

public class LocalCurrentTimeStampType extends TimeStampType {
    private static final LocalCurrentTimeStampType singleton =
            new LocalCurrentTimeStampType();
    private String defaultStr;
    public LocalCurrentTimeStampType() {
        super(SqlType.DATE, new Class<?>[] { java.sql.Timestamp.class });
    }
    public static LocalCurrentTimeStampType getSingleton() {
        return singleton;
    }
    @Override
    public boolean isEscapedDefaultValue() {
        if ("CURRENT_TIMESTAMP()".equals(defaultStr)) {
            return false;
        } else {
            return super.isEscapedDefaultValue();
        }
    }
    @Override
    public Object parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
        this.defaultStr = defaultStr;
        if ("CURRENT_TIMESTAMP()".equals(defaultStr)) {
            return defaultStr;
        } else {
            return super.parseDefaultString(fieldType, defaultStr);
        }
    }
}
