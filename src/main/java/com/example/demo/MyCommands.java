package com.example.demo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@ShellComponent
public class MyCommands {

	@ShellMethod(key = "run")
	public void helloWorld(@ShellOption(defaultValue = "spring") String arg) {
		// Path to the Bash script
		String scriptPath = "/home/amol/Downloads/demo/src/main/resources/install_mongo.sh";

		// Set execute permissions on the script file
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

		// Run the Bash script
		try {
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", scriptPath);
			pb.redirectErrorStream(true);
			Process process = pb.start();
			// Read output
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
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

	private static boolean isProcessAlive(Process process) {
		try {
			process.exitValue();
			return false; // Process has already terminated
		} catch (IllegalThreadStateException e) {
			return true; // Process is still running
		}
	}
}
