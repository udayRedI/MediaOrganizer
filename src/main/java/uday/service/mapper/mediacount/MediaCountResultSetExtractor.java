package uday.service.mapper.mediacount;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

public class MediaCountResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		rs.first();
		int count = rs.getInt(1);
		return count;
	}
}
