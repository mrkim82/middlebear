package com.groo.bear.pro.mapper;

import java.sql.*;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class OracleArrayHandler implements TypeHandler<Object> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
//		OracleConnection conn = ps.getConnection().unwrap(OracleConnection.class);
//		Array reportsArray = (Array)conn.createOracleArray("STRINGARRAY", (String[]) parameter);
//		ps.setArray(i, reportsArray);
//		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}