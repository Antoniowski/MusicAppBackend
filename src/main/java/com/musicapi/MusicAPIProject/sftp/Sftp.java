package com.musicapi.MusicAPIProject.sftp;
import java.io.IOException;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

public class Sftp {

    private final String SERVER_URL = "192.168.1.171";
    private final Integer PORT = 22;
    private final String USERNAME = "musicApp";
    private final String PASSWORD = "musicApp";

    private static Sftp instance = null;
    private SSHClient clientSSH;
    
    private Sftp() throws IOException{
    }

    private void setupSSHClient() throws Exception{
            this.clientSSH = new SSHClient();
            this.clientSSH.addHostKeyVerifier(new PromiscuousVerifier());
            this.clientSSH.useCompression();
            this.clientSSH.connect(SERVER_URL,PORT);
            this.clientSSH.authPassword(USERNAME,PASSWORD);
    }

    public static Sftp getInstance() throws IOException{
        if(instance == null)
            instance = new Sftp();

        return instance;
    }

    public Integer makeDirectory(String dirName){
        try{
            setupSSHClient();
            SFTPClient sftp = this.clientSSH.newSFTPClient();
            sftp.mkdir(dirName);
            sftp.close();
            this.clientSSH.disconnect();
            return 0;    
        }catch(Exception e){
            System.out.println(e);
            return 1;
        }
        
    }

    public Integer uploadFile(String localFilePath, String DirName, String fileName){
        try{
            setupSSHClient();
            SFTPClient sftp = this.clientSSH.newSFTPClient();
            sftp.put(localFilePath, "./"+DirName+"/"+fileName);
            sftp.close();
            this.clientSSH.disconnect();
            return 0;
        }catch(Exception e){
            System.out.println(e);
            return 1;
        }
        
    }



}
