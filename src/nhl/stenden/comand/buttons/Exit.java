package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Comand;
import nhl.stenden.comand.Receiver;

import java.rmi.server.RemoteRef;

public class Exit implements Comand
{

    /**
     *
     */

    private Receiver receiver;
    private static final int ZERO = 0;

    public Exit(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        this.receiver.exit(this.ZERO);
    }
}
