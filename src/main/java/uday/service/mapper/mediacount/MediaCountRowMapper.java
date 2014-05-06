package uday.service.mapper.mediacount;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MediaCountRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		MediaCountResultSetExtractor extractor = new MediaCountResultSetExtractor();
		return extractor.extractData(rs);
	}
}
