package myProject;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ispan.util.MyConnectionFactory;

import myModel.Shop;
import myModel.ShopDao;

public class WindowActionEvent extends JFrame {
	Connection connection = MyConnectionFactory.createMSSQLConnection();
	ShopDao sDao = new ShopDao(connection);
	
    JTextArea textShow;
    ReaderListen listener;
    JButton button;
    JTextField text,textField;
    JLabel label1,label3;
    JComboBox<Object> comBox,comBox2,choice;

    public WindowActionEvent() throws HeadlessException {
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {
        setLayout(new FlowLayout());
        
        comBox = new JComboBox<>();
        comBox.addItem("新增");
        comBox.addItem("刪除");
        comBox.addItem("更改");
        comBox.addItem("查詢");
        comBox.addItem("掃描檔案");
        comBox.addItem("匯入CSV");
        comBox.addItem("匯出CSV");
        add(comBox);


         
        add(new JLabel("                   欄位:"));
        comBox2 = new JComboBox<>();
        comBox2.addItem("子公司編號");
        comBox2.addItem("母公司編號");
        comBox2.addItem("子公司名");
        comBox2.addItem("母公司名");
        comBox2.addItem("地址");
        comBox2.addItem("狀態");
        comBox2.addItem("設立日期");
        comBox2.addItem("更改日期");
        add(comBox2);
         
        add(new JLabel("           鎖定值:"));
        text = new JTextField(20);
        add(text);
        
        add(new JLabel("           修改值:"));
        textField = new JTextField(20);
        add(textField);
        
        add(new JLabel("           執行:"));
        button = new JButton("   確認   ");
        add(button);
        
        
        
        textShow = new JTextArea(32, 80);
        listener = new ReaderListen();
        
        
        ButtonHandler handler = new ButtonHandler();
        handler.setDao(sDao);
        handler.setJComboBox(choice, comBox, comBox2);
        handler.setJTextArea(textShow);
        handler.setJTextField(text, textField);
        button.addActionListener(handler);
        
        add(new JLabel("     查詢已存在CSV檔案:"));
        choice = new JComboBox<>();
        choice.addItem("檔案列表");
        String path = "C:\\JavaWorkspace\\JDBCDemoDB\\src\\myProject\\";
        File dir = new File(path);
        FileAccept fileAccept = new FileAccept("csv"); // 設定字尾名
        String[] fileName = dir.list(fileAccept);// 把.java字尾的檔名return，並存到陣列中
        for (String name : fileName) { // Foreach return的.java檔名
            choice.addItem(path+name); // 把檔名新增到下拉列表中
        }

           
//        label3 = new JLabel("            查詢已存在CSV檔案");
//        label3.setBounds(70, 100, 150, 20);
//        add(label3);
        

        /**
         * 在ItemListener中自定義這個方法
         * 主要是要用到下拉列表框和文字區的變數,進行相應的操作
         */
        listener.setJComboBox(choice,comBox);
        listener.setJTextArea(textShow);

        choice.addItemListener(listener);
        add(choice);
        add(new JScrollPane(textShow)); //滾動窗格  常用容器
        
        comBox.addItemListener(listener);
        add(comBox);
        add(new JScrollPane(textShow)); //滾動窗格  常用容器

    }

    class FileAccept implements FilenameFilter { // 檔名過濾器
        private String type;

        FileAccept(String type) {
            this.type = type;
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(type);
        }

    }
}




class ReaderListen implements ItemListener {
    JComboBox<Object> choice,comboBox;
    JTextArea textShow;

    public void setJComboBox(JComboBox<Object> choice,JComboBox<Object> comboBox) {
        this.choice = choice;
        this.comboBox = comboBox;       
    }

    public void setJTextArea(JTextArea textShow) {
        this.textShow = textShow;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        try {
            if (comboBox.getSelectedItem().toString()!="掃描檔案") {
//         	   textShow.setText("刪除"+"\n");
            }else{
            textShow.setText(null);
            String fileName = choice.getSelectedItem().toString(); // 獲取下拉列表名稱
            File file = new File(fileName);
            FileReader inOne = new FileReader(file);
            BufferedReader inTwo = new BufferedReader(inOne); // 專門用來逐行讀取
            String s = null;
            while ((s = inTwo.readLine()) != null) { //逐行讀出
                textShow.append(s + "\n"); // 依次新增到textShow中
            }
            inOne.close();
            inTwo.close();
            }
        } catch (Exception e2) {
            textShow.append(e2.toString());
        }
        
    }
}

class ButtonHandler implements ActionListener {
    JComboBox<Object> choice,comboBox,comboBox2;
    JTextArea textShow;
    ShopDao sDao;
    JTextField text,textField;
    Shop shop;
    public void setDao(ShopDao s) {
    	this.sDao=s;
    }
    
    public void setJComboBox(JComboBox<Object> choice,JComboBox<Object> comboBox,JComboBox<Object> comboBox2) {
        this.choice = choice;
        this.comboBox = comboBox;   
        this.comboBox2 = comboBox2;   
    }
    
    public void setJTextArea(JTextArea textShow) {
        this.textShow = textShow;
    }
    
    public void setJTextField(JTextField text,JTextField textField) {
        this.text = text;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	try{
    		if(comboBox.getSelectedItem().toString()=="刪除") {
          	textShow.setText("刪除的資料如下:"+"\n");
    			List<Shop> list=new LinkedList<>();
    			list = sDao.findValue(comboBox2.getSelectedItem().toString(), text.getText().toString());
                for (Shop shop : list) {
                	textShow.append(shop.ToCSV() + "\n"); // 依次新增到textShow中
				}                 
    			sDao.deleteShop(comboBox2.getSelectedItem().toString(), text.getText().toString());;
    	        
    		}else if (comboBox.getSelectedItem().toString()=="更改") {
    		textShow.setText("被修改的原資料如下:"+"\n");
    			List<Shop> list=new LinkedList<>();
    			list = sDao.findValue(comboBox2.getSelectedItem().toString(), text.getText().toString());
                for (Shop shop : list) {
                	textShow.append(shop.ToCSV() + "\n");
				}   
            textShow.append("以上 "+comboBox2.getSelectedItem().toString()+ " 欄的 "+text.getText().toString()+" 值，被改成"); 
			sDao.updateShop(comboBox2.getSelectedItem().toString(), text.getText().toString(), textField.getText().toString());
    		
    		
    		}else if (comboBox.getSelectedItem().toString()=="查詢"){
				textShow.setText(comboBox2.getSelectedItem().toString()+" 含 "+text.getText().toString()+" 的查詢結果資料如下:"+"\n");
    			List<Shop> list=new LinkedList<>();
    			list = sDao.findValue(comboBox2.getSelectedItem().toString(), text.getText().toString());
                for (Shop shop : list) {
                	textShow.append(shop.ToCSV() + "\n");
				}   	
    		}else if (comboBox.getSelectedItem().toString()=="新增") {
    			textShow.setText("新增資料如下:"+"\n");
    			sDao.addShop(text.getText().toString());
    			textShow.append(text.getText().toString());
			}else if (comboBox.getSelectedItem().toString()=="匯出CSV") {
				OutPutCSV.out( text.getText().toString()); 
				 textShow.setText("匯出CSV檔案名為:"+text.getText().toString()+"\n");
			}else if (comboBox.getSelectedItem().toString()=="匯入CSV") {
				ImportCSV.importFile(text.getText().toString());
				textShow.setText("匯入CSV檔案名為:"+text.getText().toString()+"\n");
			}else {
    	           JOptionPane.showMessageDialog(null, "ELSE", "輸入錯誤(標題)", JOptionPane.ERROR_MESSAGE);
			}
        }catch(Exception e1){
            JOptionPane.showMessageDialog(null, "暫時有BUG沒空修"+comboBox2.getSelectedItem().toString()+text.getText().toString(), "輸入錯誤(標題)", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}