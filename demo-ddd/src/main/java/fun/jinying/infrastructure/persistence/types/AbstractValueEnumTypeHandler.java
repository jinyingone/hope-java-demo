package fun.jinying.infrastructure.persistence.types;

import fun.jinying.domain.shard.model.ValueEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 11:01
 **/
public abstract class AbstractValueEnumTypeHandler<T extends ValueEnum> extends BaseTypeHandler<ValueEnum> {
    /**
     * 转换方法
     *
     * @param value
     * @return
     */
    public abstract ValueEnum valueOf(int value);

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ValueEnum valueEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, valueEnum.getValue());
    }

    @Override
    public ValueEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return valueOf(resultSet.getInt(s));

    }

    @Override
    public ValueEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return valueOf(resultSet.getInt(i));
    }

    @Override
    public ValueEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return valueOf(callableStatement.getInt(i));
    }
}
