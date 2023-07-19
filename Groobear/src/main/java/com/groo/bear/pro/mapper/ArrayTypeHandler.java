package com.groo.bear.pro.mapper;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrayTypeHandler extends BaseTypeHandler<String[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        Array array = ps.getConnection().createArrayOf("VARCHAR", parameter);
        ps.setArray(i, array);
    }

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convertArray(rs.getArray(columnName));
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertArray(rs.getArray(columnIndex));
    }

    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertArray(cs.getArray(columnIndex));
    }

    private String[] convertArray(Array array) throws SQLException {
        if (array == null) {
            return null;
        }
        return (String[]) array.getArray();
    }
}

