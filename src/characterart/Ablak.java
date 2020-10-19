package characterart;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ablak extends javax.swing.JFrame {

    private BufferedImage  image;
    
    private int width, height, pixels;
    private int booster = 1;
    
    private String result ="";
    
    public Ablak() {
        initComponents();
        this.setTitle("Fullbaro's CharacterArt");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   
        jTextArea1.setFont(new Font("monospaced", Font.PLAIN, 12)); // Azonos szélességű karakterek
        
        //convert("car.png");
    }

    class Futtat implements Runnable{                   
        /**
         * 
         * @param file A konvertálni kívánt kép. Megadható elérési úttal is.
         */
        private void convert(String file){        
            try {
                // infók a képről
                File input = new File(file);
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();
                pixels = width/booster * height/booster;
                int count=0;

                // progress bar beállítása
                jProgressBar1.setMaximum(pixels);
                jProgressBar1.setValue(0);
                jProgressBar1.setStringPainted(true);

                // konvertálás
                for(int i=0; i<height; i+=booster) {

                    for(int j=0; j<width; j+=booster) {

                        Color c = new Color(image.getRGB(j, i));
                        int red = (int)(c.getRed() * 0.299);
                        int green = (int)(c.getGreen() * 0.587);
                        int blue = (int)(c.getBlue() *0.114);

                        int index = (int)(red + green + blue)/3;

                        result += characterMatch2(index);
                        count++;
                        jProgressBar1.setValue(count);
                        jProgressBar1.setString(((double)count / (double)pixels)*100 + " %");
                        System.out.println(((double)count / (double)pixels)*100 + " %");
                    }
                    result += "\n";
                }           
            } catch (Exception e) {
                e.printStackTrace();
            }        
            jTextArea1.setText(result);
        }

        /**
         * 
         * @param index Átlagolt GRB szin éték (0-255).
         * @return Az árnyalatnak megfelelő karakter.
         */
        private String characterMatch(int index){
            String re=" ";
            if(index <= 25)
                re = "M";
            else if(index > 25 && index <= 50)
                re = "$";
            else if(index > 50 && index <= 67)
                re = "o";
            else if(index > 25 && index <= 102)
                re = "|";
            else if(index > 25 && index <= 127)
                re = "*";
            else if(index > 25 && index <= 152)
                re = ":";
            else if(index > 25 && index <= 178)
                re = "=";
            else if(index > 25 && index <= 204)
                re = "\\";
            else if(index > 25 && index <= 230)
                re = ".";

            return re;
        }
        
        private String characterMatch2(int index){
            String re = "";
            if(index == 0)
                re = " ";
            else if(index > 0 && index <= 6){
                re = "-";
            }else if(index > 6 && index <= 12){
                re = ".";
            }else if(index > 12 && index <= 18){
                re = "_";
            }else if(index > 18 && index <= 24){
                re = "'";
            }else if(index > 24 && index <= 30){
                re = ":";
            }else if(index > 30 && index <= 36){
                re = "=";
            }else if(index > 36 && index <= 42){
                re = "÷";
            }else if(index > 42 && index <= 48){
                re = ";";
            }else if(index > 48 && index <= 54){
                re = "*";
            }else if(index > 54 && index <= 60){
                re = "L";
            }else if(index > 60 && index <= 66){
                re = "T";
            }else if(index > 66 && index <= 72){
                re = "|";
            }else if(index > 72 && index <= 78){
                re = ">";
            }else if(index > 78 && index <= 84){
                re = "F";
            }else if(index > 84 && index <= 90){
                re = "<";
            }else if(index > 90 && index <= 96){
                re = "Ł";
            }else if(index > 96 && index <= 102){
                re = "í";
            }else if(index > 102 && index <= 108){
                re = "ł";
            }else if(index > 108 && index <= 114){
                re = "¤";
            }else if(index > 114 && index <= 120){
                re = "{";
            }else if(index > 120 && index <= 126){
                re = "5";
            }else if(index > 126 && index <= 132){
                re = "}";
            }else if(index > 132 && index <= 138){
                re = "€";
            }else if(index > 138 && index <= 144){
                re = "2";
            }else if(index > 144 && index <= 150){
                re = "C";
            }else if(index > 150 && index <= 156){
                re = "#";
            }else if(index > 156 && index <= 162){
                re = "ä";
            }else if(index > 162 && index <= 168){
                re = "Ä";
            }else if(index > 168 && index <= 174){
                re = "Ű";
            }else if(index > 174 && index <= 180){
                re = "G";
            }else if(index > 180 && index <= 186){
                re = "đ";
            }else if(index > 186 && index <= 192){
                re = "8";
            }else if(index > 192 && index <= 198){
                re = "&";
            }else if(index > 198 && index <= 204){
                re = "M";
            }else if(index > 204 && index <= 210){
                re = "N";
            }else if(index > 210 && index <= 216){
                re = "Đ";
            }else if(index > 216 && index <= 222){
                re = "Q";
            }else if(index > 222 && index <= 228){
                re = "$";
            }else if(index > 228 && index <= 234){
                re = "B";
            }else if(index > 234 && index <= 240){
                re = "@";
            }else{
                //valami
            }              
            return re;
        }

        private String fileChooser(){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                return chooser.getSelectedFile().getAbsolutePath();
            }
            return "";
        }

        @Override
        public void run() {
            convert(fileChooser());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pKozep = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        pFent = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jButton1 = new javax.swing.JButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jProgressBar1 = new javax.swing.JProgressBar();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jLabel1 = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jButton2 = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jButton3 = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 50), new java.awt.Dimension(10, 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 51));

        pKozep.setLayout(new java.awt.BorderLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        pKozep.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pKozep, java.awt.BorderLayout.CENTER);

        pFent.setLayout(new javax.swing.BoxLayout(pFent, javax.swing.BoxLayout.LINE_AXIS));
        pFent.add(filler2);

        jButton1.setText("Choose image");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pFent.add(jButton1);
        pFent.add(filler6);
        pFent.add(jProgressBar1);
        pFent.add(filler7);

        jLabel1.setText("Zoom");
        pFent.add(jLabel1);
        pFent.add(filler4);

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        pFent.add(jButton2);
        pFent.add(filler3);

        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pFent.add(jButton3);
        pFent.add(filler5);

        getContentPane().add(pFent, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // új szálon kell indítani
        Runnable r = new Futtat();
        Thread futtat = new Thread(r);
        futtat.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // zoom +
        jTextArea1.setFont(new Font(jTextArea1.getFont().getFontName(), jTextArea1.getFont().getStyle(), jTextArea1.getFont().getSize()+1));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Zoom -
        jTextArea1.setFont(new Font(jTextArea1.getFont().getFontName(), jTextArea1.getFont().getStyle(), jTextArea1.getFont().getSize()-1));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseReleased

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ablak().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pFent;
    private javax.swing.JPanel pKozep;
    // End of variables declaration//GEN-END:variables
}
