package nhl.stenden.command.buttons;

import nhl.stenden.command.Command;
import nhl.stenden.command.Receiver;

public class Open implements Command
{
    private Receiver receiver;
    private String fileName;

    public Open(Receiver receiver, String fileName)
    {
        this.receiver = receiver;
        this.fileName = fileName;
    }

    @Override
    public void execute()
    {
        this.receiver.openPresentation(this.fileName);
    }
}