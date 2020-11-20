package mvctemplateexample.model;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The parameters of one game.
 * 
 * @author igor
 *
 */
public class Game extends mvctemplate.model.Game implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date start, end;

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	public Game() {
		start = new Date();
	}
	
	
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Game (");
		buf.append(format.format(start));
		buf.append(" -> ");
		if (getEnd() != null)
			buf.append(format.format(end));
		buf.append(")");
			
		
		return buf.toString();
		
	}



	private Object getEnd() {
		return end;
	}


	public Date getStart() {
		return start;
	}


	public void setEnd(Date end) {
		this.end = end;
	}


	public void setStart(Date start) {
		this.start = start;
	}
	
	@Override
	public void endGame() {
		setEnd(new Date());
	}
}
