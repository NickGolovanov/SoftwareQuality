package nhl.stenden;

public enum ErrorMessage
{
    FILE_ERROR("File "),
    NOT_FOUND(" not found"),
    PARSER_CONFIG_ERROR("Parser Configuration Exception"),
    UNKNOWN_TYPE("Unknown Element type"),
    NUMBER_FORMAT_ERROR("Number Format Exception"),
    IOEX("IO Exception: "),
    LOADERR("Load Error"),
    SAVEERR("Save Error"),
    INVALID_PAGE_NUMBER("Invalid page number");


    private final String errorMessage;

    ErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return this.errorMessage;

    }
}
