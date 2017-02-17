package dao;
import java.sql.Connection;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class conexionssh {

	private final static String S_PATH_FILE_PRIVATE_KEY = "C:\\Users\\Administrador\\workspace\\proyectoweb2\\id_rsa.ppk";
	private final static String S_PATH_FILE_KNOWN_HOSTS = "C:\\Users\\Administrador\\workspace\\proyectoweb2\\known_hosts";
	private final static String S_PASS_PHRASE = "mypassphrase";
	private final static int LOCAl_PORT = 3308; 
	private final static int REMOTE_PORT = 3306; 
	private final static int SSH_REMOTE_PORT = 22; 
	private final static String SSH_USER = "587fca0889f5cf057100004b";
	private final static String SSH_REMOTE_SERVER = "femxa-ebtm.rhcloud.com";
	private final static String MYSQL_REMOTE_SERVER = "127.11.220.2";
	private static Session sesion; //represents each ssh session

	
	public static void conectate_A_SSH () throws Throwable
	{
		JSch jsch = new JSch();
        jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
        jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY, S_PASS_PHRASE.getBytes());

        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
        sesion.connect(); //ssh connection established!

        sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT); 


	}
	
	public static void desconectate_D_SSH ()
	{
		sesion.disconnect();
	}

	


}
