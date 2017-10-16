/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.ativ.corba.client.idl.main;

import br.edu.ifpb.pos.ativ.corba.client.idl.HelloApp.Hello;
import br.edu.ifpb.pos.ativ.corba.client.idl.HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 *
 * @author kieckegard
 */
public class Loader {
    
    public static void main(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        
        
        String[] config = new String[]{
            "-ORBInitialPort", "1050",
            "-ORBInitialHost", "ativ-corba-register"
        };
        
        //init ORB
        ORB orb = ORB.init(config, System.getProperties());
        
        //get orb object instance of NameService
        Object nameServiceORB = orb.resolve_initial_references("NameService");
        //parse to NamingContextExt interface
        NamingContextExt namingContextExt = NamingContextExtHelper
                .narrow(nameServiceORB);
        
        //gets orb object instance of Hello 
        Object helloORB = namingContextExt.resolve_str("Hello");
        //parses to Hello interface
        Hello hello = HelloHelper.narrow(helloORB);
        
        //do RPC
        System.out.println(hello.helloworld());
        
    }
}
