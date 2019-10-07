/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.MensajeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.Mensaje;
import vista.BandejaEntrada;
import vista.NuevoMensaje;

/**
 *
 * @author geraldinenataliahenriquezsaez
 */
public class BandejaEntradaController implements ActionListener {
    
    private BandejaEntrada be;
    
    

    public BandejaEntradaController(BandejaEntrada be) {
        this.be = be;
    }
    
     

    @Override
    public void actionPerformed(ActionEvent e) {
        
        MensajeDao md= new MensajeDao();
        
        
     ArrayList<Mensaje> mensajes = md.getMensajes();
     
     DefaultTableModel dtm = (DefaultTableModel) be.getjTable1().getModel();
            dtm.setRowCount(0);
        
        for (int i = 0; i < mensajes.size(); i++) {
            Mensaje m = mensajes.get(i);


            Object[] fila = {m.getRemitente(),m.getContenido()};

            dtm.addRow(fila); 
            
        }
        
         
             
        
        NuevoMensaje nV= new NuevoMensaje();
        nV.setVisible(true);
        
    
    }
}

