package TempConvert.mvc.command_processor;

import TempConvert.mvc.TempConvertModel;

public abstract class Command {
    protected TempConvertModel tempConvertModelRemote;
    public abstract void execute();
}
