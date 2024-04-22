package bashMe;

import bashMe.utils.ScriptUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static bashMe.utils.ScriptPaths.TEST;


@ShellComponent
public class CommandRunner
{

    @ShellMethod(key = "test", value = "Test the script")
    public void test()
    {
        ScriptUtils.runner(TEST.getPath());
    }




}
