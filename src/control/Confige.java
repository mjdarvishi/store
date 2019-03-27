package control;

import java.util.ResourceBundle;

public class Confige {
    private  ResourceBundle resourceBundle;
private static Confige confige;

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
public static Confige getConfic() {
    if(confige==null)
        confige=new Confige();
    return confige;
}
private Confige(){
    
}

}
