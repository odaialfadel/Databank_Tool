package tool.model.formateTyp;

import java.sql.ResultSet;

/**
 * because i only have one methode
 */

public interface ServerFormat {

	public void export(ResultSet rs, String filename, String outPut);

	public int getRowIndex();
	
}
