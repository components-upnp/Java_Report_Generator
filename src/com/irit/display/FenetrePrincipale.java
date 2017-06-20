/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irit.display;

import com.irit.upnp.GenerationRapportController;
import com.irit.xml.LecteurXml;
import com.irit.xml.StockReponses;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.fourthline.cling.model.meta.LocalService;
import org.jfree.ui.RefineryUtilities;
import org.xml.sax.SAXException;

/**
 *
 * @author mkostiuk
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    private LocalService<GenerationRapportController> genService;
    
    public void init(LocalService<GenerationRapportController> grc) {
        genService = grc;
        text.setText("");
        
        genService.getManager().getImplementation().getPropertyChangeSupport()
                .addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                String stEvt = evt.getPropertyName();
                LecteurXml l = null;
                
                if (stEvt == "votes") {
                    try {
                        l = new LecteurXml((String)evt.getNewValue());
                  
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    StockReponses st = l.getStockReponses();
                    text.setText(st.getReponses().toString());
                    //PieChart p = new PieChart("Vote Results", l.getStockReponses());
                    if (st.getNbVotes() != 0) {
                        BarChart p = new BarChart("Reponses questionnaire", "Reponses questionnaire", l.getStockReponses());
                        p.setSize(560, 367);
                        RefineryUtilities.centerFrameOnScreen(p);
                        p.setVisible(true);
                    }
                }
            }
        });
    }
    
    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale(LocalService<GenerationRapportController> grc) {
        initComponents();
        init(grc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(text);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane text;
    // End of variables declaration//GEN-END:variables
}
