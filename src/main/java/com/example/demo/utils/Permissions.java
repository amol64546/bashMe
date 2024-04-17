package com.example.demo.utils;

import static org.springframework.shell.command.invocation.InvocableShellMethod.log;

public class Permissions
{

    public static void setExecutePermissions(String scriptPath){

        try {
            Process chmodProcess = Runtime.getRuntime().exec("chmod +x " + scriptPath);
            // Wait for the chmod process to finish
            int chmodExitCode = chmodProcess.waitFor();
            // Check if chmod executed successfully
            if (chmodExitCode != 0) {
                log.error("Failed to set execute permissions on the script file");
            }
        } catch (Exception e) {
            log.error("Error setting execute permissions on the script file", e);
        }

    }
}

