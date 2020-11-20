package mvctemplateexample.controller;

import java.io.IOException;
import java.util.List;

import mvctemplate.controller.Controller;
import mvctemplate.dao.DAO;
import mvctemplate.dao.ObjectStreamDAO;
import mvctemplateexample.model.Model;
import mvctemplate.view.CommandLineView;
import mvctemplate.view.View;
import mvctemplateexample.model.Game;
import util.parser.Callable;
import util.parser.ChoiceToken;
import util.parser.Command;
import util.parser.StringToken;
import util.parser.Token;
import util.parser.UnknownCommandException;
import util.parser.UserInterruptException;

public class ControllerImplementation {
	
	private Controller controller;
	private Model model;
	private View view;
	private DAO dao;
	
	
	public ControllerImplementation(Controller controller) {
		
		this.controller = controller;
		try {
			model = (mvctemplateexample.model.Model)controller.getModel();
		} catch (ClassCastException e) {
			model = new Model();
			controller.setModel(model);
		}
		view = controller.getView();
		dao = controller.getDAO();
		
		
		
		
		  
		controller.add(new StartGame());		
		controller.add(new EndGame());
		
		
		controller.add(new ExitProgram());

	}

	
	

	public class StartGame extends Command implements Callable {
		public StartGame() {
			// Startar ett nytt spel
			// start game primaryincreasing [float]  
			
			add(new StringToken("start"));
			add(new StringToken("game"));
			
					
			addCallable(this);
		}
		
		public void exec(List<Token> tokens) {
			model.startGame(new Game());
		}
	}
		
		
	
	public class EndGame extends Command implements Callable {
		public EndGame() {
			// end game
			// avslutar det nuvarande spelet och sparar det till gamla.  
			
			add(new StringToken("end"));
			add(new StringToken("game"));
								
			addCallable(this);
		}
		
		public void exec(List<Token> tokens) {
			model.endCurrentGame();
		}
	}
		

	public class ExitProgram extends Command implements Callable {
		public ExitProgram() {
			ChoiceToken token = new ChoiceToken();
			token.add("quit");
			token.add("exit");
			
			add(token);
					
			addCallable(this);
		}
		
		public void exec(List<Token> tokens) {
			try {
				dao.persist();
			} catch (IOException e) {

				view.messageToUser(e.toString());
				return; 
			}
			System.exit(0);
		}
	}
	
	
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		
		Controller controller = null;
		View view = new CommandLineView();
		
		
		controller = new Controller(new ObjectStreamDAO(), view);
		
		ControllerImplementation controllerImplementation = new ControllerImplementation(controller);
			
		
		controllerImplementation.run();
		
	}

	private void run() throws IOException {
		
		view.startMessage();
		
		while(true) {
			
			
			if (model.getCurrentGame() == null) {
				
				view.showStatus(model);
				
				view.addChoice(new StartGame());
				view.addChoice(new ExitProgram());
				String command = view.showChoices();
				try {
					controller.processInput(command);
				} catch (UnknownCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserInterruptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				view.showStatus(model);
				
				view.addChoice(new EndGame());
				view.addChoice(new ExitProgram());
				String command = view.showChoices();
				try {
					controller.processInput(command);
				} catch (UnknownCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserInterruptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}

			
			dao.persist();
				
		}
		
	}

}
