



import java.awt.event.KeyEvent;

import javax.swing.JInternalFrame;

import swing.AppFrame;

import app.AppCommand;
import app.AppParam;
import app.Result;
import field.StringField;

/**
 * Obtient une connexion vers le serveur web distant
 */
public class CmdConnection extends AppCommand{

	public CmdConnection(){
		super(
				/*wnd,*/
				"Connexion",
				KeyEvent.VK_C,
				"connect_no.png",
				null,
				"Connexion au serveur web"
		);

		//paramètres
		this.param = new AppParam(this.getClass().getName());
		this.param.fields.put("url", new StringField("Site Web (URL)","http://www.id-informatik.com"));
		this.param.fields.put("pwd", new StringField("Mot-de-passe",""));
	}

	public CmdConnection(String url,String pwd){
		this();
		
		//paramètres
		this.param.set("path", url);
		this.param.set("pwd", pwd);
	}

	/**
	 * Execute la commande
	 * @param context Context d'execution de la commande
	 * @return Resultat
	 */
	public Result exec(Object context){
		if(context instanceof AppFrame){
			AppFrame wnd = (AppFrame)context;
			
			//connexion...
			System.out.println("connexion à "+this.param.get("url")+"...");
			
			// obtient le fichier
			CmdGetFile defaultFile = new CmdGetFile(this.param.get("url")+"/default.xml");
			Result getFileResult = defaultFile.exec(wnd);
			if(getFileResult.isOK()){
				// initialise la frame
				JInternalFrame frame = wnd.addFrame(new SiteInfosPanel());
				return Result.ERR_OK;
			}

			return getFileResult;
		}
		
		return new Result(Result.ERR_CODE_FAILED,"unsupported_command_context");
	}
}
