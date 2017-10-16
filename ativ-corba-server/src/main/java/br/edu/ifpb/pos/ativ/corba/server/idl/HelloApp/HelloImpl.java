/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.ativ.corba.server.idl.HelloApp;

import org.omg.CORBA.ORB;

/**
 *
 * @author kieckegard
 */
public class HelloImpl extends HelloPOA {
    
    private ORB orb;
    
    public HelloImpl(ORB orb) {
        this.orb = orb;
    }
    
    public HelloImpl() {
        
    }

    @Override
    public String helloworld() {
        return "Hello World! :)";
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
    
    
    
}
