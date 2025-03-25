package nhl.stenden.command.buttons;

import nhl.stenden.command.Command;
import nhl.stenden.command.GlobalVariable;
import nhl.stenden.command.Receiver;

import javax.swing.*;


public class GoTo implements Command
{
    private Receiver receiver;

    public GoTo(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        String pageNumberString = JOptionPane.showInputDialog(GlobalVariable.PAGENR.getButtonName());
        int pageNumber = Integer.parseInt(pageNumberString);
        this.receiver.setSlideNumber(pageNumber - 1);
    }
}
