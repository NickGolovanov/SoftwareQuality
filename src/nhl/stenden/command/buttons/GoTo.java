package nhl.stenden.command.buttons;

import nhl.stenden.ErrorMessage;
import nhl.stenden.command.Command;
import nhl.stenden.command.GlobalVariable;
import nhl.stenden.command.Receiver;

import javax.swing.*;


public class GoTo implements Command
{
    private final Receiver receiver;

    public GoTo(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        String pageNumberString = JOptionPane.showInputDialog(GlobalVariable.PAGENR.getButtonName());
        if (pageNumberString != null && !pageNumberString.trim().isEmpty())
        {
            try
            {
                int pageNumber = Integer.parseInt(pageNumberString);
                this.receiver.setSlideNumber(pageNumber - 1);
            } catch (NumberFormatException e)
            {
                System.err.println(ErrorMessage.INVALID_PAGE_NUMBER.getErrorMessage());
            }
        }
    }
}
