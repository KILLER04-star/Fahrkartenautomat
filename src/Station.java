public class Station
{
    String name;
    String street;
    String county;
    String postal_Code;
    public Station(String name,String street, String county, String postal_Code)
    {
        this.name = name;
        this.street = street;
        this.county = county;
        this.postal_Code = postal_Code;
    }
    public String getName()
    {
        return name;
    }
    public String getCounty()
    {
        return county;
    }
    public String getStreet()
    {
        return street;
    }
    public String getPostal_Code()
    {
        return postal_Code;
    }
}
