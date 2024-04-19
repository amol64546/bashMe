package com.example.demo;

import com.example.demo.utils.ScriptUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.example.demo.utils.ScriptPaths.TEST;


@ShellComponent
public class CommandRunner
{

    @ShellMethod(key = "test", value = "Test the script")
    public void test()
    {
        ScriptUtils.runner(TEST.getPath());
    }




}
