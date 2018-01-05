package wishlist;

/**
 * Created by mara.tatar on 1/5/2018.
 */

public class Goal {
    private String Name;
    private int Priority;
    private int Status;
    private boolean isPersonal=true;
    public Goal(){}
    public Goal(String name, int priority, int status) {
        Name = name;
        Priority = priority;
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    public void setPersonal(boolean personal) {
        isPersonal = personal;
    }
}
