package com.groo.bear.pro.mapper;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import oracle.jdbc.OracleConnection;

public class OracleArrayHandler2 implements TypeHandler<List<?>> {

	@Override
	public void setParameter(PreparedStatement ps, int i, List<?> parameter, JdbcType jdbcType) throws SQLException {
		OracleConnection conn = ps.getConnection().unwrap(OracleConnection.class);
		Array reportsArray = (Array)conn.createOracleArray("STRINGARRAY", parameter.toArray());
		ps.setArray(i, reportsArray);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<?> getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}