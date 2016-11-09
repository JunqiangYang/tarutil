package win;
import java.awt.BorderLayout;
import tar.TarUtils;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel; 
public class MainWind {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainWind mw = new MainWind() ;
		mw.show();
	}

    JFrame frame = new JFrame("IAMP tar 打包工具");  // 窗口容器.
    BorderLayout layout = new BorderLayout(5,5) ;
       
    
    //上半部
    JPanel panelup = new JPanel() ;
    JLabel inputlable = new JLabel("归档源:")  ;
    JLabel outlable = new JLabel("打包另存到:")  ;
    JTextField inputtext = new JTextField(500) ;
    JTextField outtext = new JTextField(500) ;
    JButton inputFilechosebtn = new JButton("归档源文件夹") ;
    JButton outFilechosebtn = new JButton("输出源文件夹") ;
    JFileChooser jFileChoose = new JFileChooser(new File(".")) ;
    //下半部
    JTabbedPane tabpanel =  new JTabbedPane() ;
    // tab A 组件列表
    JPanel panelA = new JPanel() ; 
    
    JLabel afile = new JLabel("文件夹名称(日期格式):")  ;
    JTextField afilename = new JTextField(50) ;
    JButton abtn = new JButton("扫描") ;
    String[] columnNames ={"选择", "文件名称"} ;
    Object rowData[][] ={{true,"123123"},{false,"123123"}};
    JTable jTbale1 = new JTable(rowData,columnNames) ;
    JTextArea atextarea = new JTextArea() ;
    
    JLabel apackname = new JLabel("打包名称(输入包标识后缀):") ; 
    JTextField filenametext = new JTextField(10) ;
    JLabel minname = new JLabel("_*****_") ; 
    JLabel affpri = new JLabel(".tar") ; 
    JTextField aftfix = new JTextField(5) ;
    JButton atar = new JButton("打包") ;
    String[] afiles = null ;
    
    
    // tab B 组件列表
    JPanel panelB = new JPanel() ; 
    JLabel bfile = new JLabel("文件夹前缀:")  ;
    JTextField bfilename = new JTextField(50) ;
    JButton bbtn = new JButton("扫描") ;
    JTextArea btextarea = new JTextArea() ;
    JLabel bpackname = new JLabel("打包名称(输入包标识后缀):") ; 
    JTextField pritext = new JTextField(10) ;
    JTextField bftfix = new JTextField(5) ;
    JLabel bminname = new JLabel("_") ; 
    JLabel baffpri = new JLabel(".tar") ; 
    JButton btar = new JButton("打包") ;
    String[] bfiles = null ;
    
    // 压缩进度条窗口
    TarProcessWind tarprocesswin  ;
    
    public MainWind() { 
    	frame.setSize(900, 700);  
    	frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setLayout(layout);
        frame.add(panelup,BorderLayout.NORTH);  
        panelup.setBorder(new TitledBorder("输入输出源"));
        panelup.setLayout(new GridLayout(2, 5)) ;
        inputtext.setEditable(false);
        outtext.setEditable(false);
        jFileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChoose.setMultiSelectionEnabled(false);
        inputFilechosebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jFileChoose.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION ){
					inputtext.setText(jFileChoose.getSelectedFile().getPath());
				}
			}
		});
        outFilechosebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jFileChoose.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION ){
					outtext.setText(jFileChoose.getSelectedFile().getPath());
				}
			}
		});
        
        panelup.add(new JLabel()); 
        panelup.add(inputlable); // 添加内容组件.
        panelup.add(inputtext); 
        panelup.add(inputFilechosebtn);
        panelup.add(new JLabel()); 
        panelup.add(new JLabel()); 
        panelup.add(outlable); 
        panelup.add(outtext); 
        panelup.add(outFilechosebtn); 
        panelup.add(new JLabel()); 
        frame.add(tabpanel,BorderLayout.CENTER);  
        tabpanel.setBorder(new TitledBorder("打包方式"));
        // 设置 tab A 的组件
        panelA.setLayout(new BorderLayout());
        JPanel panelbtema = new JPanel() ;
        panelbtema.add(afile);
        panelbtema.add(afilename);
        panelbtema.add(abtn);
        panelA.add(panelbtema,BorderLayout.NORTH) ;
        JPanel panelbtema4 = new JPanel() ;
        panelbtema4.setLayout(new BorderLayout());
        JPanel panelbtema2 = new JPanel() ;
        panelbtema2.setLayout(new BorderLayout());
        atextarea.setLineWrap(true);
        atextarea.setEditable(false);
        atextarea.setLineWrap(true);
        atextarea.setColumns(20);
        atextarea.setSize(300, 500);
//        panelbtema2.add(new JScrollPane(jTbale1)) ;
        panelbtema2.add(new JScrollPane(atextarea),BorderLayout.CENTER) ;
        JPanel panelbtema3 = new JPanel() ;
        apackname = new JLabel("打包名称(输入包标识后缀):") ; 
        filenametext.setEditable(false);
        panelbtema3.add(apackname) ;
        panelbtema3.add(filenametext) ;
        panelbtema3.add(minname) ;
        panelbtema3.add(aftfix) ;
        panelbtema3.add(affpri) ;
        panelbtema4.add(panelbtema2,BorderLayout.CENTER);
        panelbtema4.add(panelbtema3,BorderLayout.SOUTH);
        panelA.add(panelbtema4,BorderLayout.CENTER);
        panelA.add(atar,BorderLayout.SOUTH);
        abtn.addActionListener(new ActionListener() {  //扫描
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 将查询到的列表放置到 列表控件中 .
				if("".equals(afilename.getText())){
					JOptionPane.showMessageDialog(frame, "文件夹名称(日期格式)");
					return ;
				}
				String isrc = inputtext.getText() ;
				String osrc = outtext.getText() ;
				String filedir = afilename.getText() ; //
				File f  = new File(isrc+File.separator+filedir); // 要查找的文件夹路径
				if( f.exists()  && f .isDirectory())      
				 {       
			    	 File[] files = f.listFiles();  // 这里需要递归的查找 是文件夹.
			    	// System.out.println(Arrays.toString(files));
			    	 if(files.length>0){
			    	 atextarea.setText(Arrays.toString(files).replace(",", "\r\n"));
			    	 filenametext.setText(filedir);
			    	 }else{
			    		 JOptionPane.showMessageDialog(frame, "文件无内容");
			    	 }
				 }else{
					 JOptionPane.showMessageDialog(frame, "文件夹不存在");
				 }
			}
		});
        atar.addActionListener(new ActionListener() { // 打包 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if("".equals(aftfix.getText())){
					JOptionPane.showMessageDialog(frame, "文件夹后缀标示不能为空");
					return ;
				}
				if("".equals(filenametext.getText())){
					JOptionPane.showMessageDialog(frame, "请先扫描文件夹");
					return ;
				}
				String isrc = inputtext.getText() ;
				String osrc = outtext.getText() ;
				final String filenamedate = afilename.getText() ;
				String prifix = aftfix.getText() ;
				tarprocesswin = new TarProcessWind(afiles,isrc,osrc,filenamedate,prifix,1); // 方式A 为1 
			}
		});
        
        tabpanel.add("方式A", panelA);
        // 设置 tab B 的组件
        btextarea.setLineWrap(true);
        btextarea.setEditable(false);
        btextarea.setLineWrap(true);
        btextarea.setColumns(20);
        btextarea.setSize(300, 500);
        JPanel panelbtemp = new JPanel() ;
        panelbtemp.add(bfile);
        panelbtemp.add(bfilename);
        panelbtemp.add(bbtn);
        panelB.setLayout(new BorderLayout());
        panelB.add(panelbtemp,BorderLayout.NORTH) ;
        JPanel panelbtemb4 = new JPanel() ;
        panelbtemb4.setLayout(new BorderLayout());
        JPanel panelbtemb2 = new JPanel() ;
        panelbtemb2.setLayout(new BorderLayout());
        panelbtemb2.add(new JScrollPane(btextarea),BorderLayout.CENTER) ;
        JPanel panelbtemb3 = new JPanel() ;
        JLabel bpackname = new JLabel("打包名称(输入包标识后缀):") ; 
        pritext.setEditable(false);
        panelbtemb3.add(bpackname) ;
        panelbtemb3.add(pritext) ;
        panelbtemb3.add(bminname) ;
        panelbtemb3.add(bftfix) ;
        panelbtemb3.add(baffpri) ;
        panelbtemb4.add(panelbtemb2,BorderLayout.CENTER);
        panelbtemb4.add(panelbtemb3,BorderLayout.SOUTH);
        panelB.add(panelbtemb4,BorderLayout.CENTER);
        panelB.add(btar,BorderLayout.SOUTH);
        bbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if("".equals(bfilename.getText())){
					JOptionPane.showMessageDialog(frame, "文件夹前缀");
					return ;
				}
				String isrc = inputtext.getText() ;
				String osrc = outtext.getText() ;
				final String filepre = bfilename.getText() ; 
				File f  = new File(isrc);
				bfiles = f.list(new FilenameFilter() {
				    @Override
				    public boolean accept(File dir, String name) {
				        return name.startsWith(filepre);
				    }
				});
				if(bfiles.length>0){
					pritext.setText(filepre);
					btextarea.setText(Arrays.toString(bfiles).replace(",", "\r\n"));
				}else{
					JOptionPane.showMessageDialog(frame, "文件夹不存在");
				}
			}
		});
        btar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if("".equals(bftfix.getText())){
					JOptionPane.showMessageDialog(frame, "文件夹后缀标示不能为空");
					return ;
				}
				if("".equals(pritext.getText())){
					JOptionPane.showMessageDialog(frame, "请先扫描文件夹");
					return ;
				}
				String isrc = inputtext.getText() ;
				String osrc = outtext.getText() ;
				final String filenamepri = bfilename.getText() ;
				String prifix = bftfix.getText()  ;
				tarprocesswin = new TarProcessWind(bfiles,isrc,osrc,filenamepri,prifix,2); // 方式B 为2 
			}
		});
        tabpanel.add("方式B", panelB);
        
    }  
 
    public void show() {  
        frame.setVisible(true);  
    }  
	
}
class MyTableModel extends DefaultTableModel {
	  public MyTableModel() {
	  }

	  /** Construct a table model with specified data and columnNames */
	  public MyTableModel(Object[][] data, Object[] columnNames) {
	    super(data, columnNames);
	  }

	  /** Override this method to return a class for the column */
	  public Class getColumnClass(int column) {
	    return getValueAt(0, column).getClass();
	  }

	  /** Override this method to return true if cell is editable */
	  public boolean isCellEditable(int row, int column) {
	    Class columnClass = getColumnClass(column);
	    return columnClass != ImageIcon.class &&
	      columnClass != Date.class;
	  }
}