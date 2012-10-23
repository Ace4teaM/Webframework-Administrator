

import javax.swing.JOptionPane;

import swing.AppFrame;

import app.AppCommand;
import app.Result;


public class CmdPropos extends AppCommand{
	
	public CmdPropos(){
		super(
				"A Propos...",
				0,
				null,
				null,
				"A Propos de l'application"
		);
	}

	/**
	 * Execute la commande
	 * @return Resultat
	 */
	public Result exec(Object context){
		//fenetre principale
		if(context instanceof AppFrame){
			AppFrame wnd = (AppFrame)context;
			JOptionPane.showInputDialog(wnd,"Hello");
			return Result.ERR_OK;
		}
		return new Result(Result.ERR_CODE_FAILED,"unsupported_command_context");
	}

}
