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
		
		
	    JFrame jf = new JFrame("JPanelDemo");
	    jf.setTitle("Word Translation");
	    jf.setSize(600, 150);
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container contentPane = jf.getContentPane();
	    
	    GridLayout gridlayout = new GridLayout(2, 1, 1, 10);
	    //FlowLayout flowlayout = new FlowLayout();
	    jf.setLayout(gridlayout);
	    //JPanel panelup = new JPanel(new GridLayout(1, 2));
	    JPanel panelup = new JPanel(new FlowLayout());
	    JPanel paneldown = new JPanel(new GridLayout(2, 1, 1, 1));
	    panelup.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    paneldown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    //JPanel paneldown = new JPanel(new FlowLayout());
	    
	    JPanel panel_small_1 =new JPanel();
	    JPanel panel_small_2 =new JPanel();
	    JPanel panel_small_3 =new JPanel();
	    panel_small_1.setLayout(new FlowLayout());
	    panel_small_2.setLayout(new FlowLayout());
	    panel_small_3.setLayout(new FlowLayout());

	    JLabel wordlabel = new JLabel("Query Word : ");
	    JTextField wordinput = new JTextField();
	    wordinput.setColumns(10);
	    panel_small_1.add(wordlabel);
	    panel_small_1.add(wordinput);
	    panelup.add(panel_small_1);
	    //panelup.add(wordlabel);
	    //panelup.add(panel_small_1);
	    //panelup.add(wordinput);
	    
	    JLabel cdictlabel = new JLabel("Query Result : ");
	    JTextArea cdictresult = new JTextArea();
	    cdictresult.setColumns(40);
	    JLabel yahoolabel = new JLabel("Query Yahoo Result : ");
	    JTextArea yahooresult = new JTextArea();
	    yahooresult.setColumns(40);
	    
	    panel_small_2.add(cdictlabel);
	    panel_small_2.add(cdictresult);
//	    paneldown.add(panel_small_2);
	    panel_small_3.add(yahoolabel);
	    panel_small_3.add(yahooresult);
//	    paneldown.add(panel_small_3);
	    
	    //paneldown.add(cdictlabel);
	    //panel_small_2.add(cdictresult);
	    paneldown.add(panel_small_2);
	    //paneldown.add(cdictresult);
	    //paneldown.add(yahoolabel);
	    //paneldown.add(yahooresult);
	    //panel_small_3.add(yahooresult);	
	    paneldown.add(panel_small_3);
	    
	    contentPane.add(panelup);
	    contentPane.add(paneldown);

	    jf.setVisible(true);

		CutBook cb = new CutBook();
		GetDictionary http = new GetDictionary();
		String queryword = cb.getBookContents();
		queryword = queryword.trim();
		http.sendGet_cdict(queryword);
		
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
					//textfieldword.setText(cb.getBookContents());

					queryWord = queryWord.trim();
					try {
						String result = http.sendGet_cdict(queryWord);
						//TextArearesult.setText(result);
						
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
