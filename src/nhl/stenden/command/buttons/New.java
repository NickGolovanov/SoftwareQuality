package nhl.stenden.command.buttons;

import nhl.stenden.command.Command;
import nhl.stenden.command.Receiver;

public class New implements Command
{
    private final Receiver receiver;
    private final String fileName;

    public New(Receiver receiver, String fileName)
    {
        this.receiver = receiver;
        this.fileName = fileName;
    }

    @Override
    public void execute()
    {
        this.receiver.save(this.fileName);
    }
}
