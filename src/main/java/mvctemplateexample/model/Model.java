package mvctemplateexample.model;

import java.util.ArrayList;
import java.util.List;

public class Model extends mvctemplate.model.Model implements java.io.Serializable {
	
	/**
	 * Serialiserad data är:
	 * 
	 * private List<Routine> routines = new ArrayList<Routine>();
	 * private List<Instance> instances = new ArrayList<Instance>();
	 */
	private static final long serialVersionUID = 1L;
		

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void checkConsistency() {
		if (getOldGames() == null)
			setOldGames(new ArrayList());
	}
	
	@SuppressWarnings("rawtypes")
	public String getState() {
		
		if (getCurrentGame() != null) {
			
			Game currentGame = (mvctemplateexample.model.Game)getCurrentGame();
			
			StringBuffer buf = new StringBuffer();
			buf.append("**** Current Game ****\n");
			buf.append(currentGame.toString());
			buf.append("\n");
						
			
			return buf.toString();
			
			
		} else {
			StringBuffer buf = new StringBuffer();
			buf.append("No game running\n");
			buf.append("**** Old Games ****\n");
			
			List oldGames = getOldGames();
			for (int index = 0; index < oldGames.size(); index++) {
				buf.append("" + (index + 1) + ". ");
				buf.append(oldGames.get(index).toString());
				buf.append("\n");
			}
			
			return buf.toString();
			
		}
				
		
		
	}
		
}
