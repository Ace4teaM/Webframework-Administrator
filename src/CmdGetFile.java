


import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import swing.AppFrame;

import app.AppCommand;
import app.AppParam;
import app.Result;
import field.StringField;

/**
 * Obtient le contenu d'un fichier distant
 */
public class CmdGetFile extends AppCommand{
	
	private AppCommand[] subCmdList = new AppCommand[]{
			new CmdSaveContent()
	};
	
	public CmdGetFile(){
		super(
				"Ouvrir URL",
				KeyEvent.VK_F,
				null,//"open_url.png",
				null,
				"Ouvrir un fichier depuis une URL"
		);
		
		//liste les paramètres
		this.param = new AppParam(this.getClass().getName());
		this.param.fields.put("path", new StringField("Fichier","version"));
	}

	public CmdGetFile(String path){
		this();
		
		//paramètres
		this.param.set("path", path);
	}

	/**
	 * Execute la commande
	 * @param context Context d'execution de la commande
	 * @return Resultat
	 */
	public Result exec(Object context){
		if(context instanceof AppFrame){
			AppFrame wnd = (AppFrame)context;
	
			//initialise l'url
			URL url;
			try {
				url = new URL(this.param.get("path"));
			} catch (MalformedURLException e1) {
				return new Result(Result.ERR_CODE_FAILED,"invalid_path_parameter");
			} 
	
			//ouvre une connexion sur le fichier et lie le contenu
	        String text = "";
			try{
				URLConnection urlConnection = url.openConnection();
				urlConnection.setConnectTimeout(1000);
				urlConnection.setReadTimeout(1000);
				
				InputStreamReader stream = new InputStreamReader(urlConnection.getInputStream());
	
				BufferedReader in = new BufferedReader(stream);
	
		        while ((text = in.readLine()) != null){
		            System.out.println(text);
		        }
		        in.close();
			}
			catch (SocketTimeoutException e){
				//e.printStackTrace();
				return new Result(Result.ERR_CODE_FAILED,"connection_timeout");
			}
			catch (IOException e){
				//e.printStackTrace();
				return new Result(Result.ERR_CODE_FAILED,"cant_open_file");
			}
			
			System.out.println("text_content\n"+text);
	        this.param.set("text_content", text);
	        
			return Result.ERR_OK;
		}
		
		return new Result(Result.ERR_CODE_FAILED,"unsupported_command_context");
	}
	

	/** Commandes enfants */
	public AppCommand[] getSubCommands(){
		return this.subCmdList;
	}
	
	/** 
	 * Sauvegarde le fichier (sous-commande)
	 */
	class CmdSaveContent extends AppCommand{

		public CmdSaveContent(){
			super(
					"Sauvegarder",
					KeyEvent.VK_S,
					null,
					null,
					"Sauvegarde le fichier"
			);
		}
		
		public Result exec(Object context){
			if(context instanceof CmdGetFile)
			{
				CmdGetFile file = (CmdGetFile)context;
				return Result.ERR_OK;
			}
			return new Result(Result.ERR_CODE_FAILED,"unsupported_command_context");
		}
	}
}
