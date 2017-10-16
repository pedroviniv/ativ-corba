/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.ativ.corba.server.main;

import br.edu.ifpb.pos.ativ.corba.server.idl.HelloApp.Hello;
import br.edu.ifpb.pos.ativ.corba.server.idl.HelloApp.HelloHelper;
import br.edu.ifpb.pos.ativ.corba.server.idl.HelloApp.HelloImpl;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author kieckegard
 */
public class Main {
    
    public static void main(String[] args) throws InvalidName, AdapterInactive, 
            ServantNotActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed {
        
        String[] config = new String[]{
            "-ORBInitialPort", "1050",
            "-ORBInitialHost", "ativ-corba-register"
        };
        
        ORB orb = ORB.init(config, System.getProperties());
       
        //Getting CORBA Object reference of RootPOA
        Object rootPOARef = orb.resolve_initial_references("RootPOA");
        //Parsing to RootPOA
        POA rootPOA = POAHelper.narrow(rootPOARef);
        //Activates the POA
        rootPOA.the_POAManager().activate();
        
        
        //Creates the servant with an Orb reference
        HelloImpl helloImpl = new HelloImpl(orb);
        
        //gets the reference equivalent to the created servant
        Object helloImplOrbReference = rootPOA.servant_to_reference(helloImpl);
        //binds the reference to an Hello interface
        Hello helloStub = HelloHelper.narrow(helloImplOrbReference);
        
        //Gets the name service orb reference
        Object nameServiceOrbReference = orb
                .resolve_initial_references("NameService");
        //binds the reference to a NamingContextExt interface
        NamingContextExt namingContextExt = NamingContextExtHelper
                .narrow(nameServiceOrbReference);
        
        //binding the helloStub to the namingContextExt
        NameComponent[] path = namingContextExt.to_name("Hello");
        namingContextExt.rebind(path, helloStub);
        
        
        System.out.println("Hello object is ready and waiting...");
        
        orb.run();
        
        
        
    }
}
