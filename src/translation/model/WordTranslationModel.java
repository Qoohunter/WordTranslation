package translation.model;

import translation.getdictionary.*;
import translation.cutbook.*;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class WordTranslationModel {
	
	private static String prev_word = "";
	
	public static void main(String[] args) throws Exception {
		
		JFrame JF = new JFrame();
		Container framePane = JF.getContentPane();
		GridBagLayout GBlayout = new GridBagLayout(); 	
		
		JF.setTitle("Word Translation");
		JF.setSize(600, 200);
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePane.setLayout(GBlayout);
		GridBagConstraints GridBagc = new GridBagConstraints();
       
        JLabel labelword = new JLabel("Query Word : ");
        GridBagc.gridx = 0;
        GridBagc.gridy = 0;
        GridBagc.gridwidth = 1;
        GridBagc.gridheight = 1;
        GridBagc.weightx = 0;
        GridBagc.weighty = 0;
        GridBagc.fill = GridBagConstraints.NONE;
        GridBagc.anchor = GridBagConstraints.NORTHEAST;
        GridBagc.insets = new Insets(20, 0, 0, 0);
        framePane.add(labelword, GridBagc);
        
        JTextField textfieldword = new JTextField();
        GridBagc.gridx = 1;
        GridBagc.gridy = 0;
        GridBagc.gridwidth = 6;
        GridBagc.gridheight = 1;
        GridBagc.weightx = 0;
        GridBagc.weighty = 0;
        GridBagc.fill = GridBagConstraints.HORIZONTAL;
        GridBagc.anchor = GridBagConstraints.NORTHEAST;
        GridBagc.insets = new Insets(20, 0, 0, 0);
        framePane.add(textfieldword, GridBagc);
        
        
        JLabel labelresult = new JLabel("Query Result : ");
        GridBagc.gridx = 0;
        GridBagc.gridy = 1;
        GridBagc.gridwidth = 1;
        GridBagc.gridheight = 1;
        GridBagc.weightx = 0;
        GridBagc.weighty = 0;
        GridBagc.fill = GridBagConstraints.NONE;
        GridBagc.anchor = GridBagConstraints.NORTHEAST;
        GridBagc.insets = new Insets(10, 0, 0, 0);
        framePane.add(labelresult, GridBagc);
        
        JTextArea TextArearesult = new JTextArea();
        GridBagc.gridx = 1;
        GridBagc.gridy = 1;
        GridBagc.gridwidth = 1;
        GridBagc.gridheight = 1;
        GridBagc.weightx = 1;
        GridBagc.weighty = 1;
        GridBagc.fill = GridBagConstraints.HORIZONTAL;
        GridBagc.anchor = GridBagConstraints.NORTHEAST;
        GridBagc.insets = new Insets(10, 0, 0, 0);
        framePane.add(TextArearesult, GridBagc);
        //JF.pack();
        JF.setVisible(true);
        
		CutBook cb = new CutBook();
		GetDictionary testhttp = new GetDictionary();
		String queryword = cb.getBookContents();
		queryword = queryword.trim();
		testhttp.sendGet(queryword);
		
		//System.out.println(Charset.defaultCharset());
		
        /*
         * Timer reference http://ithelp.ithome.com.tw/articles/10108532
         */
        Timer timer= new Timer();
		//System.out.println("Copy Start After 5 sec");
		TimerTask showtime= new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
					//System.out.println(new Date());
				String queryWord = cb.getBookContents();
				
				if(!prev_word.equals(queryWord)) {
					//System.out.println(new Date());
					textfieldword.setText(cb.getBookContents());

					queryWord = queryWord.trim();
					try {
						String result = testhttp.sendGet(queryWord);
						TextArearesult.setText(result);
						
						prev_word = queryWord;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		};
		timer.schedule(showtime, 1000, 2000);
		
	}
}
