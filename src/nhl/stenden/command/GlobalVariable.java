package nhl.stenden.command;

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

    private final String buttonName;

    GlobalVariable(String buttonName)
    {
        this.buttonName = buttonName;
    }

    public String getButtonName()
    {
        return this.buttonName;
    }


}
