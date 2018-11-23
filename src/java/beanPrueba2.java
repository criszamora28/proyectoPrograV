
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto PC
 */

@ManagedBean
@SessionScoped

public class beanPrueba2 
{
   private boolean visible;
   
   public void show()
   {
   visible=true;
   }
   public void hide()
   {
   visible=false;
   }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
