package tool.model.formateTyp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ServerFormate {

	public void export(ResultSet rs, String filename, String outPut) throws SQLException, IOException;
	
}
