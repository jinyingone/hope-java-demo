package fun.jinying.infrastructure.persistence.types;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 21:43
 **/
public class StringJoinerTypeHandler extends BaseTypeHandler<List<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.join(",", strings));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String[] split = resultSet.getString(s).split(",");
        return Stream.of(split).collect(Collectors.toList());
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String[] split = resultSet.getString(i).split(",");
        return Stream.of(split).collect(Collectors.toList());
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String[] split = callableStatement.getString(i).split(",");
        return Stream.of(split).collect(Collectors.toList());
    }
}
