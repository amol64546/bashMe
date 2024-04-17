package com.example.demo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.example.demo.ScriptPaths.TEST;


@ShellComponent
public class ScriptRunner
{

	private static final String scriptPath = TEST;


	@ShellMethod(key = "run")
	public static void main(@ShellOption(defaultValue = "spring")  String[] args) {

		setExecutePermissions();

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
				// Check if the line contains a prompt for input
				if (line.contains("[Y/n]")) {
					// Read user input
					BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Do you want to continue? [Y/n] ");
					String userInput = userInputReader.readLine();
					// Write user input to the process's input stream
					writer.write(userInput + "\n");
					writer.flush();
				}
			}

			// Check if the process is still running
			while (isProcessAlive(process)) {
				// Process is still running, sleep for a while
				Thread.sleep(1000);
			}

			// Get exit code
			int exitCode = process.exitValue();
			System.out.println("Exited code " + exitCode);
		} catch (Exception e) {
			// Handle any exceptions
			e.printStackTrace();
		}
	}



	public static void setExecutePermissions(){

		try {
			Process chmodProcess = Runtime.getRuntime().exec("chmod +x " + scriptPath);
			// Wait for the chmod process to finish
			int chmodExitCode = chmodProcess.waitFor();
			// Check if chmod executed successfully
			if (chmodExitCode != 0) {
				System.out.println("Failed to set execute permissions on the script file");
			}
		} catch (Exception e) {
			// Handle permission setting failure
			e.printStackTrace();
		}

	}


	// Helper method to check if a process is alive
	private static boolean isProcessAlive(Process process) {
		try {
			process.exitValue();
			return false; // Process has terminated
		} catch (IllegalThreadStateException e) {
			return true; // Process is still running
		}
	}
}
