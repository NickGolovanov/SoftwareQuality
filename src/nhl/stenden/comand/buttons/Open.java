package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Comand;
import nhl.stenden.comand.Receiver;

public class Open  implements Comand
{
    /**
     *
     */

    private Receiver receiver;
    private String file;

    public Open(Receiver receiver, String file)
    {
        this.receiver = receiver;
        this.file = file;
    }

    @Override
    public void execute()
    {
        receiver.openPresentation(this.file);
    }
}