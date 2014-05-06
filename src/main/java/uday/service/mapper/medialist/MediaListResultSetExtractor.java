package uday.service.mapper.medialist;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import uday.mediaorganizer.model.domainmodel.Media;
import uday.mediaorganizer.model.domainmodel.Movie;

public class MediaListResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		Media media = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3),
				rs.getString(4), rs.getDouble(5), rs.getString(6),
				rs.getString(7), rs.getString(8), rs.getString(9),
				rs.getString(10), null,rs.getString(11));
		return media;
	}
}
