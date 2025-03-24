package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Comand;
import nhl.stenden.comand.Receiver;

import javax.swing.*;

import static nhl.stenden.comand.GlobalVariable.PAGENR;


public class GoTo implements Comand
{
    private Receiver receiver;

    public GoTo(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        String pageNumberStr = JOptionPane.showInputDialog((Object)PAGENR.getButtonName());
        int pageNumber = Integer.parseInt(pageNumberStr);
        this.receiver.setSlideNumber(pageNumber - 1);
    }
}
