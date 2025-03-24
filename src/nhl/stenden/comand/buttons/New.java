package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Comand;
import nhl.stenden.comand.Receiver;

public class New implements Comand
{
    /**
     *
     */

    private Receiver receiver;

    public New(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        this.receiver.save();
    }
}
