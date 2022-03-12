package tool.model.formateTyp;

import java.sql.ResultSet;

public interface ServerFormate {

	public void export(ResultSet rs, String filename, String outPut);
	
}
