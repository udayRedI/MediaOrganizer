package uday.service.mapper.searchmedia;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SearchMediaRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		SearchMediaResultSetExtractor extractor = new SearchMediaResultSetExtractor();
		return extractor.extractData(rs);
	}
}
