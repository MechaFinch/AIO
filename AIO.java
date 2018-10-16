package aio;


public class AIO {	//This is the class you call to use 
	public static AIOUser user = new AIOUser();	//Call AIO.user.<method>
	public static AIOFile file = new AIOFile(); //Call AIO.file.<method>
	
	//Instance edition user and file if needed
	public AIOUser iUser;
	public AIOFile iFile;
	
	public AIO() {
		iUser = new AIOUser();
		iFile = new AIOFile();
	}
}