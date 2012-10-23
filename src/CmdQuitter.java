



import java.awt.event.KeyEvent;

import swing.AppFrame;

import app.AppCommand;
import app.AppParam;
import app.Result;
import field.BooleanField;


/**
 * Ferme l'application
 */
public class CmdQuitter extends AppCommand{

	public CmdQuitter(/*JFrame wnd*/){
		super(
				/*wnd,*/
				"Quitter",
				KeyEvent.VK_E,
				"cancel.png",
				null,
				"Quitte l'application"
		);
		
		//paramètres
		this.param = new AppParam(this.getClass().getName());
		this.param.fields.put("save_param", new BooleanField("Sauvegarde des paramètres",true));
	}
	
	/**
	 * Execute la commande
	 * @return Resultat
	 */
	public Result exec(Object context){
		if(context instanceof AppFrame){
			AppFrame wnd = (AppFrame)context;
			wnd.setVisible(false);
			return Result.ERR_OK;
		}
		return new Result(Result.ERR_CODE_FAILED,"unsupported_command_context");
	}
}
