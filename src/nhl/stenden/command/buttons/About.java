package nhl.stenden.command.buttons;

import nhl.stenden.AboutBox;
import nhl.stenden.command.Command;

public class About implements Command
{
    @Override
    public void execute()
    {
        AboutBox.show();
    }
}
