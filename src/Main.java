import javax.swing.SwingUtilities;

import app.AppCommand;
import app.AppCommandGroup;

import swing.AppFrame;


public class Main{
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//liste des commandes
				AppCommandGroup cmd[] = new AppCommandGroup[]{
						new AppCommandGroup("Application", this, new AppCommand[]{ new CmdConnection(),new CmdQuitter() }),
						new AppCommandGroup("Aide", this, new AppCommand[]{ new CmdPropos() })
				};
				
				//On crée une nouvelle instance de notre JDialog
				AppFrame app = new AppFrame("Webframework Administrator",cmd,true,true);
				
				//ajoute les codes d'erreurs
				app.addErrorCode("unsupported_command_context","Commande non supporté dans ce contexte");
				app.addErrorCode("connection_timeout","Connexion impossible, le serveur ne répond pas");
				
				//affiche la frame
				app.setVisible(true);//On la rend visible
			}
		});
	}
}

