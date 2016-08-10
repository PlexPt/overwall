package cc.overwall.overwall.Bean;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class NodeInfo
{   
    private int ret ;
    private  String msg;
    private ArrayList<Node> data;





    public void setRet(int ret)
    {
        this.ret = ret;
    }

    public int getRet()
    {
        return ret;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setData(ArrayList<Node> data)
    {
        this.data = data;
    }

    public ArrayList<Node> getData()
    {
        return data;
    }}
