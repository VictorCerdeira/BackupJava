
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AutomaticBackup {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String timeStamp = dateFormat.format(new Date());
                    String sourceDirectoryPath = "C:\\Users\\Usuario\\dados_originais";
                    String backupDirectoryPath = "C:\\Users\\Usuario\\backup";
                    String backupFileName = "backup_" + timeStamp;
                    
                    backupDirectoryPath = backupDirectoryPath + File.separator + backupFileName;
                    
                    File sourceDirectory = new File(sourceDirectoryPath);
                    File backupDirectory = new File(backupDirectoryPath);
                    
                    if (!backupDirectory.exists()) {
                        backupDirectory.mkdirs();
                    }
                    
                    File[] files = sourceDirectory.listFiles();
                    for (File file : files) {
                        File backupFile = new File(backupDirectoryPath + File.separator + file.getName());
                        Files.copy(file.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    
                    System.out.println("Backup realizado com sucesso em: " + backupDirectoryPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
       
