package com.groo.bear.pro.mapper;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import com.groo.bear.pro.service.todovote.CreateVoteSubVO;

import oracle.jdbc.OracleConnection;

public class OracleArrayStructHandler implements TypeHandler<Object> {

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getString(columnIndex);
	}
  
	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter,	JdbcType jdbcType) throws SQLException {
		if (parameter == null)
			return;
		OracleConnection conn = ps.getConnection().unwrap(OracleConnection.class); 	
		List<CreateVoteSubVO> voteDetail = (List<CreateVoteSubVO>)parameter;
	    Object[] filetype = new Object[2]; 
	    Struct[] array = new Struct[voteDetail.size()];

	    int arrayIndex = 0;
	    for (CreateVoteSubVO file : voteDetail) {
	    	filetype[0] = file.getVoteDetailContent();
	    	filetype[1] = file.getVoteDetailImg();
	    	array[arrayIndex++] = conn.createStruct("VOTEDETAILS", filetype);
	    }		
		Array filearray = (Array)conn.createOracleArray("VOTEARRAY", (Struct[]) array);		
		ps.setArray(i, filearray);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {

		String value = "";
		try {
			if (!StringUtils.hasText(rs.getString(columnName))) {
				value = new String(rs.getString(columnName).getBytes("8859_1"), "KSC5601");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

}