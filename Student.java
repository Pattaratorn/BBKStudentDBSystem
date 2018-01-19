package bb2;

public class Student {
    private int ID;
    private String name;
    private String surname;
    private int level;
    private int order;
    private String levelString;
    
    public Student(int order,int ID, String name, String surname, int level)
    {
        this.order = order;
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.level = level;
        levelString = valueToText(level);
    }
    
    public int getOrder()
    {
        return order;
    }
    
    public int getID()
    {
        return ID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getSurname()
    {
        return surname;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public String getLevelString()
    {
        return valueToText(level);
    }
    
    private String valueToText(int level)
    {
        switch(level)
        {
            case 1 : return "อนุบาล 1";
            case 2 : return "อนุบาล 2";
            case 3 : return "อนุบาล 3";
            case 4 : return "ป.1";
            case 5 : return "ป.2";
            case 6 : return "ป.3";
            case 7 : return "ป.4";
            case 8 : return "ป.5";
            case 9 : return "ป.6";
        }
        return null;
    }
}
