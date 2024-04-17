package com.example.demo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.example.demo.utils.ScriptPaths.TEST;
import static com.example.demo.utils.Permissions.setExecutePermissions;
import static org.springframework.shell.command.invocation.InvocableShellMethod.log;


@ShellComponent
public class ScriptRunner
{

	private static final String scriptPath = TEST;


	@ShellMethod(key = "run", value = "Run the script")
	public void run() {

		setExecutePermissions(scriptPath);

		try {

			// Run the Bash script
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", scriptPath);
			pb.redirectErrorStream(true);
			Process process = pb.start();

			// Read output and wait for the script to ask for input
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (Exception e) {
			log.error("Error running the script", e);
		}
	}





}
