package com.example.demo;

import com.example.demo.utils.ScriptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.example.demo.utils.ScriptPaths.TEST;


@ShellComponent
@RequiredArgsConstructor
public class CommandRunner
{

    private final ScriptUtils scriptUtils;

    @ShellMethod(key = "test", value = "Test the script")
    public void test()
    {
        scriptUtils.runner(TEST.getPath());
    }




}
