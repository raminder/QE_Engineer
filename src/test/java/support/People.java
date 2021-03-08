package support;
import java.util.List;

public class People
{
    private int count;

    private String next;

    private String previous;

    private List<Results> results;

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setNext(String next){
        this.next = next;
    }
    public String getNext(){
        return this.next;
    }
    public void setPrevious(String previous){
        this.previous = previous;
    }
    public String getPrevious(){
        return this.previous;
    }
    public void setResults(List<Results> results){
        this.results = results;
    }
    public List<Results> getResults(){
        return this.results;
    }
}
