package uday.service.mapper.searchmedia;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

public class SearchMediaResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		return rs.getObject(1);
	}
}
