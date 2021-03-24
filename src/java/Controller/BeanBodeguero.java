/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Fabian
 */
@Named(value = "beanBodeguero")
@SessionScoped
public class BeanBodeguero implements Serializable {

    /**
     * Creates a new instance of BeanBodeguero
     */
    public BeanBodeguero() {
        
        
    }
    
}
