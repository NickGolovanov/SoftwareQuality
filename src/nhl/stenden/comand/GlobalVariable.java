package nhl.stenden.comand;

public enum GlobalVariable
{
    ABOUT("About"),
    FILE("File"),
    EXIT("Exit"),
    GOTO("Go to"),
    HELP("Help"),
    NEW("New"),
    NEXT("Next"),
    OPEN("Open"),
    PAGENR("Page number?"),
    PREV("Prev"),
    SAVE("Save"),
    VIEW("View");

    private String buttonName;

    GlobalVariable(String buttonName)
    {
        this.buttonName = buttonName;
    }

    public String getButtonName()
    {
        return buttonName;
    }


}
