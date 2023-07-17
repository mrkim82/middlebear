package com.groo.bear.pro.mapper;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        // List<String>을 필요한 형식으로 변환하여 PreparedStatement에 설정합니다.
        // 예를 들어, 여기서는 List<String>을 쉼표로 구분된 문자열로 변환하여 설정합니다.
        preparedStatement.setString(i, String.join(",", parameter));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        // ResultSet으로부터 값을 가져와서 List<String>으로 변환합니다.
        // 예를 들어, 여기서는 쉼표로 구분된 문자열을 List<String>으로 변환하여 반환합니다.
        String value = resultSet.getString(columnName);
        return Arrays.asList(value.split(","));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        // ResultSet으로부터 값을 가져와서 List<String>으로 변환합니다.
        // 예를 들어, 여기서는 쉼표로 구분된 문자열을 List<String>으로 변환하여 반환합니다.
        String value = resultSet.getString(columnIndex);
        return Arrays.asList(value.split(","));
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        // CallableStatement로부터 값을 가져와서 List<String>으로 변환합니다.
        // 예를 들어, 여기서는 쉼표로 구분된 문자열을 List<String>으로 변환하여 반환합니다.
        String value = callableStatement.getString(columnIndex);
        return Arrays.asList(value.split(","));
    }
}