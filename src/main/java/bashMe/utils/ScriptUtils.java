package bashMe.utils;

import bashMe.Exceptions.ExecutePermissionException;
import bashMe.Exceptions.ScriptException;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.shell.command.invocation.InvocableShellMethod.log;

@UtilityClass
public class ScriptUtils
{

    public void setExecutePermissions(String scriptPath){

        try {
            Process chmodProcess = Runtime.getRuntime().exec("chmod +x " + scriptPath);
            // Wait for the chmod process to finish
            int chmodExitCode = chmodProcess.waitFor();
            // Check if chmod executed successfully
            if (chmodExitCode != 0) {
                log.error("Failed to set execute permissions on the script file");
            }
        } catch (InterruptedException | IOException e) {
            log.error("Error setting execute permissions on the script file", e);
            throw new ExecutePermissionException();
        }

    }

    public void runner(String scriptPath)
    {

        setExecutePermissions(scriptPath);

        try
        {
            // Run the Bash script
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", scriptPath);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Read output and wait for the script to ask for input
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }

        } catch (Exception e)
        {
            log.error("Error running the script", e);
            throw new ScriptException();
        }
    }
}

