package cc.overwall.overwall.Bean;

public class Node
{
    private  int id;
    private  String name;
    private  String info;
    private  String server;
    private  String status;
    private  int node_class;
    private int online;


    
   
    
    
    


    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }

    public void setServer(String server)
    {
        this.server = server;
    }

    public String getServer()
    {
        return server;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public int getNode_class() {
        return node_class;
    }

    public void setNode_class(int node_class) {
        this.node_class = node_class;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}
