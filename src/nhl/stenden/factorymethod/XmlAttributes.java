package nhl.stenden.factorymethod;

public enum XmlAttributes
{
    SHOW_TITLE("showtitle"),
    SLIDE_TITLE("title"),
    SLIDE("slide"),
    ITEM("item"),
    LEVEL("level"),
    KIND("kind"),
    TEXT("text"),
    IMAGE("image");

    private final String attribute;

    XmlAttributes(String attribute)
    {
        this.attribute = attribute;
    }

    public String getAttribute()
    {
        return this.attribute;

    }
}
