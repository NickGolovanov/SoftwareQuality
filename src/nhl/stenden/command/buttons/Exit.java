package nhl.stenden.command.buttons;

import nhl.stenden.command.Command;
import nhl.stenden.command.Receiver;

public class Exit implements Command
{
    private final Receiver receiver;

    private static final int EXIT_CODE = 0;

    public Exit(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        this.receiver.exit(EXIT_CODE);
    }
}
