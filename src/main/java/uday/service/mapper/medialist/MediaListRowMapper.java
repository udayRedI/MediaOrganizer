package uday.service.mapper.medialist;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MediaListRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		MediaListResultSetExtractor extractor = new MediaListResultSetExtractor();
		return extractor.extractData(rs);
	}
}
